package view.panels;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

import view.frames.MainGameFrame;

public class CityArmiesPanel extends ImagePanel implements Pressable , CarriesCityName{
	
	public String getCityName() {
		return cityName;
	}
	private ArrayList<JButton> allButtons;
	private String cityName;
	

	public CityArmiesPanel (String currentLocation) {
		super("images/"+ currentLocation.toLowerCase() + "CityView.png");
		cityName = currentLocation;
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(12.3f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		allButtons = new ArrayList<JButton> ();
		
		JButton defendingArmy = new JButton("Defending Army");
		defendingArmy.setBounds(180,440,180,50);
		defendingArmy.setFont(loadedFont);
		defendingArmy.setActionCommand("gotodefendingarmy");
		
		JButton controlledCityArmiesButton = new JButton("Controlled armies in city");
		controlledCityArmiesButton.setBounds(400,440,250,50);
		controlledCityArmiesButton.setFont(loadedFont);
		controlledCityArmiesButton.setActionCommand("gotocontrolledarmies");
		
		
		JButton backButton = new JButton("Back");
		backButton.setFont(loadedFont);
		backButton.setBounds(337, 500, 100, 40);
		backButton.setActionCommand("backtocityview");
		
		this.add(defendingArmy);
		this.add(controlledCityArmiesButton);
		this.add(backButton);
		
		allButtons.add(backButton);
		allButtons.add(defendingArmy);
		allButtons.add(controlledCityArmiesButton);


		
 
	}
	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}
	
public static void main(String[] args) {
	MainGameFrame frame = new MainGameFrame();
	frame.setmainPanel(new CityArmiesPanel("Sparta"));
}}
