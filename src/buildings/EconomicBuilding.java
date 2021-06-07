package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public abstract class EconomicBuilding extends Building {

	public EconomicBuilding(int cost, int upgradeCost) {
		super(cost, upgradeCost);
	}
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
		super.upgrade();
	}
	public abstract int harvest();
}
