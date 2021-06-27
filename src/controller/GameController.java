package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxCapacityException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Archer;
import units.Army;
import units.Unit;
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
	private JButton selectedUnit;
	private Color defaultbgcolor;
	private Color defaultfgcolor;
	


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
	 private static Army askUser(ArrayList<Army> controlledArmies) {
		 Army[] choices = new Army[controlledArmies.size()];
		 for (int i = 0; i < choices.length; i++) {
			choices[i] = controlledArmies.get(i);
		}
	        Army s = (Army) JOptionPane.showInputDialog(
	                null,
	                "Choose Army to relocate selected unit to",
	                "Choose Army",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                choices,
	                choices[0]);
	        return s;
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
			try {
				model.getPlayer().build("ArcheryRange", "Cairo");
			} catch (NotEnoughGoldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.endTurn();
			try {
				model.getPlayer().recruitUnit("Archer","Cairo");
			} catch (BuildingInCoolDownException | MaxRecruitedException
					| NotEnoughGoldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedUnit = null;
			startView(new IdleArmiesPanel(model.getPlayer().getControlledArmies(), model.getAvailableCities(), model.getPlayer().getControlledCities()));
		}
		else if(typeOfButton.equals("Besieging Armies")|| typeOfButton.equals("BackToBESIEGING")){
			startView(new BesiegingArmiesPanel(model.getPlayer().getControlledArmies(),model.getAvailableCities()));
		}
		else if(typeOfButton.equals("GotoArmy")){
			PressableArmy currentView = (PressableArmy)view.getmainPanel();
			startView(currentView.getArmyPanels().get(currentView.getArmyButtons().indexOf(buttonClicked)));
		}
		else if(typeOfButton.equals("UnitButton")){
			if(selectedUnit == null){
				selectedUnit = buttonClicked;
				this.defaultbgcolor = buttonClicked.getBackground(); 
				this.defaultfgcolor = buttonClicked.getForeground(); 
				buttonClicked.setBackground(Color.DARK_GRAY);
				buttonClicked.setForeground(Color.WHITE);
			}
			else if(selectedUnit == buttonClicked){
				buttonClicked.setBackground(this.defaultbgcolor);
				buttonClicked.setForeground(this.defaultfgcolor);
				selectedUnit = null;
			}
			else{
				selectedUnit.setBackground(this.defaultbgcolor);
				selectedUnit.setForeground(this.defaultfgcolor);
				selectedUnit = buttonClicked;
				buttonClicked.setBackground(Color.DARK_GRAY);
				buttonClicked.setForeground(Color.WHITE);
			}	}
		else if(typeOfButton.equalsIgnoreCase("Initiatearmy")){
			if(selectedUnit != null){
				ArmyPanel a = (ArmyPanel)view.getmainPanel();
				Unit u = a.getUnitArray().get(a.getUnits().indexOf(selectedUnit));
				City unitCity = null;
				for(City c : model.getPlayer().getControlledCities()){
					if(u.getParentArmy().getCurrentLocation() == c.getName()){
						unitCity = c;
					}
				}
				model.getPlayer().initiateArmy(unitCity,u);
				selectedUnit = null;
				startView(new IdleArmiesPanel(model.getPlayer().getControlledArmies(),model.getAvailableCities(),model.getPlayer().getControlledCities()));
			}
		}
		else if(typeOfButton.equalsIgnoreCase("relocateunit")){
			if(selectedUnit!=null){
				ArmyPanel currentView = (ArmyPanel)view.getmainPanel();
				
				try {
					askUser(model.getPlayer().getControlledArmies()).relocateUnit(currentView.getUnitArray().get(currentView.getUnits().indexOf(selectedUnit)));
					selectedUnit = null;
					startView(new IdleArmiesPanel(model.getPlayer().getControlledArmies(),model.getAvailableCities(),model.getPlayer().getControlledCities()));
				} catch (MaxCapacityException e1) {
					JOptionPane.showMessageDialog(view, e1.getMessage() ,
				               "Error!", JOptionPane.ERROR_MESSAGE);
					selectedUnit.setBackground(this.defaultbgcolor);
					selectedUnit.setForeground(this.defaultfgcolor);
					selectedUnit = null;
				}
				catch(NullPointerException np){
					JOptionPane.showMessageDialog(view, "Please Try Again" ,
				               "Error!", JOptionPane.ERROR_MESSAGE);
					selectedUnit.setBackground(this.defaultbgcolor);
					selectedUnit.setForeground(this.defaultfgcolor);
					selectedUnit = null;
					
				} catch(ArrayIndexOutOfBoundsException e1){
					JOptionPane.showMessageDialog(view, "Must initiate a controlled army first" ,
				               "Error!", JOptionPane.ERROR_MESSAGE);
					selectedUnit.setBackground(this.defaultbgcolor);
					selectedUnit.setForeground(this.defaultfgcolor);
					selectedUnit = null;
				}
				
			}
		}
		else if(typeOfButton .equalsIgnoreCase("end turn")){
			model.endTurn();
			this.updatePlayerInfoBar();
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
