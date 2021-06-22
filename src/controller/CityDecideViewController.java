package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import engine.Game;
import view.CityDecideFrame;

public class CityDecideViewController implements ActionListener {
	CityDecideFrame Gameview;
	Game model;
	String PlayerName;

	public CityDecideViewController(String PlayerName) {
		this.PlayerName = PlayerName;
		Gameview = new CityDecideFrame();
		Gameview.getConfirmButton().addActionListener(this);
		Gameview.getCitiesAvailableToPlayer().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if (eventSource instanceof JComboBox) {
			String selectedCityValue = ((JComboBox) eventSource)
					.getSelectedItem().toString();
			if (selectedCityValue.equals("Cairo")) {
				Gameview.getBackgroundLabel().setIcon(
						new ImageIcon("images/CairoArmy.png"));
			} else if (selectedCityValue.equals("Rome")) {
				Gameview.getBackgroundLabel().setIcon(
						new ImageIcon("images/RomeArmy.png"));
			} else if (selectedCityValue.equals("Sparta")) {
				Gameview.getBackgroundLabel().setIcon(
						new ImageIcon("images/SpartaArmy.png"));
			} else {
				Gameview.getBackgroundLabel().setIcon(
						new ImageIcon("images/WorldMap.png"));

			}
		} else {
			String PlayerCity = Gameview.getCitiesAvailableToPlayer()
					.getSelectedItem().toString();
			if (PlayerCity != "Choose City") {
				Gameview.dispose();
				try {
					new GameController(PlayerName, PlayerCity);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		new CityDecideViewController("");
	}
}
