package buildings;

import java.io.IOException;
import java.util.ArrayList;

import units.Archer;
import units.Unit;
import engine.ReadingCSVFile;
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
		if(this.getCurrentRecruit() > 2)
			throw new MaxRecruitedException();	
		ArrayList<String[]> Archers = null;
		try {
			Archers = ReadingCSVFile.readFile("Archer.csv");
		} catch (IOException e) {}
		int level = getLevel();
		String[] ArcherInfo = Archers.get(level - 1);
		this.setCurrentRecruit(this.getCurrentRecruit() + 1);	
		return new Archer(level, 
						Integer.parseInt(ArcherInfo[1]),
						Double.parseDouble(ArcherInfo[2]), 
						Double.parseDouble(ArcherInfo[3]), 
						Double.parseDouble(ArcherInfo[4]));
	
	}
	

}
