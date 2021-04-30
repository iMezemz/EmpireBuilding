package engine;

import java.io.IOException;
import java.util.ArrayList;

import units.*;

public class Game {
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount;
	private int currentTurnCount;

	public Game(String playerName, String playerCity) throws IOException {
		player = new Player(playerName, playerCity);

		maxTurnCount = 30;

		currentTurnCount = 1;

		availableCities = new ArrayList<City>();

		distances = new ArrayList<Distance>();

		loadCitiesAndDistances();

		// Generate defending armies for non player controlled cities only
		for (int i = 0; i < availableCities.size(); i++) {
			String name = availableCities.get(i).getName();
			if (!playerCity.equals(name)) {
				loadArmy(name, name + ".csv");
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
		ArrayList<Unit> units = new ArrayList<Unit>();

		for (int i = 0; i < armyInfo.size(); i++) {

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

		// Finding and inserting the units into the defending city
		int index = availableCities.indexOf(new City(cityName));

		Army armyInsert = new Army(cityName);

		armyInsert.setUnits(units);

		availableCities.get(index).setDefendingArmy(armyInsert);

	}

	public void loadCitiesAndDistances() throws IOException {

		// Loading distance resources
		ArrayList<String[]> distanceList = ReadingCSVFile
				.readFile("distances.csv");

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

}
