package view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;


public class MainGameFrame extends JFrame {
	
	private JTextArea PlayerInfo;
	private JLabel backgroundLabel;
	private JButton idleArmies;
	private JButton marchingArmies;
	private JButton besiegingArmies;
	private JTextArea InfoLabel;
	private JPanel MainPanel;
	
	public MainGameFrame() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setTitle("Empire Building");
		this.setLocationRelativeTo(null);
		
		MainPanel = new WorldMapPanel();
		this.add(MainPanel);
		
		
		PlayerInfo = new JTextArea();
		PlayerInfo.setPreferredSize(new Dimension(this.getWidth(),21));
//		PlayerInfo.setBounds(5, 5, this.getWidth()-5, 20);
		PlayerInfo.setOpaque(true);
		PlayerInfo.setEditable(false);
		PlayerInfo.setText("Hussein Ahmed");  
		PlayerInfo.setFont(new Font("Serif", Font.BOLD, 20));
		PlayerInfo.setForeground(new Color(235, 191, 138));
		PlayerInfo.setBackground(Color.BLACK);
		
		this.add(PlayerInfo, BorderLayout.NORTH);
		
//		this.add(mainContainer);
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
		new MainGameFrame();
	}
}
