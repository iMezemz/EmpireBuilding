package units;

import java.util.ArrayList;
import units.Status;
import exceptions.MaxCapacityException;

/**
 * @author mohammad.hussein
 *
 */
public class Army{
	private Status currentStatus;
	private ArrayList<Unit> units;
	private int distancetoTarget;
	private String target;
	private String currentLocation;
	@SuppressWarnings("unused")
	private final int maxToHold=10;

	public Army(String currentLocation) {
		this.currentLocation=currentLocation;
		currentStatus=Status.IDLE;
		units=new ArrayList<Unit>();
		distancetoTarget=-1;
		target="";
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

	public int getDistancetoTarget() {
		return distancetoTarget;
	}

	public void setDistancetoTarget(int distancetoTarget) {
		this.distancetoTarget = distancetoTarget;
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
	public void relocateUnit(Unit unit) throws MaxCapacityException {
		if(units.size() == this.getMaxToHold())
			throw new MaxCapacityException();
		
		ArrayList<Unit> oldParentArmyUnits = unit.getParentArmy().getUnits();
		oldParentArmyUnits.remove(unit);
		unit.setParentArmy(this);
		this.units.add(unit);
	}
	public void handleAttackedUnit(Unit u){
		if(u.getCurrentSoldierCount() == 0){
			this.getUnits().remove(u);
		}
	}
	public double foodNeeded(){
		Double food = 0.0;
		
		for(Unit u : this.units){
			if(this.getCurrentStatus() == Status.BESIEGING)
			food += (u.getSiegeUpkeep())*u.getCurrentSoldierCount();
			else if(this.getCurrentStatus() == Status.IDLE)
			food += (u.getIdleUpkeep())*u.getCurrentSoldierCount();	
			else
			food += (u.getMarchingUpkeep())*u.getCurrentSoldierCount();
		}
		return food;
	}
	
}
