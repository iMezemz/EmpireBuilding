package view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class StartFrame extends JFrame {
	private JTextField playerNameInput;
	private JButton startButton;

	public StartFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(false);
		this.setTitle("Empire Building");
		this.setLocationRelativeTo(null);
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/mainFont.ttf")).deriveFont(14f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		JLayeredPane mainContainer = new JLayeredPane();
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		backgroundLabel.setIcon(new ImageIcon("images/MainBackground.gif"));
		mainContainer.add(backgroundLabel);
		
		
		startButton = new JButton();
		startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		startButton.setBorderPainted(false);
		startButton.setIcon(new ImageIcon("images/startButton.png"));
		startButton.setBounds(460, 470, 250, 30);
		this.add(startButton);

		playerNameInput = new JTextField();
		playerNameInput.setBounds(460, 420, 250, 30);
		playerNameInput.setFont(loadedFont);
		playerNameInput.setForeground(Color.BLACK);
		playerNameInput.setOpaque(false);
		this.add(playerNameInput);

		JLabel logoLabel = new JLabel();
		logoLabel.setIcon(new ImageIcon("images/EmpireLogo.png"));
		logoLabel.setBounds(170, 70, 500, 300);
		this.add(logoLabel);

		JLabel namePrompt = new JLabel();
		namePrompt.setIcon(new ImageIcon("images/namePrompt.png"));
		namePrompt.setBounds(140, 288, 500, 300);
		this.add(namePrompt);

		this.add(mainContainer);
		this.setVisible(true);
	}

	public JTextField getPlayerNameInput() {
		return playerNameInput;
	}

	public void setPlayerNameInput(JTextField playerNameInput) {
		this.playerNameInput = playerNameInput;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public static void main(String[] args) {
		new StartFrame();
	}
}
