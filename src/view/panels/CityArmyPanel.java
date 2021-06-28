package view.panels;

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
import units.Unit;
public class CityArmyPanel extends ImagePanel implements PressableUnit, CarriesCityName {
	public String getCityName() {
		return cityName;
	}

	private ArrayList<JButton> units;
	private JButton initiateArmy, relocateUnit;
	private ArrayList<JButton> allButtons;
	private ArrayList<Unit> unitArray;
	private String cityName;
	
	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}

	public CityArmyPanel(Army army,String currentLocation,boolean defending) {
		super("images/"+currentLocation.toLowerCase()+"CityView.png");
		this.cityName = currentLocation;
		allButtons = new ArrayList<JButton>();
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(10.8f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton backButton = new JButton("Back");
		backButton.setText("Back");
		backButton.setActionCommand("gotocityarmiespanel");
		backButton.setBounds(0,0,100,100);
		
		units = new ArrayList<JButton>();
		this.setLayout(new GridBagLayout());
		GridBagConstraints buttons = new GridBagConstraints();


		buttons.insets = new Insets(0,0,0,0);
		buttons.gridx = 0;
		buttons.gridy = 1;
		buttons.gridwidth = 1;
		buttons.fill = GridBagConstraints.HORIZONTAL;
		for (Unit u : army.getUnits()) {
			JButton b = new JButton(u.toString());
			units.add(b);
			allButtons.add(b);
			b.setFont(loadedFont);
			b.setActionCommand("UnitButton");
			this.add(b, buttons);
			if (buttons.gridx == 1) {
				buttons.gridx = 0;
				buttons.gridy++;
			} else {
				buttons.gridx++;
			}
		}
		if( defending ){
		buttons.gridx = 0;
		buttons.gridy++;
		initiateArmy = new JButton("Initiate Army");
		initiateArmy.setActionCommand("InitiateArmy");
		initiateArmy.setFont(loadedFont);
		allButtons.add(initiateArmy);
		this.add(initiateArmy, buttons);
		buttons.gridx++;
		relocateUnit = new JButton("Relocate Unit");
		relocateUnit.setActionCommand("RelocateUnit");
		relocateUnit.setFont(loadedFont);
		allButtons.add(relocateUnit);
		this.add(relocateUnit, buttons);
		unitArray = army.getUnits();
		}
		
		buttons.gridx = 0;
		buttons.gridy++;
		buttons.gridwidth = 2;
		buttons.fill = GridBagConstraints.HORIZONTAL;
		buttons.insets = new Insets(25,25,25,25);
		backButton.setFont(loadedFont);
		allButtons.add(backButton);
		this.add(backButton,buttons);
		

	}

	public ArrayList<JButton> getUnits() {
		return units;
	}

	public ArrayList<Unit> getUnitArray() {
		return unitArray;
	}}