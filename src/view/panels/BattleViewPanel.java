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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
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
	public BattleViewPanel(String img, ArrayList<Unit> defendingArmyUnits, ArrayList<Unit> attackingArmyUnits) {
		super(img);
		allButtons = new ArrayList<JButton>();
		defendingArmyButtons = new ArrayList<JButton>();
		attackingArmyButtons = new ArrayList<JButton>();

		backPanel = new JPanel();
		
		Font loadedFont = null;
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(12.3f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//log to output updates of the battle 
		log = new JTextField();
		log.setBounds(510, 70, 250, 450);
		log.setEditable(false);
		log.setFont(loadedFont);
		log.setForeground(Color.BLACK);
		log.setOpaque(false);
		this.add(log);
		
		
		for(Unit u: defendingArmyUnits) {
			JButton d = new JButton(u.toString());
			defendingArmyButtons.add(d);
		}
		
		for(Unit u: attackingArmyUnits) {
			JButton a = new JButton(u.toString());
			attackingArmyButtons.add(a);
		}
		
		

		
////////////////////////////////////////////////////ATTACKING ARMY/////////////////////////////////////////////////////////////////	
attackingArmyPanel = new JPanel();
attackingArmyPanel.setBounds(10, 290, 490, 250);
attackingArmyPanel.setOpaque(true);
attackingArmyPanel.setVisible(true);
attackingArmyPanel.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));


attackingArmyPanel.setLayout(new GridBagLayout());

buttons.insets = new Insets(0,0,0,0);
buttons.gridx = 0;
buttons.gridy = 0;
buttons.gridwidth = 1;
buttons.fill = GridBagConstraints.HORIZONTAL;
for (JButton b  : attackingArmyButtons) {
	
	
	allButtons.add(b);
	b.setFont(loadedFont);
	b.setActionCommand("UnitButton");
	this.add(b, buttons);
	if (buttons.gridx == 1) {
		buttons.gridx = 0;
		buttons.gridy++;
	} else {
		buttons.gridx++;
	}
	attackingArmyPanel.add(b,buttons );

}
this.add(attackingArmyPanel);

		
//////////////////////////////////////////////////////////ATTACK BUTTON///////////////////////////////////////////////////		
		attackButton = new JButton("Attack");
		attackButton.setOpaque(false);
		attackButton.setContentAreaFilled(true);
		attackButton.setFont(loadedFont);
		attackButton.setBackground(Color.white);
		attackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		attackButton.setBounds(210, 265,50, 20);
		attackButton.setOpaque(false);
		attackButton.setBorder(BorderFactory.createBevelBorder(1, Color.black, Color.red));

		this.add(attackButton);
		allButtons.add(attackButton);

////////////////////////////////////////////// DEFENDING ARMY////////////////////////////////////////////////////////////////
		defendingArmyPanel = new JPanel();
		defendingArmyPanel.setBounds(10, 10, 490, 250);
		defendingArmyPanel.setOpaque(true);
		defendingArmyPanel.setVisible(true);
		defendingArmyPanel.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));
		
		
		buttons.gridx = 0;
		buttons.gridy = 0;
		buttons.gridwidth = 1;
		buttons.fill = GridBagConstraints.HORIZONTAL;
		
		
		for (JButton b  : defendingArmyButtons) {
			
			
			allButtons.add(b);
			b.setFont(loadedFont);
			b.setActionCommand("UnitButton");
			this.add(b, buttons);
			if (buttons.gridx == 1) {
				buttons.gridx = 0;
				buttons.gridy++;
			} else {
				buttons.gridx++;
			}
			defendingArmyPanel.add(b,buttons );

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
		frame.setmainPanel(new BattleViewPanel("images/battleViewBackground.png", defendingArmy, attackingArmy));
		
	}



}
