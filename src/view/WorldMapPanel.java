package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class WorldMapPanel extends ImagePanel {
	

	private JButton idleArmies;
	private JButton marchingArmies;
	private JButton besiegingArmies;
	private JButton cairoCity,romeCity,spartaCity,initiateArmy,relocateUnit;
	private JTextArea infoLabel;
//	private JLabel mapViewLabel;
//	private JButton cityViewButton;
 
	
	public WorldMapPanel() {
		
		super("images/GameplayMapBackground.png");
		
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
		
		marchingArmies = new JButton("My Marching Armies");
		marchingArmies.setFont(loadedFont);
		marchingArmies.setCursor(new Cursor(Cursor.HAND_CURSOR));
		marchingArmies.setBounds(320, 475, 180 ,50);
		
		besiegingArmies = new JButton("My Besieging Armies");
		besiegingArmies.setFont(loadedFont);
		besiegingArmies.setCursor(new Cursor(Cursor.HAND_CURSOR));
		besiegingArmies.setBounds(520, 475, 180, 50);
		
		cairoCity = new JButton();
		cairoCity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cairoCity.setBounds(632,322,100,30);
		cairoCity.setOpaque(false);
		cairoCity.setContentAreaFilled(false);
		cairoCity.setBorderPainted(false);
		
		spartaCity = new JButton();
		spartaCity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		spartaCity.setBounds(580,170,140,30);
		spartaCity.setOpaque(false);
		spartaCity.setContentAreaFilled(false);
		spartaCity.setBorderPainted(false);
		
		romeCity = new JButton();
		romeCity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		romeCity.setBounds(495,105,93,30);
		romeCity.setOpaque(false);
		romeCity.setContentAreaFilled(false);
		romeCity.setBorderPainted(false);
		
		initiateArmy = new JButton("Initiate Army");
		initiateArmy.setFont(loadedFont);
		initiateArmy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		initiateArmy.setBounds(20, 80, 140, 40);
		initiateArmy.setVisible(false);
		
		relocateUnit = new JButton("Relocate Unit");
		relocateUnit.setFont(loadedFont);
		relocateUnit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		relocateUnit.setBounds(20, 130, 140, 40);
		relocateUnit.setVisible(false);
		
		
		infoLabel = new JTextArea();
		infoLabel.setBounds(200, 170, 360, 280);
		infoLabel.setBackground(new Color(235, 191, 138));		
		infoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		infoLabel.setVisible(false);
		
		JLabel mapViewLabel = new JLabel(new ImageIcon("images/MapView.png"));
		mapViewLabel.setBounds(20, 30, 100, 40);
		
//		cityViewButton = new JButton("City View");
//		cityViewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		cityViewButton.setBounds(20, 80, 100, 40);
//		cityViewButton.setFont(loadedFont);
		
		this.add(initiateArmy);
		this.add(relocateUnit);
		this.add(romeCity);
		this.add(spartaCity);
		this.add(cairoCity);
//		this.add(cityViewButton);
		this.add(mapViewLabel);
		this.add(infoLabel);
		this.add(idleArmies);
		this.add(marchingArmies);
		this.add(besiegingArmies);
//		this.add(PlayerInfo);
		
		
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
	public JButton getInitiateArmy() {
		return initiateArmy;
	}
	public void setInitiateArmy(JButton initiateArmy) {
		this.initiateArmy = initiateArmy;
	}
	public JButton getRelocateUnit() {
		return relocateUnit;
	}
	public void setRelocateUnit(JButton relocateUnit) {
		this.relocateUnit = relocateUnit;
	}
	public JTextArea getInfoLabel() {
		return infoLabel;
	}
	public void setInfoLabel(JTextArea infoLabel) {
		this.infoLabel = infoLabel;
	}
	public static void main(String[] args) {
		WorldMapPanel p = new WorldMapPanel();
		
	
	}
}
