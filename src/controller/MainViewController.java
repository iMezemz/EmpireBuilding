package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import view.StartFrame;

public class MainViewController implements ActionListener {
	StartFrame view;

	public MainViewController() {
		view = new StartFrame();
		try {
			this.playSound("sounds/soundtrack.wav");
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e) {
			e.printStackTrace();
		}
		view.getStartButton().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String playerName = view.getPlayerNameInput().getText();
		boolean validName = false;
		for (int i = 0; i < playerName.length(); i++) {
			if(playerName.charAt(i) != ' '){
				validName = true;
				break;
			}
		}
		if (validName) {
			view.dispose();
			new CityDecideViewController(playerName);
		}
	}

	public void playSound(String path) throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		AudioInputStream audioInputStream = AudioSystem
				.getAudioInputStream(new File(path).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public static void main(String[] args) {
		new MainViewController();
	}
}
