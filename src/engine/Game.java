package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.TargetNotReachedException;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 30;
	private int currentTurnCount;

	public Game(String playerName, String playerCity) throws IOException {

		player = new Player(playerName);
		availableCities = new ArrayList<City>();
		distances = new ArrayList<Distance>();
		currentTurnCount = 1;
		loadCitiesAndDistances();
		for (City c : availableCities) {
			if (c.getName().equals(playerCity))

				player.getControlledCities().add(c);

			else
				loadArmy(c.getName(), c.getName().toLowerCase() + "_army.csv");
			

		}
	}

	private void loadCitiesAndDistances() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("distances.csv"));
		String currentLine = br.readLine();
		ArrayList<String> names = new ArrayList<String>();

		while (currentLine != null) {

			String[] content = currentLine.split(",");
			if (!names.contains(content[0])) {
				availableCities.add(new City(content[0]));
				names.add(content[0]);
			} else if (!names.contains(content[1])) {
				availableCities.add(new City(content[1]));
				names.add(content[1]);
			}
			distances.add(new Distance(content[0], content[1], Integer.parseInt(content[2])));
			currentLine = br.readLine();

		}
		br.close();
	}

	public void loadArmy(String cityName, String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String currentLine = br.readLine();
		Army resultArmy = new Army(cityName);
		while (currentLine != null) {
			String[] content = currentLine.split(",");
			String unitType = content[0].toLowerCase();
			int unitLevel = Integer.parseInt(content[1]);
			Unit u = null;
			if (unitType.equals("archer")) {

				if (unitLevel == 1)
					u = (new Archer(1, 60, 0.4, 0.5, 0.6));

				else if (unitLevel == 2)
					u = (new Archer(2, 60, 0.4, 0.5, 0.6));
				else
					u = (new Archer(3, 70, 0.5, 0.6, 0.7));
			} else if (unitType.equals("infantry")) {
				if (unitLevel == 1)
					u = (new Infantry(1, 50, 0.5, 0.6, 0.7));

				else if (unitLevel == 2)
					u = (new Infantry(2, 50, 0.5, 0.6, 0.7));
				else
					u = (new Infantry(3, 60, 0.6, 0.7, 0.8));
			} else if (unitType.equals("cavalry")) {
				if (unitLevel == 1)
					u = (new Cavalry(1, 40, 0.6, 0.7, 0.75));

				else if (unitLevel == 2)
					u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
				else
					u = (new Cavalry(3, 60, 0.7, 0.8, 0.9));
			}
			u.setParentArmy(resultArmy);
			resultArmy.getUnits().add(u);
			currentLine = br.readLine();
		}
		br.close();
		for (City c : availableCities) {
			if (c.getName().toLowerCase().equals(cityName.toLowerCase()))
				c.setDefendingArmy(resultArmy);
		}
	}
	public void targetCity(Army army, String targetName){
		if(army.getDistancetoTarget() == -1){
			int index = distances.indexOf(new Distance(army.getCurrentLocation(),targetName,-1));
			index = (index == -1)?distances.indexOf(
					new Distance(targetName,army.getCurrentLocation(),-1)):index;
		int distance = distances.get(index).getDistance();
		army.setDistancetoTarget(distance);	
		army.setTarget(targetName);
		}
	}

	public void endTurn() {
		// increment turn count
		this.setCurrentTurnCount(getCurrentTurnCount() + 1);
		// player buildings' cooldowns to false, currentTurnCount to 0
		// Harvested all player economical buildings and added the yield to the player
		// treasury
		Player tempPlayer = getPlayer();
		for (City c : tempPlayer.getControlledCities()) {
			ArrayList<EconomicBuilding> economicBuildings = c.getEconomicalBuildings();
			for (EconomicBuilding b : economicBuildings) {
				b.setCoolDown(false);
				if(b instanceof Market){
				int harvestedGold = b.harvest();
				double currentTreasury = tempPlayer.getTreasury();
				tempPlayer.setTreasury(harvestedGold + currentTreasury);}
				if(b instanceof Farm){
					int harvestedFood = b.harvest();
					double currentFood = tempPlayer.getFood();
					tempPlayer.setFood(harvestedFood + currentFood);}
			}
			ArrayList<MilitaryBuilding> militaryBuildings = c.getMilitaryBuildings();
			for (MilitaryBuilding b : militaryBuildings) {
				b.setCoolDown(false);
				b.setCurrentRecruit(0);
			}
		}
		double foodDecrement = 0.0;
		for (Army a : tempPlayer.getControlledArmies()) {
			foodDecrement += a.foodNeeded();
			if (a.getDistancetoTarget() != -1 && a.getTarget() != null)
				a.setDistancetoTarget(a.getDistancetoTarget() - 1);
			if(a.getDistancetoTarget() == 0 ){
				City target = availableCities.get(availableCities.indexOf(new City(a.getTarget())));
				try {
					tempPlayer.laySiege(a,target);
				} catch (TargetNotReachedException e) {} catch (FriendlyCityException e) {}
		}}
		double playerFood = tempPlayer.getFood() - foodDecrement;
		if (playerFood < 0) {
			for (Army a : tempPlayer.getControlledArmies()) {
				for (Unit u : a.getUnits()) {
					int soldierCount = (int) Math.floor(u.getCurrentSoldierCount() * 0.9);
					u.setCurrentSoldierCount(soldierCount);
				}
			}
			tempPlayer.setFood(0);
		}
		else
			tempPlayer.setFood(playerFood);
		
		for (City c : availableCities) {
			if (c.isUnderSiege()) {
				c.setTurnsUnderSiege(c.getTurnsUnderSiege() + 1);
				for (Unit u : c.getDefendingArmy().getUnits()) {
					int soldierCount = (int) Math.floor(u.getCurrentSoldierCount() * 0.9);
					u.setCurrentSoldierCount(soldierCount);
				}

			}
		}

	}
	public void occupy(Army a,String cityName){
		int index = availableCities.indexOf(new City(cityName));
		City occupiedCity = availableCities.get(index);
		occupiedCity.setDefendingArmy(a);
		occupiedCity.setTurnsUnderSiege(-1);
		occupiedCity.setUnderSiege(false);
		getPlayer().getControlledCities().add(occupiedCity);
		
	}

	public void autoResolve(Army attacker, Army defender) throws
	FriendlyFireException{
		ArrayList<Unit> attackerUnits = attacker.getUnits();
		ArrayList<Unit> defenderUnits = defender.getUnits();
		boolean attack = true;
		
		while(attackerUnits.size()>0 && defenderUnits.size()>0){
			int randomAttackerIndex = (int)Math.floor(Math.random()*attackerUnits.size());
			int randomDefenderIndex = (int)Math.floor(Math.random()*defenderUnits.size());
			if(attack){
				attackerUnits.get(randomAttackerIndex).attack(defenderUnits.get(randomDefenderIndex));
//				defender.handleAttackedUnit(defenderUnits.get(randomDefenderIndex));
			}
			else{
				defenderUnits.get(randomDefenderIndex).attack(attackerUnits.get(randomAttackerIndex));
//				attacker.handleAttackedUnit(attackerUnits.get(randomAttackerIndex));
			}
			attack = !attack;
			
			endTurn();		
		}
		if(defenderUnits.size() == 0 && attackerUnits.size()!=0)
			occupy(attacker,defender.getCurrentLocation());

	}
	public boolean isGameOver() { 
		if(currentTurnCount > maxTurnCount) {
			return true;
		}
		for(City c: availableCities) { // compare all cities in controlled cities to cities in available cities
			if(!player.getControlledCities().contains(c)) {
				return false;
			}
		}
		return false;
	}
	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}

	public ArrayList<Distance> getDistances() {
		return distances;
	}

	public int getCurrentTurnCount() {
		return currentTurnCount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}

	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}

}
