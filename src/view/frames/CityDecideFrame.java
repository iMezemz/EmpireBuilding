package view.frames;


import javax.swing.*;




import java.awt.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class CityDecideFrame extends JFrame {
	private JComboBox <String> citiesAvailableToPlayer;
	private JButton confirmButton;
//	private String PlayerCity;
	private JLabel backgroundLabel ;
	
	public CityDecideFrame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setTitle("Choose your City");
		this.setLocationRelativeTo(null);
		
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/mainFont.ttf")).deriveFont(12.3f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLayeredPane mainContainer = new JLayeredPane();
		
		backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		backgroundLabel.setIcon(new ImageIcon("images/WorldMap.png"));
		mainContainer.add(backgroundLabel);
		
	
		
		confirmButton = new JButton();
		confirmButton.setText("Confirm");
		confirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		confirmButton.setBounds(360,380,120,40);
		confirmButton.setFont(loadedFont);
		this.add(confirmButton);
		
		
		
		
		String[] choices = {"Choose City", "Cairo","Rome", "Sparta"};
		citiesAvailableToPlayer = new JComboBox<String>(choices);
		citiesAvailableToPlayer.setBounds(300, 350, 250, 30);
		citiesAvailableToPlayer.setVisible(true);
		citiesAvailableToPlayer.setToolTipText("choose"); 
		citiesAvailableToPlayer.setFont(loadedFont);
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
		new CityDecideFrame();

	}






	public JComboBox<String> getCitiesAvailableToPlayer() {
		return citiesAvailableToPlayer;
	}

}
