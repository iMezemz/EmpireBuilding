package buildings;

import units.Archer;
import units.Unit;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;

public class ArcheryRange extends MilitaryBuilding {

	public ArcheryRange() {
		super(1500, 800, 400);

	}
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		if (this.getLevel() == 2) {
			this.setUpgradeCost(700);
			this.setRecruitmentCost(450);
		} else
			this.setRecruitmentCost(500);
	}
	
	public Unit recruit() throws BuildingInCoolDownException,
			MaxRecruitedException {
		if(this.isCoolDown())
			throw new BuildingInCoolDownException();
		if(this.getCurrentRecruit()>2)
			throw new MaxRecruitedException();
		this.setCurrentRecruit(getCurrentRecruit()+1);
		switch(this.getLevel()){
		case 1 : 
			return new Archer(1,60,0.4,0.5,0.6);
		case 2 :
			return new Archer(2,60,0.4,0.5,0.6);
		default :
			return new Archer(3,70,0.5,0.6,0.7);
		}
		
	
	}

}
