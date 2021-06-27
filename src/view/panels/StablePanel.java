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
import buildings.Stable;
import view.frames.MainGameFrame;

public class StablePanel extends ImagePanel implements Pressable {
	
	ArrayList<JButton> allButtons;
	ArrayList<MilitaryBuilding> militaryBuildingsArray;
	JButton upgradeButton , recruitButton, buyButton;
	JTextArea StableInfo;


	public StablePanel (String currentLocation , ArrayList<MilitaryBuilding> militaryBuildingsArray) {
		super("images/"+ currentLocation.toLowerCase() + "CityView.png");
		Font loadedFont = null;
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
		
		
		boolean hasStable = false;
		MilitaryBuilding ourStable = null;
		
		StableInfo = new JTextArea();
		StableInfo.setFont(loadedFont);
		StableInfo.setForeground(Color.WHITE);
		StableInfo.setBackground(Color.DARK_GRAY);
		StableInfo.setBounds(230,	350,300,70);
		StableInfo.setVisible(true);
		StableInfo.setEditable(false);
		
		
		for(MilitaryBuilding x : militaryBuildingsArray) {
			if (x instanceof Stable) {
				hasStable = true;
				ourStable = x;
			}
		}
		if(hasStable) {
		upgradeButton = new JButton("Upgrade");
		upgradeButton.setFont(loadedFont);
		upgradeButton.setBounds(180,440,100,50);
		upgradeButton.setActionCommand("upgradeunit");
		this.add(upgradeButton);
		allButtons.add(upgradeButton);
		
		recruitButton = new JButton("Recruit");
		recruitButton.setFont(loadedFont);
		recruitButton.setBounds(320,440,130,50);
		recruitButton.setActionCommand("recruitunit");
		this.add(recruitButton);
		allButtons.add(recruitButton);
		
		StableInfo.append("Level :" + ourStable.getLevel() + "\n" +
				"Upgrade Cost :" + ourStable.getUpgradeCost() + "\n" + 
						"Recruitment Cost :" + ourStable.getRecruitmentCost());
		this.add(StableInfo);
		
		}
		else {
		buyButton = new JButton("Buy");
		buyButton.setFont(loadedFont);
		buyButton.setBounds(335,440,100,50);
		buyButton.setActionCommand("buyunit");
		this.add(buyButton);
		allButtons.add(buyButton);
		
		StableInfo.append("Cost : 2500" );
		this.add(StableInfo);
		
		}
		
		
		
		
		
	}

	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}
	public static void main(String[] args) {
		MainGameFrame frame = new MainGameFrame();
		ArrayList<MilitaryBuilding> x = new ArrayList <MilitaryBuilding>();	
		x.add(new Stable());
		frame.setmainPanel(new StablePanel("Rome" , x  ));
	}
}
		

