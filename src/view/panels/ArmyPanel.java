package view.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import units.Army;
import units.Unit;

@SuppressWarnings("serial")
public class ArmyPanel extends JPanel {
	private ArrayList<JButton> units;
	private JButton initiateArmy, relocateUnit;

	public ArmyPanel(Army army) {
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(10.8f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		units = new ArrayList<JButton>();
		this.setLayout(new GridBagLayout());
		GridBagConstraints buttons = new GridBagConstraints();

		buttons.gridx = 0;
		buttons.gridy = 0;
		buttons.fill = GridBagConstraints.HORIZONTAL;
		for (Unit u : army.getUnits()) {
			JButton b = new JButton(u.toString());
			// b.setActionCommand(u.getParentArmy().getCurrentLocation());
			units.add(b);
			b.setFont(loadedFont);
			this.add(b, buttons);
			if (buttons.gridx == 1) {
				buttons.gridx = 0;
				buttons.gridy++;
			} else {
				buttons.gridx++;
			}

		}
		buttons.gridx = 0;
		buttons.gridy++;
		initiateArmy = new JButton("Initiate Army");
		initiateArmy.setActionCommand("Initiate Army");
		initiateArmy.setFont(loadedFont);
		this.add(initiateArmy, buttons);
		buttons.gridx++;
		relocateUnit = new JButton("Relocate Unit");
		relocateUnit.setActionCommand("Relocate Unit");
		relocateUnit.setFont(loadedFont);
		this.add(relocateUnit, buttons);
	}

	public JButton getInitiateArmy() {
		return initiateArmy;
	}

	public JButton getRelocateUnit() {
		return relocateUnit;
	}

	public ArrayList<JButton> getUnits() {
		return units;
	}

}
