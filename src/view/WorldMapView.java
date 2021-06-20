package view;

import javax.swing.*;
import java.awt.*;


public class WorldMapView extends JFrame {
	
	private JTextArea PlayerInfo;
	private JLabel backgroundLabel;
	
	public WorldMapView() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setTitle("Empire Building");
		this.setLocationRelativeTo(null);
		
		
		JLayeredPane mainContainer = new JLayeredPane();
		
		backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0,0, this.getWidth(), this.getHeight());
		backgroundLabel.setIcon(new ImageIcon("images/WorldMap.png"));
		mainContainer.add(backgroundLabel);
		
		
		PlayerInfo = new JTextArea();
		PlayerInfo.setPreferredSize(new Dimension(this.getWidth(),25));
		PlayerInfo.setBounds(5, 5, this.getWidth()-5, 20);
		PlayerInfo.setOpaque(false);
		PlayerInfo.setEditable(false);
//		PlayerInfo.setText("");
		PlayerInfo.setFont(new Font("Serif", Font.BOLD, 23));
		PlayerInfo.setForeground(Color.BLACK);
		
		this.add(PlayerInfo);
		
		this.add(mainContainer);
		this.setVisible(true);
		
	}

	public JLabel getBackgroundLabel() {
		return backgroundLabel;
	}
	
	public static void main(String[]args) {
		new WorldMapView();
	}
}
