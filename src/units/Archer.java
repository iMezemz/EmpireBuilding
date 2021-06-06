package units;

import java.math.*;
import java.io.IOException;
import java.util.ArrayList;

import engine.ReadingCSVFile;
import exceptions.FriendlyFireException;

public class Archer extends Unit {

	public Archer(int level, int maxSoldierCount, double idleUpkeep,
			double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	public void attack(Unit target) throws FriendlyFireException, IOException{
		//ArrayList<String[]> Archers_Attack = ReadingCSVFile.readFile("archer_attack.csv");
		double factor=1;
		if (target instanceof Archer) {
			Archer A = (Archer) target;
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
			Infantry I = (Infantry) target;
			switch(this.getLevel()) {
			case 1:
				factor = 0.2;
				break;
			
			case 2:
				factor = 0.3;
				break;
				
			case 3:
				factor = 0.4;
				break;
				
			}}
		if (target instanceof Cavalry) {
			Cavalry C = (Cavalry) target;
			switch(this.getLevel()) {
			case 1:
				factor = 0.1;
				break;
			
			case 2:
				factor = 0.1;
				break;
				
			case 3:
				factor = 0.2;
				break;
				
			}}
		
		int newSoldierCount = (int) Math.round( factor * this.getCurrentSoldierCount());
		target.setCurrentSoldierCount(newSoldierCount);
		
		
	}

}
