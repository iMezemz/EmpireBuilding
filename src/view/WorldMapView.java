package view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;


public class WorldMapView extends JFrame {
	
	private JTextArea PlayerInfo;
	private JLabel backgroundLabel;
	private JButton idleArmies;
	private JButton marchingArmies;
	private JButton besiegingArmies;
	private JTextArea InfoLabel;
	public WorldMapView() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setTitle("Empire Building");
		this.setLocationRelativeTo(null);
		
		
		JLayeredPane mainContainer = new JLayeredPane();
		
		backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0,0, this.getWidth(), this.getHeight());
		backgroundLabel.setIcon(new ImageIcon("images/GameplayMapBackground.png"));
		mainContainer.add(backgroundLabel);
		
		
		PlayerInfo = new JTextArea();
		PlayerInfo.setPreferredSize(new Dimension(this.getWidth(),25));
		PlayerInfo.setBounds(5, 5, this.getWidth()-5, 20);
		PlayerInfo.setOpaque(false);
		PlayerInfo.setEditable(false);
//		PlayerInfo.setText("");
		PlayerInfo.setFont(new Font("Serif", Font.BOLD, 23));
		PlayerInfo.setForeground(Color.BLACK);
		
		idleArmies = new JButton("My Idle Armies");
		idleArmies.setFont(new Font("Serif", Font.BOLD, 14));
		idleArmies.setBounds(120, 500, 160 ,50);
		marchingArmies = new JButton("My Marching Armies");
		marchingArmies.setFont(new Font("Serif", Font.BOLD, 14));
		marchingArmies.setBounds(320, 500, 160 ,50);
		besiegingArmies = new JButton("My Besieging Armies");
		besiegingArmies.setFont(new Font("Serif", Font.BOLD, 14));
		besiegingArmies.setBounds(520, 500, 160, 50);
		
		
		InfoLabel = new JTextArea();
		InfoLabel.setBounds(200, 190, 360, 280);
		InfoLabel.setBackground(new Color(235, 191, 138));
		
		InfoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		InfoLabel.setVisible(false);
		
		this.add(InfoLabel);
		this.add(idleArmies);
		this.add(marchingArmies);
		this.add(besiegingArmies);
		this.add(PlayerInfo);
		
		this.add(mainContainer);
		this.setVisible(true);
		
	}

	public JTextArea getPlayerInfo() {
		return PlayerInfo;
	}

	public JButton getIdleArmies() {
		return idleArmies;
	}

	public JButton getMarchingArmies() {
		return marchingArmies;
	}

	public JButton getBesiegingArmies() {
		return besiegingArmies;
	}

	public JTextArea getInfoLabel() {
		return InfoLabel;
	}

	public JLabel getBackgroundLabel() {
		return backgroundLabel;
	}
	
	public static void main(String[]args) {
		new WorldMapView();
	}
}
