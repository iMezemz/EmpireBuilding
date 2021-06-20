package view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

@SuppressWarnings("serial")
public class MainGameWindow extends JFrame {
	private JTextField playerNameInput;
	private JButton startButton;

	public MainGameWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setResizable(true);
		this.setTitle("Empire Building");
		this.setLocationRelativeTo(null);

		JLayeredPane mainContainer = new JLayeredPane();
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		backgroundLabel.setIcon(new ImageIcon("images/MainBackground.gif"));
		mainContainer.add(backgroundLabel);
		
		
		startButton = new JButton();
		startButton.setOpaque(true);
		startButton.setContentAreaFilled(false);
		startButton.setIcon(new ImageIcon("images/startButton.png"));
		startButton.setBounds(460, 470, 250, 30);
		this.add(startButton);

		playerNameInput = new JTextField();
		playerNameInput.setBounds(460, 420, 250, 30);
		playerNameInput.setFont(new Font("Serif", Font.BOLD, 18));
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
		new MainGameWindow();
	}
}
