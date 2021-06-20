package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
		
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String PlayerCity = Gameview.getCitiesAvailableToPlayer().getSelectedItem().toString();
		if (PlayerCity != "") {
		Gameview.dispose();
//		new GameController(PlayerName , PlayerCity)
		
		}
		
		}
	
public static void main(String[] args) {
	new CityDecideViewController("");
}
}
	

