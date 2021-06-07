package engine;

import java.util.ArrayList;

import units.Status;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Unit;

public class Player {
	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;

	public Player(String name) {
		this.name = name;
		this.controlledCities = new ArrayList<City>();
		this.controlledArmies = new ArrayList<Army>();
	}

	public void recruitUnit(String type, String cityName)
			throws BuildingInCoolDownException, MaxRecruitedException,
			NotEnoughGoldException {
		if(treasury == 0){
			throw new NotEnoughGoldException();
		}
		
		City givenCity = null;
		
		for (City c : this.controlledCities) {
			if (c.getName().equals(cityName)){
				givenCity = c;
			}
		}
		if(givenCity!=null){
		ArrayList<MilitaryBuilding> militaryBuildings = givenCity
				.getMilitaryBuildings();
		Unit toBeRecruited = null;
		int recruitementCost = 0;
		if (type.equals("Archer")) {
			for (MilitaryBuilding b : militaryBuildings) {
				if (b instanceof ArcheryRange){
					toBeRecruited = b.recruit();
					recruitementCost = b.getRecruitmentCost();
					}
			}
		} else if (type.equals("Infantry")) {
			for (MilitaryBuilding b : militaryBuildings) {
				if (b instanceof Barracks){
					toBeRecruited = b.recruit();
					recruitementCost = b.getRecruitmentCost();
					}
			}
		} else if (type.equals("Cavalry")) {
			for (MilitaryBuilding b : militaryBuildings) {
				if (b instanceof Stable){
					toBeRecruited = b.recruit();
					recruitementCost = b.getRecruitmentCost();
					}
			}
			
			
			if (recruitementCost > this.treasury)
				throw new NotEnoughGoldException();
			
			
			ArrayList<Unit> tempUnit = givenCity.getDefendingArmy().getUnits();
			tempUnit.add(toBeRecruited);
			givenCity.getDefendingArmy().setUnits(tempUnit);
			for (Army a : controlledArmies) {
				if (a.getCurrentLocation() == cityName) {
					toBeRecruited.setParentArmy(a);
					a.getUnits().add(toBeRecruited);
				}
				
			
			this.treasury -= recruitementCost;

		}
	}}}

	public void build(String type, String cityName)
			throws NotEnoughGoldException {
		City givenCity = null;
		for (City c : this.controlledCities) {
			if (c.getName().equals(cityName))
				givenCity = c;
		}
		Building toBeBuilt = null;
		boolean military = false;
		boolean uniqueBuilding = true;
		if (type.equals("ArcheryRange")) {
			toBeBuilt = new ArcheryRange();
			military = true;
			for(MilitaryBuilding b : givenCity.getMilitaryBuildings()){
				if(b instanceof ArcheryRange)
					uniqueBuilding = false;
			}
		} else if (type.equals("Barracks")) {
			toBeBuilt = new Barracks();
			military = true;
			for(MilitaryBuilding b : givenCity.getMilitaryBuildings()){
				if(b instanceof Barracks)
					uniqueBuilding = false;
			}
		} else if (type.equals("Stable")) {
			toBeBuilt = new Stable();
			military = true;
			for(MilitaryBuilding b : givenCity.getMilitaryBuildings()){
				if(b instanceof Stable)
					uniqueBuilding = false;
			}
		} else if (type.equals("Farm")) {
			toBeBuilt = new Farm();
			for(EconomicBuilding b : givenCity.getEconomicalBuildings()){
				if(b instanceof Farm)
					uniqueBuilding = false;
			}
		} else if (type.equals("Market")) {
			toBeBuilt = new Market();
			for(EconomicBuilding b : givenCity.getEconomicalBuildings()){
				if(b instanceof Market)
					uniqueBuilding = false;
			}
		}
		if(uniqueBuilding){
		if (toBeBuilt.getCost() > this.treasury) {
			throw new NotEnoughGoldException();
		}
		toBeBuilt.setCoolDown(true);
		if (military)
			givenCity.getMilitaryBuildings().add((MilitaryBuilding) toBeBuilt);
		else
			givenCity.getEconomicalBuildings()
					.add((EconomicBuilding) toBeBuilt);
		this.treasury -= toBeBuilt.getCost();
		}
	}

	public void upgradeBuilding(Building b) throws NotEnoughGoldException,
			BuildingInCoolDownException, MaxLevelException {
		if (this.treasury < b.getUpgradeCost())
			throw new NotEnoughGoldException();
		int upgradeCost = b.getUpgradeCost();
		b.upgrade();
		this.treasury -= upgradeCost;
		
		
	}
	
	public void initiateArmy(City city, Unit unit){
		Army newArmy = new Army(city.getName());
		newArmy.getUnits().add(unit);
		city.getDefendingArmy().getUnits().remove(unit);
		unit.setParentArmy(newArmy);
		this.controlledArmies.add(newArmy);
	}
	
	public void laySiege(Army army,City city) throws TargetNotReachedException,
	FriendlyCityException{
		if(army.getDistancetoTarget()>0)
			throw new TargetNotReachedException();
		for(City c : this.controlledCities){
			if(city.getName().equals(c.getName()))
				throw new FriendlyCityException();
		}
		army.setCurrentStatus(Status.BESIEGING);
		army.setCurrentLocation(army.getTarget());
		city.setUnderSiege(true);
		city.setTurnsUnderSiege(0);
	}
	public double getTreasury() {
		return treasury;
	}

	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}

	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}
}
