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

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.MilitaryBuilding;
import view.frames.MainGameFrame;

public class ArcheryRangePanel extends ImagePanel implements Pressable {
	
	ArrayList<JButton> allButtons;
	ArrayList<MilitaryBuilding> militaryBuildingsArray;
	JButton upgradeButton , recruitButton, buyButton;
	JTextArea archeryRangeInfo;


	public ArcheryRangePanel (String currentLocation , ArrayList<MilitaryBuilding> militaryBuildingsArray) {
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
		
		
		boolean hasArcheryRange = false;
		MilitaryBuilding ourArcheryRange = null;
		
		archeryRangeInfo = new JTextArea();
		archeryRangeInfo.setFont(loadedFont);
		archeryRangeInfo.setForeground(Color.WHITE);
		archeryRangeInfo.setBackground(Color.DARK_GRAY);
		archeryRangeInfo.setBounds(230,	350,300,70);
		archeryRangeInfo.setVisible(true);
		
		
		for(MilitaryBuilding x : militaryBuildingsArray) {
			if (x instanceof ArcheryRange) {
				hasArcheryRange = true;
				ourArcheryRange = x;
			}
		}
		if(hasArcheryRange) {
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
		
		archeryRangeInfo.append("Level :" + ourArcheryRange.getLevel() + "\n" +
				"Upgrade Cost :" + ourArcheryRange.getUpgradeCost() +  "\n" + 
						"Recruitment Cost :" + ourArcheryRange.getRecruitmentCost());
		this.add(archeryRangeInfo);
		
		}
		else {
		buyButton = new JButton("Buy");
		buyButton.setFont(loadedFont);
		buyButton.setBounds(335,440,100,50);
		buyButton.setActionCommand("buyunit");
		this.add(buyButton);
		allButtons.add(buyButton);
		
		archeryRangeInfo.append("Cost : 1500" );
		this.add(archeryRangeInfo);
		
		}
		
		
		
		
		
	}

	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}
	public static void main(String[] args) {
		MainGameFrame frame = new MainGameFrame();
		ArrayList<MilitaryBuilding> x = new ArrayList <MilitaryBuilding>();	
		x.add(new ArcheryRange());
		frame.setmainPanel(new ArcheryRangePanel("Sparta" , x  ));
	}
}
		

