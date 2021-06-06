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
		
		this.units.add(unit);
		
		ArrayList<Unit> tempUnit = this.getUnits();
		tempUnit.remove(unit);
		this.setUnits(tempUnit);
		//unit.getParentArmy().setUnits((this.getUnits()).remove(unit));
		
		
	}
	



	public void handleAttackedUnit (Unit u) {
	if(u.getCurrentSoldierCount() == 0) {
		ArrayList<Unit> temp = this.getUnits();
		temp.remove(u);
		
		this.setUnits(temp);
	}
	
	
	}
	
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
