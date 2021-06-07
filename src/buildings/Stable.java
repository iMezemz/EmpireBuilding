package buildings;

import units.Cavalry;
import units.Unit;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;

public class Stable extends MilitaryBuilding {

	public Stable() {
		super(2500, 1500, 600);

	}

	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		if (this.getLevel() == 2) {
			this.setUpgradeCost(2000);
			this.setRecruitmentCost(650);
		} else
			this.setRecruitmentCost(700);
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
			return new Cavalry(1, 40, 0.6, 0.7, 0.75);
		case 2:
			return new Cavalry(2, 40, 0.6, 0.7, 0.75);
		default:
			return new Cavalry(3, 60, 0.7, 0.8, 0.9);
		}

	}

}
