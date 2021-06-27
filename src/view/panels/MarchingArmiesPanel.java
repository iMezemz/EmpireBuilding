package view.panels;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

import engine.City;
import units.Archer;
import units.Army;
import units.Status;
import units.Unit;
import view.frames.MainGameFrame;

@SuppressWarnings("serial")
public class MarchingArmiesPanel extends ImagePanel implements PressableArmy{

	

		ArrayList<ArmyPanel> armyPanels;
		ArrayList<JButton> armyButtons;
		ArrayList<JButton> allButtons;

		public MarchingArmiesPanel(ArrayList<Army> armies)  {
			super("images/marchingArmies.png");
			armyPanels = new ArrayList<ArmyPanel>();
			armyButtons = new ArrayList<JButton>();
			this.setLayout(new GridBagLayout());
			GridBagConstraints panelConstraint = new GridBagConstraints();
			panelConstraint.gridx = 0;
			panelConstraint.gridy = 1;
			panelConstraint.insets = new Insets(5, 5, 5, 5);
			panelConstraint.fill = GridBagConstraints.HORIZONTAL;
			JButton b = null;
			Font loadedFont = null;
			try {
				loadedFont = Font.createFont(Font.TRUETYPE_FONT,
						new File("fonts/mainFont.ttf")).deriveFont(14f);
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (Army a : armies) {
				if (a.getCurrentStatus() == Status.MARCHING) {
					b = new JButton();
					b.setText("Currently Marching To "+a.getTarget()+"... Arriving in  "+a.getDistancetoTarget());
					b.setFont(loadedFont);
					b.setCursor(new Cursor(Cursor.HAND_CURSOR));
					b.setActionCommand("GotoArmy");
					armyButtons.add(b);
					this.add(b, panelConstraint);
					if (panelConstraint.gridx == 1) {
						panelConstraint.gridx = 0;
						panelConstraint.gridy++;
					} else {
						panelConstraint.gridx++;
					}
					armyPanels.add(new ArmyPanel(a,"Marching",false));
				}
				
			}
			
			JButton backButton = new JButton("Back");
			backButton.setActionCommand("BackToMapView");
			panelConstraint.gridx = 0;
			panelConstraint.gridy++;
			panelConstraint.gridwidth = 2;
			panelConstraint.fill = GridBagConstraints.HORIZONTAL;
			panelConstraint.insets = new Insets(35,35,35,35);
			backButton.setFont(loadedFont);
			backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.add(backButton,panelConstraint);
			allButtons = new ArrayList<JButton>();
			allButtons.addAll(armyButtons);
			allButtons.add(backButton);

		}
		public ArrayList<ArmyPanel> getArmyPanels() {
			return armyPanels;
		}
		public ArrayList<JButton> getArmyButtons() {
			return armyButtons;
		}
		public ArrayList<JButton> getAllButtons() {
			return allButtons;
		}
		public static void main(String[] args) {
			Army a = new Army("Cairo");
			a.setCurrentStatus(Status.IDLE);
			City c = new City("Cairo");
			c.setTurnsUnderSiege(2);
			ArrayList<City> cities = new ArrayList<City>();
			cities.add(c);
			Army b = new Army("Cairo");
			b.setCurrentStatus(Status.IDLE);
			Army n = new Army("Cairo");
//			Army d = new Army("Cairo");
//			Army e = new Army("Cairo");
//			Army f = new Army("Cairo");
//			Army g = new Army("Cairo");
//			Army h = new Army("Cairo");
			
			n.setCurrentStatus(Status.IDLE);
//			
//			d.setCurrentStatus(Status.BESIEGING);
//			e.setCurrentStatus(Status.BESIEGING);
//			f.setCurrentStatus(Status.BESIEGING);
//			g.setCurrentStatus(Status.BESIEGING);
			ArrayList<Unit> us = new ArrayList<Unit>();
			for (int i = 0; i < 10; i++) {
				us.add(new Archer(i, i + 1, i + 200, i + 21, 23));
			}
			a.setUnits(us);
			a.setTarget("Rome");
			a.setDistancetoTarget(2);
			b.setUnits(us);
			b.setTarget("Rome");
			b.setDistancetoTarget(2);
			n.setUnits(us);
			n.setTarget("Rome");
			n.setDistancetoTarget(3);
//			c.setUnits(us);
//			d.setUnits(us);
//			e.setUnits(us);
//			f.setUnits(us);
//			g.setUnits(us);
//			h.setUnits(us);
			ArrayList<Army> armies = new ArrayList<Army>();
			armies.add(a);
			armies.add(b);
			armies.add(n);
//			armies.add(c);
//			armies.add(d);
//			armies.add(e);
//			armies.add(f);
//			armies.add(g);
//			armies.add(h);
			MarchingArmiesPanel p = new MarchingArmiesPanel(armies);
//			 ArmyPanel p = new ArmyPanel(a,a.getCurrentStatus().toString());
			MainGameFrame frame = new MainGameFrame();
			frame.setmainPanel(p);
			frame.revalidate();
			frame.repaint();
		}

}
