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
import buildings.Market;
import view.frames.MainGameFrame;

public class MarketPanel extends ImagePanel implements Pressable {
	
	ArrayList<JButton> allButtons;
	ArrayList<EconomicBuilding> EconomicalBuildingsArray;
	JButton upgradeButton, buyButton;
	JTextArea MarketInfo;


	public MarketPanel (String currentLocation , ArrayList<EconomicBuilding> EconomicalBuildingsArray) {
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
		
		
		boolean hasMarket = false;
		EconomicBuilding ourMarket = null;
		
		MarketInfo = new JTextArea();
		MarketInfo.setFont(loadedFont);
		MarketInfo.setForeground(Color.WHITE);
		MarketInfo.setBackground(Color.DARK_GRAY);
		MarketInfo.setBounds(230,	350,300,70);
		MarketInfo.setVisible(true);
		
		
		for(EconomicBuilding x : EconomicalBuildingsArray) {
			if (x instanceof Market) {
				hasMarket = true;
				ourMarket = x;
			}
		}
		if(hasMarket) {
		upgradeButton = new JButton("Upgrade");
		upgradeButton.setFont(loadedFont);
		upgradeButton.setBounds(335,440,100,50);
		upgradeButton.setActionCommand("upgradeunit");
		this.add(upgradeButton);
		allButtons.add(upgradeButton);
		
		
		MarketInfo.append("Level :" + ourMarket.getLevel() + "\n" +
				"Upgrade Cost :" + ourMarket.getUpgradeCost());
		this.add(MarketInfo);
		
		}
		else {
		buyButton = new JButton("Buy");
		buyButton.setFont(loadedFont);
		buyButton.setBounds(335,440,100,50);
		buyButton.setActionCommand("buyunit");
		this.add(buyButton);
		allButtons.add(buyButton);
		
		MarketInfo.append("Cost : 1500" );
		this.add(MarketInfo);
		
		}
		
		
		
		
		
	}

	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}
	public static void main(String[] args) {
		MainGameFrame frame = new MainGameFrame();
		ArrayList<EconomicBuilding> x = new ArrayList <EconomicBuilding>();	
		x.add(new Market());
		frame.setmainPanel(new MarketPanel("Cairo" , x  ));
	}
}
		

