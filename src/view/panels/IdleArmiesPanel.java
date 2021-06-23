package view.panels;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;

import units.Archer;
import units.Army;
import units.Status;
import units.Unit;
import view.frames.MainGameFrame;

@SuppressWarnings("serial")
public class IdleArmiesPanel extends ImagePanel{
	
	
	public IdleArmiesPanel(ArrayList<Army> armies) {
		super("images/idleArmies.png");
		this.setLayout(new GridLayout(0,1));
		for(Army currentArmy : armies){
			if(currentArmy.getCurrentStatus() == Status.IDLE){
				JPanel armyInfo = new JPanel(new GridLayout(1,4));
				armyInfo.setOpaque(false);
				armyInfo.add(new JTextArea(currentArmy.getCurrentLocation()));
				String s = "";
				for(Unit currentUnit : currentArmy.getUnits()){
					s+=currentUnit.toString()+"\n";
				}
				armyInfo.add(new JTextArea(s));
				JButton initArmy = new JButton("Initiate Army");
				initArmy.setContentAreaFilled(false);
				armyInfo.add(initArmy);
				armyInfo.add(new JButton("Relocate Unit"));
				this.add(armyInfo);
			}
		}
		
	}
	public static void main(String[] args) {
		Army a = new Army("Cairo");
		Army b = new Army("Sparta");
		ArrayList<Unit> us = new ArrayList<Unit>();
		for(int i = 0; i<10; i++){
			us.add(new Archer(i,i+1,i+200,i+21, 23));
		}
		a.setUnits(us);
		b.setUnits(us);
		ArrayList<Army> armies = new ArrayList<Army>();
		armies.add(a);
		armies.add(b);
		IdleArmiesPanel p = new IdleArmiesPanel(armies);
		MainGameFrame frame = new MainGameFrame();
		frame.setmainPanel(p);
	}

}
