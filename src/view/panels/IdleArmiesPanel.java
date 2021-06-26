package view.panels;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import controller.CityDecideViewController;

import java.util.ArrayList;

import units.Archer;
import units.Army;
import units.Status;
import units.Unit;
import view.frames.MainGameFrame;

@SuppressWarnings("serial")
public class IdleArmiesPanel extends ImagePanel{

	ArrayList<ArmyPanel> armyPanels;
	ArrayList<JButton> armyButtons;

	public IdleArmiesPanel(ArrayList<Army> armies)  {
		super("images/idleArmies.png");
		armyPanels = new ArrayList<ArmyPanel>();
		armyButtons = new ArrayList<JButton>();
		this.setLayout(new GridBagLayout());
		GridBagConstraints panelConstraint = new GridBagConstraints();
		panelConstraint.gridx = 0;
		panelConstraint.gridy = 1;
		panelConstraint.insets = new Insets(5, 5, 5, 5);
		panelConstraint.fill = GridBagConstraints.HORIZONTAL;
		JButton b = null;
		int cairoCount = 0;
		int spartaCount = 0;
		int romeCount = 0;
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(14f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Army a : armies) {
			if (a.getCurrentStatus() == Status.IDLE) {
				b = new JButton();
				if (a.getCurrentLocation().equals("Cairo")) {
					cairoCount++;
					b.setText(a.getCurrentLocation() + " " + cairoCount);
				} else if (a.getCurrentLocation().equals("Rome")) {
					romeCount++;
					b.setText(a.getCurrentLocation() + " " + romeCount);
				} else {
					spartaCount++;
					b.setText(a.getCurrentLocation() + " " + spartaCount);	
				}
				b.setFont(loadedFont);
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));
				armyButtons.add(b);
				this.add(b, panelConstraint);
				if (panelConstraint.gridx == 1) {
					panelConstraint.gridx = 0;
					panelConstraint.gridy++;
				} else {
					panelConstraint.gridx++;
				}
				armyPanels.add(new ArmyPanel(a,"Idle"));
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

	}


	public static void main(String[] args) {
		Army a = new Army("Cairo");
		Army b = new Army("Cairo");
		Army c = new Army("Cairo");
		Army d = new Army("Cairo");
		Army e = new Army("Cairo");
		Army f = new Army("Cairo");
		Army g = new Army("Cairo");
		Army h = new Army("Cairo");
		ArrayList<Unit> us = new ArrayList<Unit>();
		for (int i = 0; i < 10; i++) {
			us.add(new Archer(i, i + 1, i + 200, i + 21, 23));
		}
		a.setUnits(us);
		b.setUnits(us);
		c.setUnits(us);
		d.setUnits(us);
		e.setUnits(us);
		f.setUnits(us);
		g.setUnits(us);
		h.setUnits(us);
		ArrayList<Army> armies = new ArrayList<Army>();
		armies.add(a);
		armies.add(b);
		armies.add(c);
		armies.add(d);
		armies.add(e);
		armies.add(f);
		armies.add(g);
		armies.add(h);
//		IdleArmiesPanel p = new IdleArmiesPanel(armies);
		 ArmyPanel p = new ArmyPanel(a, a.getCurrentStatus().toString());
		MainGameFrame frame = new MainGameFrame();
		frame.setmainPanel(p);
		frame.revalidate();
		frame.repaint();
	}

}
