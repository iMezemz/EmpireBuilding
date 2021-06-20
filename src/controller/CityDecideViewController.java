package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import engine.Game;
import view.PlayerCityDecideView;


public class CityDecideViewController implements ActionListener {
	PlayerCityDecideView Gameview;
	Game model;
	String PlayerName;
	
	
	public CityDecideViewController(String PlayerName) {
		this.PlayerName = PlayerName;
		Gameview = new PlayerCityDecideView();
		Gameview.getConfirmButton().addActionListener(this);
		Gameview.getCitiesAvailableToPlayer().addActionListener(this);
	
		
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if(eventSource instanceof JComboBox) {
			String selectedCityValue = ((JComboBox)eventSource).getSelectedItem().toString(); 
			if(selectedCityValue.equals("Cairo")) {
				Gameview.getBackgroundLabel().setIcon(new ImageIcon("images/CairoArmy.png"));
			}
			else if (selectedCityValue.equals("Rome")) {
				Gameview.getBackgroundLabel().setIcon(new ImageIcon("images/RomeArmy.png"));
			}
			else if (selectedCityValue.equals("Sparta")) {
				Gameview.getBackgroundLabel().setIcon(new ImageIcon("images/SpartaArmy.png"));
			}
			else {
				Gameview.getBackgroundLabel().setIcon(new ImageIcon("images/WorldMap.png"));
				
			}
		}
		else {
		String PlayerCity = Gameview.getCitiesAvailableToPlayer().getSelectedItem().toString();
		if (PlayerCity != "") {
		Gameview.dispose();
//		new GameController(PlayerName , PlayerCity)
		}
		}
		
		}
	
public static void main(String[] args) {
	new CityDecideViewController("");
}
}
	

