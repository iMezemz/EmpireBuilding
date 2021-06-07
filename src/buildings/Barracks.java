package buildings;

import units.Infantry;
import units.Unit;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;

public class Barracks extends MilitaryBuilding {

	public Barracks() {
		super(2000, 1000, 500);

	}

	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		if (this.getLevel() == 2) {
			this.setUpgradeCost(1500);
			this.setRecruitmentCost(550);
		} else
			this.setRecruitmentCost(600);
	}

	public Unit recruit() throws BuildingInCoolDownException,
			MaxRecruitedException {
		if (this.isCoolDown())
			throw new BuildingInCoolDownException();
		if (this.getCurrentRecruit() > 2)
			throw new MaxRecruitedException();
		this.setCurrentRecruit(getCurrentRecruit()+1);
		switch (this.getLevel()) {
		case 1:
			return new Infantry(1, 50, 0.5, 0.6, 0.7);
		case 2:
			return new Infantry(2, 50, 0.5, 0.6, 0.7);
		default:
			return new Infantry(3, 60, 0.6, 0.7, 0.8);
		}

	}
}
