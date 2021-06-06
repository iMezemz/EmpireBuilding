package engine;

import java.io.IOException;
import java.util.ArrayList;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import exceptions.FriendlyFireException;
import units.*;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount;
	private int currentTurnCount;

	public Game(String playerName, String playerCity) throws IOException {
		player = new Player(playerName);

		player.addBaseCity(playerCity);

		maxTurnCount = 30;

		currentTurnCount = 1;

		availableCities = new ArrayList<City>();

		distances = new ArrayList<Distance>();

		loadCitiesAndDistances();

		availableCities.get(availableCities.indexOf(new City(playerCity))).setDefendingArmy(null);

		// Generate defending armies for non player controlled cities only
		for (int i = 0; i < availableCities.size(); i++) {
			String name = availableCities.get(i).getName();
			if (!playerCity.equals(name)) {
				loadArmy(name, name.toLowerCase() + "_army.csv");
			}
		}
	}

	public void loadArmy(String cityName, String path) throws IOException {

		// Loading CSV resources for level dictated army information
		ArrayList<String[]> Archers = ReadingCSVFile.readFile("Archer.csv");
		ArrayList<String[]> Infantry = ReadingCSVFile.readFile("Infantry.csv");
		ArrayList<String[]> Cavalry = ReadingCSVFile.readFile("Cavalry.csv");

		// Loading the city's army information (size, types and levels)
		ArrayList<String[]> armyInfo = ReadingCSVFile.readFile(path);

		// Initiallizing an array of units and filling it with info from the
		// above CSVs
		Army armyInsert = new Army(cityName);
		ArrayList<Unit> units = new ArrayList<Unit>();

		for (int i = 0; i < armyInfo.size(); i++) {

			String[] armyLine = armyInfo.get(i);

			int level = Integer.parseInt(armyLine[1]);
			if (armyLine[0].equals("Archer")) {

				String[] ArcherInfo = Archers.get(level - 1);
				Archer tempArcher = new Archer(level, Integer.parseInt(ArcherInfo[1]),
						Double.parseDouble(ArcherInfo[2]), Double.parseDouble(ArcherInfo[3]),
						Double.parseDouble(ArcherInfo[4]));

				tempArcher.setParentArmy(armyInsert);
				units.add(tempArcher);

			} else if (armyLine[0].equals("Infantry")) {

				String[] InfantryInfo = Infantry.get(level - 1);
				Infantry tempInfantry = new Infantry(level, Integer.parseInt(InfantryInfo[1]),
						Double.parseDouble(InfantryInfo[2]), Double.parseDouble(InfantryInfo[3]),
						Double.parseDouble(InfantryInfo[4]));
				tempInfantry.setParentArmy(armyInsert);
				units.add(tempInfantry);
			} else {

				String[] CavalryInfo = Cavalry.get(level - 1);
				Cavalry tempCavalry = new Cavalry(level, Integer.parseInt(CavalryInfo[1]),
						Double.parseDouble(CavalryInfo[2]), Double.parseDouble(CavalryInfo[3]),
						Double.parseDouble(CavalryInfo[4]));
				tempCavalry.setParentArmy(armyInsert);
				units.add(tempCavalry);
			}
		}

		// Finding and inserting the units into the defending city
		int index = availableCities.indexOf(new City(cityName));

		armyInsert.setUnits(units);

		availableCities.get(index).setDefendingArmy(armyInsert);

	}

	private void loadCitiesAndDistances() throws IOException {

		// Loading distance resources
		ArrayList<String[]> distanceList = ReadingCSVFile.readFile("distances.csv");

		// Inserting the Cities inside the available cities array by inserting
		// non duplicated city objects
		for (int i = 0; i < distanceList.size(); i++) {
			for (int j = 0; j < 2; j++) {
				City c = new City(distanceList.get(i)[j]);
				if (!availableCities.contains(c)) {
					availableCities.add(c);
					
				}
			}

		}

		// Inserting distance objects from the resource file into distances
		// array

		for (int i = 0; i < distanceList.size(); i++) {

			String[] distanceLine = distanceList.get(i);

			String from = distanceLine[0];

			String to = distanceLine[1];

			int value = Integer.parseInt(distanceLine[2]);

			Distance toBeInserted = new Distance(from, to, value);

			this.distances.add(toBeInserted);
		}
	}
	
	public void occupy(Army a, String cityName) {
		for(City c: availableCities) {
			if(c.getName().equals(cityName)) {
				player.getControlledCities().add(c);
				c.setDefendingArmy(a);
				c.setUnderSiege(true);
				c.setTurnsUnderSiege(0);
			}
			
		}
			
		
		
	}
	
	public boolean isGameOver() { 
		boolean finalFlag = false;
		if(currentTurnCount > maxTurnCount) {
			finalFlag = true;
		}
		
		for(City c: availableCities) { // compare all cities in controlled cities to cities in available cities
			if(player.getControlledCities().contains(c)) {
				finalFlag = true;
			}
			else {
				finalFlag = false;
				break;
				
			}
			
		}
		
		
		
		return finalFlag;
	}
	

	public void targetCity(Army army, String targetName) {
		int index = distances.indexOf(new Distance(army.getCurrentLocation(), targetName, -1));
		index = (index == -1) ? distances.indexOf(new Distance(targetName, army.getCurrentLocation(), -1)) : index;
		int distanceToTarget = this.distances.get(index).getDistance();
		army.setDistancetoTarget(distanceToTarget);
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
				int harvested = b.harvest();
				double currentTreasury = tempPlayer.getTreasury();
				tempPlayer.setTreasury(harvested + currentTreasury);
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
			if (a.getDistancetoTarget() != -1)
				a.setDistancetoTarget(a.getDistancetoTarget() - 1);
		}
		double playerFood = tempPlayer.getFood() - foodDecrement;
		if (playerFood < 0) {
			for (Army a : tempPlayer.getControlledArmies()) {
				for (Unit u : a.getUnits()) {
					int soldierCount = (int) Math.floor(u.getCurrentSoldierCount() * 0.1);
					u.setCurrentSoldierCount(soldierCount);
				}
			}
		}
		tempPlayer.setFood(0);
		for (City c : availableCities) {
			if (c.isUnderSiege()) {
				c.setTurnsUnderSiege(c.getTurnsUnderSiege() + 1);
				for (Unit u : c.getDefendingArmy().getUnits()) {
					int soldierCount = (int) Math.floor(u.getCurrentSoldierCount() * 0.1);
					u.setCurrentSoldierCount(soldierCount);
				}

			}
		}

	}
	public void autoResolve(Army attacker, Army defender) throws
	FriendlyFireException{
		ArrayList<Unit> attackerUnits = attacker.getUnits();
		ArrayList<Unit> defenderUnits = defender.getUnits();
		boolean attack = true;
		
		while(attackerUnits.size()!=0 && defenderUnits.size()!=0){
			int randomAttackerIndex = (int)Math.random()*attackerUnits.size();
			int randomDefenderIndex = (int)Math.random()*defenderUnits.size();
			if(attack)
				attackerUnits.get(randomAttackerIndex).attack(defenderUnits.get(randomDefenderIndex));
			else
				defenderUnits.get(randomDefenderIndex).attack(attackerUnits.get(randomAttackerIndex));
			attack = !attack;
			endTurn();		
		}
		if(defenderUnits.size() == 0)
			occupy(attacker,defender.getCurrentLocation());


		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getCurrentTurnCount() {
		return currentTurnCount;
	}

	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}

	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}

	public ArrayList<Distance> getDistances() {
		return distances;
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}

	public void targetCity(Army army, String string) {
		// TODO Auto-generated method stub
		
	}

	public void endTurn() {
		// TODO Auto-generated method stub
		
	}

}
