package units;
import java.util.*;

public class Army {
private Status currentStatus;
private ArrayList<Unit> units;
private int distanceToTarget;
private String target;
private String currentLocation;
private final int maxToHold;

public Army(String currentLocation) {
	this.currentStatus = Status.IDLE;
	this.units = new ArrayList<>();
	this.distanceToTarget = -1;
	this.target = "";
	this.maxToHold = 10;
	
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

public int getDistanceToTarget() {
	return distanceToTarget;
}

public void setDistanceToTarget(int distanceToTarget) {
	this.distanceToTarget = distanceToTarget;
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







}
