package engine;

import java.io.IOException;
import java.util.ArrayList;

import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount;
	private int currentTurnCount;

	// City names and city distances yet to be filled
	public Game(String playerName, String playerCity) throws IOException {
		player = new Player(playerName);

		maxTurnCount = 30;

		currentTurnCount = 1;

		availableCities = new ArrayList<City>();

		distances = new ArrayList<Distance>();
		
		loadCitiesAndDistances();
		for (int i = 0; i < availableCities.size(); i++) {
			String name = availableCities.get(i).getName();
			if(!playerCity.equals(name)){
				loadArmy(name,name+".csv");
			}

		}

		
	}

	public void loadArmy(String cityName, String path) throws IOException {
		ArrayList<String[]> Archers = ReadingCSVFile.readFile("Archer.csv");
		ArrayList<String[]> Infantry = ReadingCSVFile.readFile("Infantry.csv");
		ArrayList<String[]> Cavalry = ReadingCSVFile.readFile("Cavalry.csv");
		ArrayList<String[]> armyInfo = ReadingCSVFile.readFile(path);
		ArrayList<Unit> units = new ArrayList<Unit>();
		for(int i = 0; i< armyInfo.size(); i++){
		String[] armyLine = armyInfo.get(i);
		int level = Integer.parseInt(armyLine[1]);
		if (armyLine[0].equals("Archer")) {
			
			String[] ArcherInfo = Archers.get(level - 1);
			
			units.add(new Archer(level, 
					Integer.parseInt(ArcherInfo[1]),
					Double.parseDouble(ArcherInfo[2]), 
					Double.parseDouble(ArcherInfo[3]), 
					Double.parseDouble(ArcherInfo[4])));
			
		} else if (armyLine[0].equals("Infantry")) {
			
			String[] InfantryInfo = Infantry.get(level - 1);
			
			units.add(new Infantry(level,
					Integer.parseInt(InfantryInfo[1]), 
					Double.parseDouble(InfantryInfo[2]), 
					Double.parseDouble(InfantryInfo[3]),
					Double.parseDouble(InfantryInfo[4])));
		} else {
			
			String[] CavalryInfo = Cavalry.get(level - 1);
			
			units.add(new Cavalry(level, 
					Integer.parseInt(CavalryInfo[1]),
					Double.parseDouble(CavalryInfo[2]),
					Double.parseDouble(CavalryInfo[3]), 
					Double.parseDouble(CavalryInfo[4])));
		}
		}
		Army armyInsert = new Army(cityName);
		armyInsert.setUnits(units);
		City cityInsert = new City(cityName);
		cityInsert.setDefendingArmy(armyInsert);
		availableCities.add(cityInsert);
		

	}

	public void loadCitiesAndDistances() throws IOException {
		

		ArrayList<String[]> distanceList = ReadingCSVFile
				.readFile("distances.csv");

		for (int i = 0; i < distanceList.size(); i++) {

			String[] distanceLine = distanceList.get(i);

			String from = distanceLine[0];

			String to = distanceLine[1];

			int value = Integer.parseInt(distanceLine[2]);

			Distance toBeInserted = new Distance(from, to, value);

			this.distances.add(toBeInserted);
		}
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
	public static void main(String[] args) {
		
	}
}
