package units;

import exceptions.FriendlyFireException;

public abstract class Unit {
	private int level;
	private int maxSoldierCount;
	private int currentSoldierCount;
	private double idleUpkeep;
	private double marchingUpkeep;
	private double siegeUpkeep;
	private Army parentArmy;

	public Unit(int level, int maxSoldierConunt, double idleUpkeep,
			double marchingUpkeep, double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierConunt;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
		this.currentSoldierCount = maxSoldierCount;
	}

	@Override
	public String toString() {
		return "Unit [level=" + level + ", maxSoldierCount=" + maxSoldierCount
				+ ", currentSoldierCount=" + currentSoldierCount
				+ ", idleUpkeep=" + idleUpkeep + ", marchingUpkeep="
				+ marchingUpkeep + ", siegeUpkeep=" + siegeUpkeep
				+ ", parentArmy=" + parentArmy + "]";
	}

	public void attack(Unit target) throws FriendlyFireException {
		if (target.getParentArmy().getCurrentLocation()
				.equals(this.getParentArmy().getCurrentLocation())) {
			throw new FriendlyFireException();
		}		
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		if (currentSoldierCount != other.currentSoldierCount)
			return false;
		if (Double.doubleToLongBits(idleUpkeep) != Double
				.doubleToLongBits(other.idleUpkeep))
			return false;
		if (level != other.level)
			return false;
		if (Double.doubleToLongBits(marchingUpkeep) != Double
				.doubleToLongBits(other.marchingUpkeep))
			return false;
		if (maxSoldierCount != other.maxSoldierCount)
			return false;
		if (Double.doubleToLongBits(siegeUpkeep) != Double
				.doubleToLongBits(other.siegeUpkeep))
			return false;
		return true;
	}

	public Army getParentArmy() {
		return parentArmy;
	}

	public void setParentArmy(Army parentArmy) {
		this.parentArmy = parentArmy;
	}

	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}

	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount = currentSoldierCount;
	}

	public int getLevel() {
		return level;
	}

	public int getMaxSoldierCount() {
		return maxSoldierCount;
	}

	public double getIdleUpkeep() {
		return idleUpkeep;
	}

	public double getMarchingUpkeep() {
		return marchingUpkeep;
	}

	public double getSiegeUpkeep() {
		return siegeUpkeep;
	}
}
