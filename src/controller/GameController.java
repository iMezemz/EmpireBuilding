package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import engine.Game;
import view.frames.MainGameFrame;
import view.panels.WorldMapPanel;

public class GameController implements ActionListener , MouseListener{

	private MainGameFrame view;
	private Game model;
	private WorldMapPanel mapView;
	


	public GameController(String PlayerName, String PlayerCity) throws IOException  {
		this.model = new Game(PlayerName , PlayerCity);
		this.view = new MainGameFrame();
		
		view.getplayerInfo().append(model.getPlayer().toString() + "     Turns : "+ model.getCurrentTurnCount()+"/"+model.getMaxTurnCount() );
		
		
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[]args) throws IOException {
		new GameController("Hussein","Cairo");
	}
}
