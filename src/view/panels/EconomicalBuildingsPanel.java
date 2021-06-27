package view.panels;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;


import view.frames.MainGameFrame;

@SuppressWarnings("serial")

public class EconomicalBuildingsPanel extends ImagePanel implements Pressable{
	
	JButton farmButton, marketButton ;
	ArrayList<JButton> allButtons;
	

	public EconomicalBuildingsPanel (String currentLocation) {
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
		
		farmButton = new JButton("Farm");
		farmButton.setBounds(180,440,100,50);
		farmButton.setFont(loadedFont);
		farmButton.setActionCommand("gotofarm");
		
		marketButton = new JButton("Market");
		marketButton.setBounds(320,440,130,50);
		marketButton.setFont(loadedFont);
		marketButton.setActionCommand("gotomarket");
		
		
		JButton backButton = new JButton("Back");
		backButton.setFont(loadedFont);
		backButton.setBounds(337, 500, 100, 40);
		backButton.setActionCommand("Backtocityview");
		
		this.add(farmButton);
		this.add(marketButton);
		this.add(backButton);
		
		allButtons.add(backButton);
		allButtons.add(farmButton);
		allButtons.add(marketButton);


		
 
	}
	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}
	public static void main(String[] args) {
		MainGameFrame frame = new MainGameFrame();
		frame.setmainPanel(new EconomicalBuildingsPanel("Sparta"));
	}
}
