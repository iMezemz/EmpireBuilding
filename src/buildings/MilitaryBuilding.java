package buildings;

public class MilitaryBuilding extends Building {
	
	private int recruitmentCost;
	private int currentRecruit; 
	private final int maxRecruit; 
	public MilitaryBuilding(int cost, int upgradeCost, int recruitmentCost , int currentRecruit) {
		super(cost, upgradeCost);
		maxRecruit = 3; 
		this.recruitmentCost = recruitmentCost;
		this.currentRecruit = currentRecruit;
		
	}
	public int getRecruitmentCost() {
		return recruitmentCost;
	}
	public void setRecruitmentCost(int recruitmentCost) {
		this.recruitmentCost = recruitmentCost;
	}
	public int getCurrentRecruit() {
		return currentRecruit;
	}
	public void setCurrentRecruit(int currentRecruit) {
		this.currentRecruit = currentRecruit;
	}
	public int getMaxRecruit() {
		return maxRecruit;
	}

}
