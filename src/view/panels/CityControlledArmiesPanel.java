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

import units.Army;
import units.Status;
import engine.City;

@SuppressWarnings("serial")
public class CityControlledArmiesPanel extends ImagePanel implements PressableArmy,Pressable,CarriesCityName{

	ArrayList<CityArmyPanel> armyPanels;
	ArrayList<JButton> armyButtons;
	ArrayList<JButton> allButtons;
	String cityName;

	public CityControlledArmiesPanel(String currentLocation,ArrayList<Army> armies) {
		super("images/"+currentLocation.toLowerCase()+"CityView.png");
		this.cityName = currentLocation;
		armyPanels = new ArrayList<CityArmyPanel>();
		armyButtons = new ArrayList<JButton>();
		this.setLayout(new GridBagLayout());
		GridBagConstraints panelConstraint = new GridBagConstraints();
		panelConstraint.gridx = 0;
		panelConstraint.gridy = 1;
		panelConstraint.insets = new Insets(5, 5, 5, 5);
		panelConstraint.fill = GridBagConstraints.HORIZONTAL;
		JButton b = null;
		int armyCount = 0;
		
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(14f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Army a : armies) {
			if (a.getCurrentStatus() == Status.IDLE && a.getCurrentLocation().equalsIgnoreCase(currentLocation)) {
				b = new JButton();
				armyCount++;
				b.setText(a.getCurrentLocation() + " " + armyCount);
				
				b.setFont(loadedFont);
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));
				armyButtons.add(b);
				b.setActionCommand("GotoArmy");
				this.add(b, panelConstraint);
				if (panelConstraint.gridx == 1) {
					panelConstraint.gridx = 0;
					panelConstraint.gridy++;
				} else {
					panelConstraint.gridx++;
				}
				armyPanels.add(new CityArmyPanel(a, currentLocation ,false));
			}
		}
//		for (City c : controlledCities) {
//			Army a = c.getDefendingArmy();
//			if (a.getCurrentStatus() == Status.IDLE) {
//				b = new JButton();
//				b.setText(a.getCurrentLocation()+ " Defending Army");
//				b.setFont(loadedFont);
//				b.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				armyButtons.add(b);
//				b.setActionCommand("GotoArmy");
//				this.add(b, panelConstraint);
//				if (panelConstraint.gridx == 1) {
//					panelConstraint.gridx = 0;
//					panelConstraint.gridy++;
//				} else {
//					panelConstraint.gridx++;
//				}
//				armyPanels.add(new ArmyPanel(a, "Idle",true));
//			}
//		}
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("gotocityarmiespanel");
		panelConstraint.gridx = 0;
		panelConstraint.gridy++;
		panelConstraint.gridwidth = 2;
		panelConstraint.fill = GridBagConstraints.HORIZONTAL;
		panelConstraint.insets = new Insets(35, 35, 35, 35);
		backButton.setFont(loadedFont);
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(backButton, panelConstraint);
		allButtons = new ArrayList<JButton>();
		allButtons.addAll(armyButtons);
		allButtons.add(backButton);

	}

	public String getCityName() {
		return cityName;
	}

	public ArrayList<CityArmyPanel> getCityArmyPanels() {
		return armyPanels;
	}
	public ArrayList<ArmyPanel> getArmyPanels(){
		return null;
	}

	public ArrayList<JButton> getArmyButtons() {
		return armyButtons;
	}

	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}
	}
