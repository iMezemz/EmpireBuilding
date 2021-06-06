package units;

import java.io.IOException;

import exceptions.FriendlyFireException;

public class Infantry extends Unit {

	public Infantry(int level, int maxSoldierCount, double idleUpkeep,
			double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	
	public void attack(Unit target) throws FriendlyFireException{
		//ArrayList<String[]> Archers_Attack = ReadingCSVFile.readFile("archer_attack.csv");
		double factor =1;
		if (target instanceof Archer) {
			switch(this.getLevel()) {
			case 1:
				factor = 0.3;
				break;
			
			case 2:
				factor = 0.4;
				break;
				
			case 3:
				factor = 0.5;
				break;
				
			}}
		
		if (target instanceof Infantry) {
			switch(this.getLevel()) {
			case 1:
				factor = 0.1;
				break;
			
			case 2:
				factor = 0.2;
				break;
				
			case 3:
				factor = 0.3;
				break;
				
			}}
		if (target instanceof Cavalry) {
			switch(this.getLevel()) {
			case 1:
				factor = 0.1;
				break;
			
			case 2:
				factor = 0.2;
				break;
				
			case 3:
				factor = 0.25;
				break;
				
			}}
		
		int newSoldierCount = (int) Math.round( factor * this.getCurrentSoldierCount());
		target.setCurrentSoldierCount(newSoldierCount);
		
		
	}

}
