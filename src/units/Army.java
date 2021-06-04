package units;

import java.util.*;

import exceptions.MaxCapacityException;

public class Army {
	private Status currentStatus;
	private ArrayList<Unit> units;
	private int distancetoTarget;
	private String target;
	private String currentLocation;
	private final int maxToHold;

	public Army(String currentLocation) {
		this.currentStatus = Status.IDLE;
		this.units = new ArrayList<Unit>();
		this.distancetoTarget = -1;
		this.target = "";
		this.currentLocation = currentLocation;
		this.maxToHold = 10;

	}
	
	
	public void relocateUnit(Unit unit) throws MaxCapacityException{
		if(this.getUnits().size() == this.getMaxToHold()) {
			throw new MaxCapacityException();
		}
		Army NewArmy = unit.getParentArmy();
		NewArmy.getUnits().remove(unit);
		unit.setParentArmy(NewArmy);
		this.units.add(unit);
		
	}
	
	public void handleAttackedUnit (Unit u) {
		for(int i = 0; i<units.size(); i++) {
		if(this.units.get(i) == u) {
			if(u.getCurrentSoldierCount() == 0) {
			this.units.remove(u);}
		}
	}}
	
	public double foodNeeded() {
		double totalFoodNeeded = 0;
		for(int i = 0; i<this.units.size(); i++) {
			totalFoodNeeded += (units.get(i).getIdleUpkeep()) * (units.get(i).getCurrentSoldierCount());
		}
		
		return totalFoodNeeded;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}


	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public int getMaxToHold() {
		return maxToHold;
	}

	public int getDistancetoTarget() {
		return distancetoTarget;
	}

	public void setDistancetoTarget(int distancetoTarget) {
		this.distancetoTarget = distancetoTarget;
	}

}
