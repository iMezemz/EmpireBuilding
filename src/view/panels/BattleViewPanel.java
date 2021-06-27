package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import units.Archer;
import units.Army;
import units.Unit;
import view.frames.MainGameFrame;



@SuppressWarnings("serial")
public class BattleViewPanel extends ImagePanel {
	
	private JButton attackingUnitButton, defendingUnitButton, attackButton;
	private JTextField log;
	private JPanel backPanel, defendingArmyPanel, attackingArmyPanel;
	private JLayeredPane mainContainer;
	private ArrayList<JButton> allButtons, defendingArmyButtons, attackingArmyButtons;
	GridBagConstraints buttons = new GridBagConstraints();
	
	
	//old loop as reference
	
//	this.setLayout(new GridBagLayout());
//	GridBagConstraints buttons = new GridBagConstraints();
//

//	buttons.insets = new Insets(0,0,0,0);
//	buttons.gridx = 0;
//	buttons.gridy = 1;
//	buttons.gridwidth = 1;
//	buttons.fill = GridBagConstraints.HORIZONTAL;
//	for (Unit u : army.getUnits()) {
//		JButton b = new JButton(u.toString());
//		units.add(b);
//		allButtons.add(b);
//		b.setFont(loadedFont);
//		b.setActionCommand("UnitButton");
//		this.add(b, buttons);
//		if (buttons.gridx == 1) {
//			buttons.gridx = 0;
//			buttons.gridy++;
//		} else {
//			buttons.gridx++;
//		}
//
//	}
//	
	public BattleViewPanel(ArrayList<Unit> defendingArmyUnits, ArrayList<Unit> attackingArmyUnits) {
		super("images/battleViewBackground.png");
		allButtons = new ArrayList<JButton>();
		defendingArmyButtons = new ArrayList<JButton>();
		attackingArmyButtons = new ArrayList<JButton>();

		backPanel = new JPanel();
		
		Font loadedFont = null;
		Font largerLoadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(8.4f);
			largerLoadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(15f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//log to output updates of the battle 
		log = new JTextField();
		log.setBounds(535, 70, 250, 450);
		log.setEditable(false);
		log.setFont(loadedFont);
		log.setForeground(Color.WHITE);
		log.setBackground(Color.DARK_GRAY);
		log.setOpaque(true);
		log.setBorder(BorderFactory.createEmptyBorder());
		this.add(log);
		JTextArea logLabel = new JTextArea("Battle Log");
		logLabel.setFont(largerLoadedFont);
		logLabel.setBounds(610,30,200,40);
		logLabel.setForeground(Color.WHITE);
		logLabel.setOpaque(false);
		logLabel.setEditable(false);
		this.add(logLabel);
		
		
		for(Unit u: defendingArmyUnits) {
			JButton d = new JButton(u.toString());
			defendingArmyButtons.add(d);
		}
		
		for(Unit u: attackingArmyUnits) {
			JButton a = new JButton(u.toString());
			attackingArmyButtons.add(a);
		}
		
		

		
////////////////////////////////////////////////////ATTACKING ARMY/////////////////////////////////////////////////////////////////	
		JTextArea attackingLabel = new JTextArea("Attacking Army");
		attackingLabel.setFont(largerLoadedFont);
		attackingLabel.setBounds(205,510,200,40);
		attackingLabel.setForeground(Color.WHITE);
		attackingLabel.setOpaque(false);
		attackingLabel.setEditable(false);
		this.add(attackingLabel);
		
attackingArmyPanel = new JPanel();
attackingArmyPanel.setBounds(10, 290, 520, 250);
attackingArmyPanel.setOpaque(false);
attackingArmyPanel.setVisible(true);
//attackingArmyPanel.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));
attackingArmyPanel.setLayout(new GridBagLayout());

buttons.insets = new Insets(5,5,5,5);
buttons.gridx = 0;
buttons.gridy = 0;
buttons.gridwidth = 1;
buttons.fill = GridBagConstraints.HORIZONTAL;
for (JButton b  : attackingArmyButtons) {
	
	
	allButtons.add(b);
	b.setFont(loadedFont);
	b.setActionCommand("UnitButton");
	attackingArmyPanel.add(b,buttons);
	if (buttons.gridx == 1) {
		buttons.gridx = 0;
		buttons.gridy++;
	} else {
		buttons.gridx++;
	}
	

}
this.add(attackingArmyPanel);

		
//////////////////////////////////////////////////////////ATTACK BUTTON///////////////////////////////////////////////////		
		attackButton = new JButton("Attack");
		
		attackButton.setFont(largerLoadedFont);
		attackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		attackButton.setBounds(217, 265,100, 40);
		this.add(attackButton);
		allButtons.add(attackButton);

////////////////////////////////////////////// DEFENDING ARMY////////////////////////////////////////////////////////////////
		JTextArea defendingLabel = new JTextArea("Defending Army");
		defendingLabel.setFont(largerLoadedFont);
		defendingLabel.setBounds(205,30,200,40);
		defendingLabel.setForeground(Color.WHITE);
		defendingLabel.setOpaque(false);
		defendingLabel.setEditable(false);
		
		this.add(defendingLabel);
		defendingArmyPanel = new JPanel();
		defendingArmyPanel.setBounds(10, 10, 520, 250);
		defendingArmyPanel.setOpaque(false);
		defendingArmyPanel.setVisible(true);
//		defendingArmyPanel.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));
		defendingArmyPanel.setLayout(new GridBagLayout());
		
		buttons.gridx = 0;
		buttons.gridy = 0;
		buttons.gridwidth = 1;
		buttons.fill = GridBagConstraints.HORIZONTAL;
		
		
		for (JButton b  : defendingArmyButtons) {
			
			allButtons.add(b);
			b.setFont(loadedFont);
			b.setActionCommand("UnitButton");
			defendingArmyPanel.add(b,buttons);
			if (buttons.gridx == 1) {
				buttons.gridx = 0;
				buttons.gridy++;
			} else {
				buttons.gridx++;
			}
			

		}
		this.add(defendingArmyPanel);

		
		
	}



	public JButton getAttackButton() {
		return attackButton;
	}

	public JTextField getLog() {
		return log;
	}




	public void setAttackButton(JButton attackButton) {
		this.attackButton = attackButton;
	}

	public void setLog(JTextField log) {
		this.log = log;
	}


	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}

	public void setAllButtons(ArrayList<JButton> allButtons) {
		this.allButtons = allButtons;
	}
	
	public static void main(String[] args) {
		Army a = new Army("Cairo");
		Army b = new Army("Cairo");
		Army c = new Army("Cairo");
		Army d = new Army("Cairo");
		Army e = new Army("Cairo");
		Army f = new Army("Cairo");
		Army g = new Army("Cairo");
		Army h = new Army("Cairo");
		ArrayList<Unit> us = new ArrayList<Unit>();
		for (int i = 0; i < 10; i++) {
			us.add(new Archer(i, i + 1, i + 200, i + 21, 23));
		}
		a.setUnits(us);
		b.setUnits(us);
		c.setUnits(us);
		d.setUnits(us);
		e.setUnits(us);
		f.setUnits(us);
		g.setUnits(us);
		h.setUnits(us);
		
		ArrayList<Unit> attackingArmy = a.getUnits();
		ArrayList<Unit> defendingArmy = b.getUnits();
		
		MainGameFrame frame = new MainGameFrame();
		frame.setmainPanel(new BattleViewPanel( defendingArmy, attackingArmy));
		
	}



}
