package buildings;

public class Stable extends MilitaryBuilding{

	public Stable(int cost, int upgradeCost, int recruitmentCost, int currentRecruit) {
		super(cost, upgradeCost, recruitmentCost, currentRecruit);
		cost = 2500;
		upgradeCost = 1500;
		recruitmentCost = 600;
	}

}
