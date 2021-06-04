package buildings;

import java.io.IOException;
import java.util.ArrayList;

import units.Cavalry;
import units.Unit;
import engine.ReadingCSVFile;
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
		ArrayList<String[]> Cavalry = null;
		try {
			Cavalry = ReadingCSVFile.readFile("Cavalry.csv");
		} catch (IOException e) {
		}
		int level = getLevel();
		String[] CavalryInfo = Cavalry.get(level - 1);
		this.setCurrentRecruit(this.getCurrentRecruit() + 1);
		return new Cavalry(level, 
				Integer.parseInt(CavalryInfo[1]),
				Double.parseDouble(CavalryInfo[2]),
				Double.parseDouble(CavalryInfo[3]),
				Double.parseDouble(CavalryInfo[4]));

	}
}
