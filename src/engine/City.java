package engine;

import java.util.ArrayList;

import units.Army;
import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;

public class City {
	private String name;
	private ArrayList<EconomicBuilding> economicalBuildings;
	private ArrayList<MilitaryBuilding> militaryBuildings;
	private Army defendingArmy;
	private int turnsUnderSiege;
	private boolean underSiege;

	// No initiallization for defending army because the city may or may not be
	// defending
	// (i.e. player controlled)
	public City(String name) {
		this.name = name;
		economicalBuildings = new ArrayList<EconomicBuilding>();
		militaryBuildings = new ArrayList<MilitaryBuilding>();
		turnsUnderSiege = 0;
		underSiege = false;
	}

	public Army getDefendingArmy() {
		return defendingArmy;
	}

	public void setDefendingArmy(Army defendingArmy) {
		this.defendingArmy = defendingArmy;
	}

	public int getTurnsUnderSiege() {
		return turnsUnderSiege;
	}

	public void setTurnsUnderSiege(int turnsUnderSiege) {
		this.turnsUnderSiege = turnsUnderSiege;
	}

	public boolean isUnderSiege() {
		return underSiege;
	}

	public void setUnderSiege(boolean underSiege) {
		this.underSiege = underSiege;
	}

	public String getName() {
		return name;
	}

	// written for convenience of loadArmy and loadCitiesAndDistances methods 
	//(contains and indexOf method mainly)
	// (MAY NEED IMPROVMENT LATER)
	public boolean equals(Object o) {
		City c = (City) o;
		return c.name.equals(name);
	}

	public ArrayList<EconomicBuilding> getEconomicalBuildings() {
		return economicalBuildings;
	}

	public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
		return militaryBuildings;
	}

}
