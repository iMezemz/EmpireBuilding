package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;


import buildings.EconomicBuilding;
import buildings.Farm;
import view.frames.MainGameFrame;

public class FarmPanel extends ImagePanel implements Pressable {
	
	ArrayList<JButton> allButtons;
	ArrayList<EconomicBuilding> EconomicalBuildingsArray;
	JButton upgradeButton, buyButton;
	JTextArea FarmInfo;


	public FarmPanel (String currentLocation , ArrayList<EconomicBuilding> EconomicalBuildingsArray) {
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
		backButton.setActionCommand("BacktoEconomicalbuildingspanel");
		this.add(backButton);
		allButtons.add(backButton);
		
		
		boolean hasFarm = false;
		EconomicBuilding ourFarm = null;
		
		FarmInfo = new JTextArea();
		FarmInfo.setFont(loadedFont);
		FarmInfo.setForeground(Color.WHITE);
		FarmInfo.setBackground(Color.DARK_GRAY);
		FarmInfo.setBounds(230,	350,300,70);
		FarmInfo.setVisible(true);
		
		
		for(EconomicBuilding x : EconomicalBuildingsArray) {
			if (x instanceof Farm) {
				hasFarm = true;
				ourFarm = x;
			}
		}
		if(hasFarm) {
		upgradeButton = new JButton("Upgrade");
		upgradeButton.setFont(loadedFont);
		upgradeButton.setBounds(335,440,100,50);
		upgradeButton.setActionCommand("upgradeunit");
		this.add(upgradeButton);
		allButtons.add(upgradeButton);
		
		
		FarmInfo.append("Level :" + ourFarm.getLevel() + "\n" +
				"Upgrade Cost :" + ourFarm.getUpgradeCost());
		this.add(FarmInfo);
		
		}
		else {
		buyButton = new JButton("Buy");
		buyButton.setFont(loadedFont);
		buyButton.setBounds(335,440,100,50);
		buyButton.setActionCommand("buyunit");
		this.add(buyButton);
		allButtons.add(buyButton);
		
		FarmInfo.append("Cost : 1000" );
		this.add(FarmInfo);
		
		}
		
		
		
		
		
	}

	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}
	public static void main(String[] args) {
		MainGameFrame frame = new MainGameFrame();
		ArrayList<EconomicBuilding> x = new ArrayList <EconomicBuilding>();	
		x.add(new Farm());
		frame.setmainPanel(new FarmPanel("Rome" , x  ));
	}
}
		

