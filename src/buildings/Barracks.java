package buildings;

public class Barracks extends MilitaryBuilding {

	public Barracks(int cost, int upgradeCost, int recruitmentCost, int currentRecruit) {
		super(cost, upgradeCost, recruitmentCost, currentRecruit);
		cost = 2000;
		upgradeCost = 1000;
		recruitmentCost = 600;
	}
	
}
