package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class WorldMapPanel extends ImagePanel {
	
//	private JTextArea PlayerInfo;
	private JLabel backgroundLabel;
	private JButton idleArmies;
	private JButton marchingArmies;
	private JButton besiegingArmies;
	private JTextArea InfoLabel;
	private JButton MapViewButton;
	private JButton CityViewButton;
	
	public WorldMapPanel() {
		
		super("images/GameplayMapBackground.png");
		

//		PlayerInfo = new JTextArea();
//		PlayerInfo.setPreferredSize(new Dimension(this.getWidth(),25));
//		PlayerInfo.setBounds(5, 5, this.getWidth()-5, 20);
//		PlayerInfo.setOpaque(false);
//		PlayerInfo.setEditable(false);
//		PlayerInfo.setText(""); Player Information 
//		PlayerInfo.setFont(new Font("Serif", Font.BOLD, 23));
//		PlayerInfo.setForeground(Color.BLACK);
		
		idleArmies = new JButton("My Idle Armies");
		idleArmies.setFont(new Font("Serif", Font.BOLD, 14));
		idleArmies.setBounds(120, 485, 160 ,50);
		marchingArmies = new JButton("My Marching Armies");
		marchingArmies.setFont(new Font("Serif", Font.BOLD, 14));
		marchingArmies.setBounds(320, 485, 160 ,50);
		besiegingArmies = new JButton("My Besieging Armies");
		besiegingArmies.setFont(new Font("Serif", Font.BOLD, 14));
		besiegingArmies.setBounds(520, 485, 160, 50);
		
		
		InfoLabel = new JTextArea();
		InfoLabel.setBounds(200, 190, 360, 280);
		InfoLabel.setBackground(new Color(235, 191, 138));
		
		InfoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		InfoLabel.setVisible(false);
		
		MapViewButton = new JButton("Map View");
		MapViewButton.setBounds(20, 30, 100, 40);
		MapViewButton.setFont(new Font("Serif", Font.BOLD, 14));
		
		CityViewButton = new JButton("City View");
		CityViewButton.setBounds(20, 80, 100, 40);
		CityViewButton.setFont(new Font("Serif", Font.BOLD, 14));
		
		this.add(CityViewButton);
		this.add(MapViewButton);
		this.add(InfoLabel);
		this.add(idleArmies);
		this.add(marchingArmies);
		this.add(besiegingArmies);
//		this.add(PlayerInfo);
		
		
	}
	public static void main(String[] args) {
		WorldMapPanel p = new WorldMapPanel();
		
	
	}
}
