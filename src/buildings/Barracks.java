package buildings;

import java.io.IOException;
import java.util.ArrayList;

import units.Infantry;
import units.Unit;
import engine.ReadingCSVFile;
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
		ArrayList<String[]> Infantry = null;
		try {
			Infantry = ReadingCSVFile.readFile("Infantry.csv");
		} catch (IOException e) {
		}
		int level = getLevel();
		String[] InfantryInfo = Infantry.get(level - 1);
		this.setCurrentRecruit(this.getCurrentRecruit() + 1);
		return new Infantry(level, 
				Integer.parseInt(InfantryInfo[1]),
				Double.parseDouble(InfantryInfo[2]),
				Double.parseDouble(InfantryInfo[3]),
				Double.parseDouble(InfantryInfo[4]));

	}
}
