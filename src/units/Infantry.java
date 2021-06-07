package units;

import java.util.ArrayList;

import exceptions.FriendlyFireException;

public class Infantry extends Unit {

	public Infantry(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierConunt, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	public void attack(Unit target) throws FriendlyFireException {
		super.attack(target);
		int level = this.getLevel();
		if (target instanceof Archer) {
			switch (level) {
			case 1:
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int) Math.floor(0.3 * this
						.getCurrentSoldierCount()));
				break;
			case 2:
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int) Math.floor(0.4 * this
						.getCurrentSoldierCount()));
				break;
			default:
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int) Math.floor(0.5 * this
						.getCurrentSoldierCount()));
				break;
			}
		} else if (target instanceof Infantry) {
			switch (level) {
			case 1:
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int) Math.floor(0.1 * this
						.getCurrentSoldierCount()));
				break;
			case 2:
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int) Math.floor(0.2 * this
						.getCurrentSoldierCount()));
				break;
			default:
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int) Math.floor(0.3 * this
						.getCurrentSoldierCount()));
				break;
			}
		} else if (target instanceof Cavalry) {
			switch (level) {
			case 1:
				target.setCurrentSoldierCount((int) Math.floor(target.getCurrentSoldierCount()-0.1 * this
						.getCurrentSoldierCount()));
				break;
			case 2:
				target.setCurrentSoldierCount((int) Math.floor(target.getCurrentSoldierCount()-0.2 * this
						.getCurrentSoldierCount()));
				break;
			default:
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int) Math.floor(0.25 * this
						.getCurrentSoldierCount()));
				break;
			}

		}
		if (target.getCurrentSoldierCount() <= 0) {
			target.setCurrentSoldierCount(0);
			ArrayList<Unit> targetParentArmy = target.getParentArmy().getUnits();
			for (int i = 0; i < targetParentArmy.size(); i++) {
				if(target.getParentArmy().getCurrentLocation() .equals( targetParentArmy.get(i).getParentArmy().getCurrentLocation()))
					targetParentArmy.remove(i);
			}
		}
		
	}
}
