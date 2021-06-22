package view;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class MainGameFrame extends JFrame {

	private JTextArea playerInfo;
	private ImagePanel mainPanel;

	public ImagePanel getmainPanel() {
		return mainPanel;
	}

	public void setmainPanel(ImagePanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public MainGameFrame() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setTitle("Empire Building");
		this.setLocationRelativeTo(null);
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/mainFont.ttf")).deriveFont(15f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		// Only for testing, MainPanel starts as null and is set by the controller
		mainPanel = new WorldMapPanel();
		this.add(mainPanel);
		//------------------------------------------------------------------------
		
		playerInfo = new JTextArea();
		playerInfo.setPreferredSize(new Dimension(this.getWidth(),20));
		playerInfo.setOpaque(true);
		playerInfo.setEditable(false);
//		playerInfo.setText(" Hussein Ahmed");  
		playerInfo.setFont(loadedFont);
		playerInfo.setForeground(new Color(235, 191, 138));
		playerInfo.setForeground(Color.WHITE);
		playerInfo.setBackground(new Color(70, 70, 70));

		
		this.add(playerInfo, BorderLayout.NORTH);
		
		this.setVisible(true);
		
	}

	public JTextArea getplayerInfo() {
		return playerInfo;
	}



	public static void main(String[] args) {
		new MainGameFrame();
	}
}
