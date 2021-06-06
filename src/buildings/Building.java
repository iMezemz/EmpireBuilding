package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public abstract class Building {
	private int cost;
	private int level;
	private int upgradeCost;
	private boolean coolDown;

	public Building(int cost, int upgradeCost) {
		this.cost = cost;
		this.upgradeCost = upgradeCost;
		level = 1;
		coolDown = true;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	public boolean isCoolDown() {
		return coolDown;
	}

	public void setCoolDown(boolean coolDown) {
		this.coolDown = coolDown;
	}

	public int getCost() {
		return cost;
	}

	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		if (this.isCoolDown())
			throw new BuildingInCoolDownException();
		if (this.getLevel() > 2)
			throw new MaxLevelException();
		this.setLevel(this.getLevel() + 1);
		this.setCoolDown(true);
	}

}
