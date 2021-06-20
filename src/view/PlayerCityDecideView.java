package view;


import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

@SuppressWarnings("serial")
public class PlayerCityDecideView extends JFrame {
	private JComboBox <String> citiesAvailableToPlayer;
	
	
	public PlayerCityDecideView(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setTitle("Choose your City");
		this.setLocationRelativeTo(null);
		
		JLayeredPane mainContainer = new JLayeredPane();
		
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		backgroundLabel.setIcon(new ImageIcon("images/"));
		mainContainer.add(backgroundLabel);
		
		
		
		String[] choices = {"", "Cairo","Rome", "Sparta"};
		citiesAvailableToPlayer = new JComboBox<String>(choices);
		citiesAvailableToPlayer.setBounds(450, 350, 250, 30);
		citiesAvailableToPlayer.setVisible(true);
		citiesAvailableToPlayer.setToolTipText("choose"); 
		this.add(citiesAvailableToPlayer);
		
		this.add(mainContainer);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new PlayerCityDecideView();
	}

}
