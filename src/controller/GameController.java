package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import engine.Game;
import view.MainGameFrame;

public class GameController implements ActionListener , MouseListener{

	private MainGameFrame MapView;
	private Game model;
	
	public GameController(String PlayerName, String PlayerCity) {
		MapView = new MainGameFrame();
		try {
			model = new Game(PlayerName , PlayerCity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MapView.dispose();
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[]args) {
		new GameController("Hussein","Cairo");
	}
}
