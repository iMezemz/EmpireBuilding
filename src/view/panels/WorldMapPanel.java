package view.panels;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class WorldMapPanel extends ImagePanel {
	

	private JButton idleArmies;
	private JButton marchingArmies;
	private JButton besiegingArmies;
	private JButton cairoCity,romeCity,spartaCity;
	private ArrayList<JButton> allButtons;
	
	
	public WorldMapPanel() {
		
		super("images/GameplayMapBackground.png");
		allButtons = new ArrayList<JButton>();
		
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/mainFont.ttf")).deriveFont(12.3f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		idleArmies = new JButton("My Idle Armies");
		idleArmies.setFont(loadedFont);
		idleArmies.setCursor(new Cursor(Cursor.HAND_CURSOR));
		idleArmies.setBounds(120, 475, 180 ,50);
		idleArmies.setActionCommand("Idle Armies");
		
		marchingArmies = new JButton("My Marching Armies");
		marchingArmies.setFont(loadedFont);
		marchingArmies.setCursor(new Cursor(Cursor.HAND_CURSOR));
		marchingArmies.setBounds(320, 475, 180 ,50);
		marchingArmies.setActionCommand("Marching Armies");
		
		besiegingArmies = new JButton("My Besieging Armies");
		besiegingArmies.setFont(loadedFont);
		besiegingArmies.setCursor(new Cursor(Cursor.HAND_CURSOR));
		besiegingArmies.setBounds(520, 475, 180, 50);
		besiegingArmies.setActionCommand("Besieging Armies");
				
		cairoCity = new JButton();
		cairoCity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cairoCity.setBounds(632,322,100,30);
		cairoCity.setOpaque(false);
		cairoCity.setContentAreaFilled(false);
		cairoCity.setBorderPainted(false);
		cairoCity.setActionCommand("Cairo");

		
		spartaCity = new JButton();
		spartaCity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		spartaCity.setBounds(580,170,140,30);
		spartaCity.setOpaque(false);
		spartaCity.setContentAreaFilled(false);
		spartaCity.setBorderPainted(false);
		spartaCity.setActionCommand("Sparta");
		
		romeCity = new JButton();
		romeCity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		romeCity.setBounds(495,105,93,30);
		romeCity.setOpaque(false);
		romeCity.setContentAreaFilled(false);
		romeCity.setBorderPainted(false);
		romeCity.setActionCommand("Rome");

		allButtons.add(romeCity);
		allButtons.add(cairoCity);
		allButtons.add(spartaCity);
		allButtons.add(idleArmies);
		allButtons.add(marchingArmies);
		allButtons.add(besiegingArmies);
		
		
		
		

		JLabel mapViewLabel = new JLabel(new ImageIcon("images/MapView.png"));
		mapViewLabel.setBounds(20, 30, 100, 40);
		

		
//		this.add(initiateArmy);
//		this.add(relocateUnit);
		this.add(romeCity);
		this.add(spartaCity);
		this.add(cairoCity);
		this.add(mapViewLabel);
		this.add(idleArmies);
		this.add(marchingArmies);
		this.add(besiegingArmies);
		
		
	}
	public JButton getIdleArmies() {
		return idleArmies;
	}
	public void setIdleArmies(JButton idleArmies) {
		this.idleArmies = idleArmies;
	}
	public JButton getMarchingArmies() {
		return marchingArmies;
	}
	public void setMarchingArmies(JButton marchingArmies) {
		this.marchingArmies = marchingArmies;
	}
	public JButton getBesiegingArmies() {
		return besiegingArmies;
	}
	public void setBesiegingArmies(JButton besiegingArmies) {
		this.besiegingArmies = besiegingArmies;
	}
	public JButton getCairoCity() {
		return cairoCity;
	}
	public void setCairoCity(JButton cairoCity) {
		this.cairoCity = cairoCity;
	}
	public JButton getRomeCity() {
		return romeCity;
	}
	public void setRomeCity(JButton romeCity) {
		this.romeCity = romeCity;
	}
	public JButton getSpartaCity() {
		return spartaCity;
	}
	public void setSpartaCity(JButton spartaCity) {
		this.spartaCity = spartaCity;
	}

	
}
