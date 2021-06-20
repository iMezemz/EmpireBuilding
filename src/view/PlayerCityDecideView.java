package view;


import javax.swing.*;
import javax.swing.border.Border;


import java.awt.*;

@SuppressWarnings("serial")
public class PlayerCityDecideView extends JFrame {
	private JComboBox <String> citiesAvailableToPlayer;
	private JButton confirmButton;
	private String PlayerCity ;
	private JLabel backgroundLabel ;
	
	public PlayerCityDecideView(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setTitle("Choose your City");
		this.setLocationRelativeTo(null);
		
		JLayeredPane mainContainer = new JLayeredPane();
		
		backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		backgroundLabel.setIcon(new ImageIcon("images/WorldMap.png"));
		mainContainer.add(backgroundLabel);
		
	
		
		confirmButton = new JButton();
		confirmButton.setText("Confirm");
		confirmButton.setBounds(380,380,80,40);
		this.add(confirmButton);
		
		
		
		
		String[] choices = {"", "Cairo","Rome", "Sparta"};
		citiesAvailableToPlayer = new JComboBox<String>(choices);
		citiesAvailableToPlayer.setBounds(300, 350, 250, 30);
		citiesAvailableToPlayer.setVisible(true);
		citiesAvailableToPlayer.setToolTipText("choose"); 
		this.add(citiesAvailableToPlayer);
		
		this.add(mainContainer);
		this.setVisible(true);
		
		
		
	}
	


	


	public JLabel getBackgroundLabel() {
		return backgroundLabel;
	}






	public JButton getConfirmButton() {
		return confirmButton;
	}

	public static void main(String[] args) {
		new PlayerCityDecideView();

	}






	public JComboBox<String> getCitiesAvailableToPlayer() {
		return citiesAvailableToPlayer;
	}

}
