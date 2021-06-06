package engine;

import java.util.ArrayList;

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
import units.*;
import engine.City;

public class Player {
	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;

	public Player(String name) {
		this.name = name;
		controlledCities = new ArrayList<City>();
		controlledArmies = new ArrayList<Army>();
		treasury = 0.0;
		food = 0.0;
	}

	// New constructor written to initialise controlledCities array since the
	// variable is read-only
	// ControlledCities should only contain the playerCity at the start of the
	// game
	public void addBaseCity(String startingCity) {

		City c = new City(startingCity);
		c.setDefendingArmy(null);
		controlledCities.add(c);

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
	
	public void recruitUnit(String type,String cityName) throws
	BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {
		Unit toBeRecruited = null;
		for(City c : this.getControlledCities()) {
			if(cityName == c.getName()) {
				double recruitmentCost = 0;
				switch(type) {
				case "Archer": 
					for(MilitaryBuilding b : c.getMilitaryBuildings()) {
						if(b instanceof ArcheryRange) {
							toBeRecruited = b.recruit();							
							recruitmentCost = b.getRecruitmentCost();
						}
					}
					break;
				case "Infantry": 
					for(MilitaryBuilding b : c.getMilitaryBuildings()) {
						if(b instanceof Barracks) {
							toBeRecruited = b.recruit();
							recruitmentCost = b.getRecruitmentCost();
						}
					}
					break;
				case "Cavalry": 
					for(MilitaryBuilding b : c.getMilitaryBuildings()) {
						if(b instanceof Stable) {
							toBeRecruited = b.recruit();
							
							recruitmentCost = b.getRecruitmentCost();
						}
					}
					break;
					
				}
				if (recruitmentCost > treasury) {
					throw new NotEnoughGoldException() ;
				}
				treasury -= recruitmentCost;
				toBeRecruited.setParentArmy(c.getDefendingArmy());
				c.getDefendingArmy().getUnits().add(toBeRecruited);
				
			}
			}
	}	
	
	public void build(String type,String cityName) throws NotEnoughGoldException{
		for(City c : this.getControlledCities()) {
			if(cityName == c.getName()) {
				int BuildingCost = 0;
				Building newBuilding = null;
				switch(type) {
				
				case"Farm": newBuilding = new Farm();
							BuildingCost = newBuilding.getCost();
					break;
				case"Market": newBuilding = new Market();
							  BuildingCost = newBuilding.getCost();
					break;
				case"Stable": newBuilding = new Stable();
							BuildingCost = newBuilding.getCost();
					break;
				case"ArcheryRange":	newBuilding = new ArcheryRange();
									BuildingCost = newBuilding.getCost();
					break;
				case"Barracks": newBuilding = new Barracks();
								BuildingCost = newBuilding.getCost();
					break;
				}
				newBuilding.setCoolDown(true);
				if( newBuilding instanceof MilitaryBuilding) {
					c.getMilitaryBuildings().add((MilitaryBuilding) newBuilding);
				}
				else if(newBuilding instanceof EconomicBuilding) {
					c.getEconomicalBuildings().add((EconomicBuilding) newBuilding );
				}
				if (BuildingCost > treasury) {
					throw new NotEnoughGoldException() ;
				}
				treasury -= BuildingCost;
			}
		}
			}
	public void upgradeBuilding(Building b) throws NotEnoughGoldException,
	BuildingInCoolDownException, MaxLevelException {
		int Cost = b.getUpgradeCost();
		b.upgrade();
		if (Cost > treasury) {
			throw new NotEnoughGoldException() ;
		}
		treasury -= Cost;
	}
	public void initiateArmy(City city,Unit unit)  {
		Army AttackingArmy = new Army(city.getName());
		try {
			AttackingArmy.relocateUnit(unit);
		} catch (MaxCapacityException e) {
			
		}
		getControlledArmies().add(AttackingArmy);

	}
	public void laySiege(Army army,City city) throws TargetNotReachedException,
	FriendlyCityException{
		if(army.getDistancetoTarget()!=0) {
			throw new TargetNotReachedException();
		}
		if(controlledCities.contains(city)) {
			throw new FriendlyCityException();
		}
		
		army.setCurrentStatus(Status.BESIEGING);
		city.setUnderSiege(true);
		
	}
	}
			
	

		

	


