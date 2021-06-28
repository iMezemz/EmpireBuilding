package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import buildings.Barracks;
import buildings.MilitaryBuilding;
import view.frames.MainGameFrame;

public class BarracksPanel extends ImagePanel implements Pressable,CarriesCityName {
	
	public String getCityName() {
		return cityName;
	}
	ArrayList<JButton> allButtons;
	ArrayList<MilitaryBuilding> militaryBuildingsArray;
	JButton upgradeButton , recruitButton, buyButton;
	JTextArea barracksInfo;
	String cityName;


	public BarracksPanel (String currentLocation , ArrayList<MilitaryBuilding> militaryBuildingsArray) {
		super("images/"+ currentLocation.toLowerCase() + "CityView.png");
		Font loadedFont = null;
		this.cityName = currentLocation;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(12.3f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allButtons = new ArrayList<JButton> ();
	
		JButton backButton = new JButton("Back");
		backButton.setFont(loadedFont);
		backButton.setBounds(337, 500, 100, 40);
		backButton.setActionCommand("Backtomilitarybuildingspanel");
		this.add(backButton);
		allButtons.add(backButton);
		
		
		boolean hasBarracks = false;
		MilitaryBuilding ourBarracks = null;
		
		barracksInfo = new JTextArea();
		barracksInfo.setFont(loadedFont);
		barracksInfo.setForeground(Color.WHITE);
		barracksInfo.setBackground(Color.DARK_GRAY);
		barracksInfo.setBounds(230,	350,300,70);
		barracksInfo.setVisible(true);
		barracksInfo.setEditable(false);
		
		
		for(MilitaryBuilding x : militaryBuildingsArray) {
			if (x instanceof Barracks) {
				hasBarracks = true;
				ourBarracks = x;
			}
		}
		if(hasBarracks) {
		upgradeButton = new JButton("Upgrade");
		upgradeButton.setFont(loadedFont);
		upgradeButton.setBounds(180,440,100,50);
		upgradeButton.setActionCommand("upgradebarracks");
		this.add(upgradeButton);
		allButtons.add(upgradeButton);
		
		recruitButton = new JButton("Recruit");
		recruitButton.setFont(loadedFont);
		recruitButton.setBounds(320,440,130,50);
		recruitButton.setActionCommand("recruitunit");
		this.add(recruitButton);
		allButtons.add(recruitButton);
		
		barracksInfo.append("Level :" + ourBarracks.getLevel() + "\n" +
				"Upgrade Cost :" + ourBarracks.getUpgradeCost() + "\n" + 
				"Recruitment Cost :" + ourBarracks.getRecruitmentCost()) ;
		this.add(barracksInfo);
		
		}
		else {
		buyButton = new JButton("Buy");
		buyButton.setFont(loadedFont);
		buyButton.setBounds(335,440,100,50);
		buyButton.setActionCommand("buybarracks");
		this.add(buyButton);
		allButtons.add(buyButton);
		
		barracksInfo.append("Cost : 2000" );
		this.add(barracksInfo);
		
		}
		
		
		
		
		
	}

	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}
	public static void main(String[] args) {
		MainGameFrame frame = new MainGameFrame();
		ArrayList<MilitaryBuilding> x = new ArrayList <MilitaryBuilding>();	
		x.add(new Barracks());
		frame.setmainPanel(new BarracksPanel("Cairo" , x  ));
	}
}
		

