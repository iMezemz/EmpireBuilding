package view.panels;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;


import view.frames.MainGameFrame;

@SuppressWarnings("serial")

public class MilitaryBuildingsPanel extends ImagePanel implements Pressable, CarriesCityName{
	
	JButton barracksButton , archeryRangeButton, stableButton ;
	ArrayList<JButton> allButtons;
	private String cityName;
	

	public String getCityName() {
		return cityName;
	}
	public MilitaryBuildingsPanel (String currentLocation) {
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
		
		barracksButton = new JButton("Barracks");
		barracksButton.setBounds(180,440,100,50);
		barracksButton.setFont(loadedFont);
		barracksButton.setActionCommand("gotobarracks");
		
		archeryRangeButton = new JButton("Archery Range");
		archeryRangeButton.setBounds(313,440,140,50);
		archeryRangeButton.setFont(loadedFont);
		archeryRangeButton.setActionCommand("gotoarcheryrange");
		
		stableButton = new JButton("Stable");
		stableButton.setBounds(480,440,100,50);
		stableButton.setFont(loadedFont);
		stableButton.setActionCommand("gotostable");
		
		JButton backButton = new JButton("Back");
		backButton.setFont(loadedFont);
		backButton.setBounds(337, 500, 100, 40);
		backButton.setActionCommand("Backtocityview");
		
		this.add(barracksButton);
		this.add(archeryRangeButton);
		this.add(stableButton);
		this.add(backButton);
		
		allButtons.add(backButton);
		allButtons.add(barracksButton);
		allButtons.add(archeryRangeButton);
		allButtons.add(stableButton);

		
 
	}
	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}
	public static void main(String[] args) {
		MainGameFrame frame = new MainGameFrame();
		frame.setmainPanel(new MilitaryBuildingsPanel("Cairo"));
	}
}