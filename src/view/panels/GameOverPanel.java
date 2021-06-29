package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextArea;

import view.frames.MainGameFrame;


public class GameOverPanel extends ImagePanel{
	
	JTextArea gameResult;
	JButton close;
	
	public GameOverPanel() throws FontFormatException {
		

		super("images/GameOver.jpg");
		gameResult= new JTextArea("Game Over  \n  ");
		Font loadedFont = null;
		Font loadedFont2 = null;
		
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(50f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		}
		
		try {
			loadedFont2 = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(15f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			
		}
		gameResult.setFont(loadedFont);
		gameResult.setBounds(240,260,320,200);
		gameResult.setOpaque(false);
		gameResult.setEditable(false);
		gameResult.setVisible(true);
		gameResult.setForeground(Color.WHITE);
		this.add(gameResult);
		
		close = new JButton ("Close");
		close.setBounds(340,500,100,60);
		close.setFont(loadedFont2);
		close.setVisible(true);
		close.setForeground(Color.BLACK);
		close.setActionCommand("dispose");
		this.add(close);
		
		
		
		
		
	}

	public JButton getClose() {
		return close;
	}

	public JTextArea getGameResult() {
		return gameResult;
	}

	public void setGameResult(JTextArea gameResult) {
		this.gameResult = gameResult;
	}

	@Override
	public ArrayList<JButton> getAllButtons() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main (String[]args)  {
		
		MainGameFrame x = new MainGameFrame();
		x.getContentPane().removeAll();
		try {
			x.setmainPanel(new GameOverPanel());
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	
	
		
	}
	

