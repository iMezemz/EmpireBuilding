package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Archer;
import view.frames.MainGameFrame;
import view.panels.ArmyPanel;
import view.panels.BesiegingArmiesPanel;
import view.panels.CairoViewPanel;
import view.panels.IdleArmiesPanel;
import view.panels.ImagePanel;
import view.panels.MarchingArmiesPanel;
import view.panels.PressableArmy;
import view.panels.RomeViewPanel;
import view.panels.SpartaViewPanel;
import view.panels.WorldMapPanel;

public class GameController implements ActionListener , MouseListener{

	private MainGameFrame view;
	private Game model;
	private WorldMapPanel mapView;
	


	public GameController(String PlayerName, String PlayerCity) throws IOException  {
		this.model = new Game(PlayerName , PlayerCity);
		this.view = new MainGameFrame();
		this.bind(view.getmainPanel().getAllButtons());
		this.updatePlayerInfoBar();
	}
	
	private void updatePlayerInfoBar(){
		view.getplayerInfo().setText("");
		view.getplayerInfo().append(model.getPlayer().toString() + "     Turns : "+ model.getCurrentTurnCount()+"/"+model.getMaxTurnCount() );
		
	}
	private void bind(ArrayList<JButton> viewButtons){
		for(JButton b: viewButtons){
			b.addActionListener(this);
		}
		}
	private void startView(ImagePanel newPanel){
		view.setmainPanel(newPanel);
		this.bind(view.getmainPanel().getAllButtons());
		this.updatePlayerInfoBar();
	}
	public boolean checkControlledCity(String cityName){
		for(City c :model.getPlayer().getControlledCities()){
			if(c.getName().equals(cityName)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonClicked = (JButton)e.getSource();
		String typeOfButton = buttonClicked.getActionCommand();
		
		if(typeOfButton.equals("BackToMapView")){
			startView(new WorldMapPanel());
		}
		else if(typeOfButton.equals("Marching Armies")|| typeOfButton.equals("BackToMARCHING")){
			startView(new MarchingArmiesPanel(model.getPlayer().getControlledArmies()));
		}
		else if(typeOfButton.equals("Idle Armies") || typeOfButton.equals("BackToIDLE")){
			startView(new IdleArmiesPanel(model.getPlayer().getControlledArmies(), model.getAvailableCities(), model.getPlayer().getControlledCities()));
		}
		else if(typeOfButton.equals("Besieging Armies")|| typeOfButton.equals("BackToBESIEGING")){
			startView(new BesiegingArmiesPanel(model.getPlayer().getControlledArmies(),model.getAvailableCities()));
		}
		else if(typeOfButton.equals("GotoArmy")){
			PressableArmy currentView = (PressableArmy)view.getmainPanel();
			startView(currentView.getArmyPanels().get(currentView.getArmyButtons().indexOf(buttonClicked)));
		}
		else if(typeOfButton.equalsIgnoreCase("Cairo")){
			view.setmainPanel(new CairoViewPanel());
		}
		else if(typeOfButton.equalsIgnoreCase("Rome")){
			view.setmainPanel(new RomeViewPanel());
		}
		else if(typeOfButton.equalsIgnoreCase("Sparta")){
			view.setmainPanel(new SpartaViewPanel());
		}
		
		
		
		
		
	}
	
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[]args) throws IOException {
		new GameController("Hussein","Cairo");
	}
}
