package view.panels;

import javax.swing.*;

import view.frames.MainGameFrame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CityViewPanel extends ImagePanel {
	JButton militaryBuildings, economicalBuildings, defendingArmy;
	private ArrayList<JButton> allButtons;

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

		militaryBuildings = new JButton("Military Buildings");
		militaryBuildings.setFont(loadedFont);
		militaryBuildings.setCursor(new Cursor(Cursor.HAND_CURSOR));
		militaryBuildings.setBounds(110, 475, 180, 50);

		economicalBuildings = new JButton("Economical Buildings");
		economicalBuildings.setFont(loadedFont);
		economicalBuildings.setCursor(new Cursor(Cursor.HAND_CURSOR));
		economicalBuildings.setBounds(310, 475, 200, 50);

		defendingArmy = new JButton("Defending Army");
		defendingArmy.setFont(loadedFont);
		defendingArmy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		defendingArmy.setBounds(530, 475, 180, 50);
		
		this.add(defendingArmy);
		this.add(economicalBuildings);
		this.add(militaryBuildings);
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
