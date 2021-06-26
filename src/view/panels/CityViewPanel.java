package view.panels;

import javax.swing.*;

import view.frames.MainGameFrame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")

public class CityViewPanel extends ImagePanel implements Pressable{
	
	JTextArea archeryRangeInfo, farmInfo, barracksInfo, marketInfo, stableInfo;
	JButton militaryBuildingsButton, economicalBuildingsButton, cityArmiesButton;
	ArrayList <JButton> allButtons;
	

	public CityViewPanel(String img) {
		super(img);
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
		backButton.setBounds(360, 500, 100, 40);
		backButton.setActionCommand("Backtomapview");
			
		

		militaryBuildingsButton = new JButton("Military Buildings");
		militaryBuildingsButton.setFont(loadedFont);
		militaryBuildingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		militaryBuildingsButton.setBounds(110, 435, 180, 50);
		militaryBuildingsButton.setActionCommand("militaryBuildingsButton");

		economicalBuildingsButton = new JButton("Economical Buildings");
		economicalBuildingsButton.setFont(loadedFont);
		economicalBuildingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		economicalBuildingsButton.setBounds(310, 435, 200, 50);
		economicalBuildingsButton.setActionCommand("Economical Buildings");
		

		cityArmiesButton = new JButton("City Armies");
		cityArmiesButton.setFont(loadedFont);
		cityArmiesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cityArmiesButton.setBounds(530, 435, 180, 50);
		cityArmiesButton.setActionCommand("City Armies");
		
		
		this.add(cityArmiesButton);
		this.add(economicalBuildingsButton);
		this.add(militaryBuildingsButton);
		this.add(backButton);
		
		allButtons.add(militaryBuildingsButton);
		allButtons.add(economicalBuildingsButton);
		allButtons.add(cityArmiesButton);
		allButtons.add(backButton);
		
		
	}

	public static void main(String[] args) {
		MainGameFrame frame = new MainGameFrame();
		frame.setmainPanel(new RomeViewPanel());
	}

	@Override
	public ArrayList<JButton> getAllButtons() {
		return this.allButtons;
	}

}
