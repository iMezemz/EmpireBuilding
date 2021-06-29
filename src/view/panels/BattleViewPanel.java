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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import units.Archer;
import units.Army;
import units.Unit;
import view.frames.MainGameFrame;

@SuppressWarnings("serial")
public class BattleViewPanel extends ImagePanel implements Pressable {

	private JButton attackingUnitButton, defendingUnitButton, attackButton;
	private JTextArea log;
	private JPanel backPanel, defendingArmyPanel, attackingArmyPanel;
	private JLayeredPane mainContainer;
	private ArrayList<JButton> allButtons, defendingArmyButtons,attackingArmyButtons;
	private ArrayList<Unit> defendingUnits, attackingUnits;
	
	GridBagConstraints buttons = new GridBagConstraints();

	// old loop as reference

	// this.setLayout(new GridBagLayout());
	// GridBagConstraints buttons = new GridBagConstraints();
	//

	// buttons.insets = new Insets(0,0,0,0);
	// buttons.gridx = 0;
	// buttons.gridy = 1;
	// buttons.gridwidth = 1;
	// buttons.fill = GridBagConstraints.HORIZONTAL;
	// for (Unit u : army.getUnits()) {
	// JButton b = new JButton(u.toString());
	// units.add(b);
	// allButtons.add(b);
	// b.setFont(loadedFont);
	// b.setActionCommand("UnitButton");
	// this.add(b, buttons);
	// if (buttons.gridx == 1) {
	// buttons.gridx = 0;
	// buttons.gridy++;
	// } else {
	// buttons.gridx++;
	// }
	//
	// }
	//
	public BattleViewPanel(ArrayList<Unit> defendingArmyUnits,
			ArrayList<Unit> attackingArmyUnits) {
		super("images/battleViewBackground.png");
		this.attackingUnits = attackingArmyUnits;
		this.defendingUnits = defendingArmyUnits;
		allButtons = new ArrayList<JButton>();
		defendingArmyButtons = new ArrayList<JButton>();
		attackingArmyButtons = new ArrayList<JButton>();
		JTextArea resultsPanel = new JTextArea();
		resultsPanel.setEditable(false);
		resultsPanel.setBounds(290, 260, 250, 50);
		resultsPanel.setForeground(Color.WHITE);
		resultsPanel.setBackground(Color.DARK_GRAY);
		resultsPanel.setOpaque(false);
		Font loadedFont = null;
		Font largerLoadedFont = null;
		Font verylargeFont = null;
		JButton confirmAndGoBack = new JButton("Ok");

		confirmAndGoBack.setBounds(350, 320, 120, 40);
		try {
			loadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(8.4f);
			largerLoadedFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(15f);
			verylargeFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("fonts/mainFont.ttf")).deriveFont(20f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		confirmAndGoBack.setFont(largerLoadedFont);
		confirmAndGoBack.setActionCommand("backtomapview");
		resultsPanel.setFont(verylargeFont);
		if (defendingArmyUnits.size() == 0 && attackingArmyUnits.size() != 0) {
			resultsPanel.setText("You Won The Battle!");
			this.add(resultsPanel);
			this.add(confirmAndGoBack);
			allButtons.add(confirmAndGoBack);

		} else if (defendingArmyUnits.size() != 0
				&& attackingArmyUnits.size() == 0) {
			resultsPanel.setText("You Lost The Battle!");
			this.add(resultsPanel);
			this.add(confirmAndGoBack);
			allButtons.add(confirmAndGoBack);
		} else {
			backPanel = new JPanel();

			// log to output updates of the battle
			log = new JTextArea();
			log.setBounds(544, 70, 250, 420);
			log.setEditable(false);
			log.setFont(loadedFont);
			log.setForeground(Color.WHITE);
			log.setBackground(Color.DARK_GRAY);
			log.setOpaque(true);
			log.setBorder(BorderFactory.createEmptyBorder());
			log.setLineWrap(true);
			log.setWrapStyleWord(true);
			
			JScrollPane scroll = new JScrollPane(log);
			scroll.setBounds(544, 70, 250, 420);
			this.add(scroll);
			JTextArea logLabel = new JTextArea("Battle Log");
			logLabel.setFont(largerLoadedFont);
			logLabel.setBounds(610, 30, 200, 40);
			logLabel.setForeground(Color.WHITE);
			logLabel.setOpaque(false);
			logLabel.setEditable(false);
			this.add(logLabel);

			for (Unit u : defendingArmyUnits) {
				JButton d = new JButton(u.toString());
				defendingArmyButtons.add(d);
			}

			for (Unit u : attackingArmyUnits) {
				JButton a = new JButton(u.toString());
				attackingArmyButtons.add(a);
			}

			// //////////////////////////////////////////////////ATTACKING
			// ARMY/////////////////////////////////////////////////////////////////
			JTextArea attackingLabel = new JTextArea("Attacking Army");
			attackingLabel.setFont(largerLoadedFont);
			attackingLabel.setBounds(205, 510, 200, 40);
			attackingLabel.setForeground(Color.WHITE);
			attackingLabel.setOpaque(false);
			attackingLabel.setEditable(false);
			this.add(attackingLabel);

			attackingArmyPanel = new JPanel();
			attackingArmyPanel.setBounds(-15, 290, 580, 250);
			attackingArmyPanel.setOpaque(false);
			attackingArmyPanel.setVisible(true);
			// attackingArmyPanel.setBorder(BorderFactory.createBevelBorder(0,
			// Color.black, Color.black));
			attackingArmyPanel.setLayout(new GridBagLayout());

			buttons.insets = new Insets(0, 0, 0, 0);
			buttons.gridx = 0;
			buttons.gridy = 0;
			buttons.gridwidth = 1;
			buttons.fill = GridBagConstraints.HORIZONTAL;
			for (JButton b : attackingArmyButtons) {

				allButtons.add(b);
				b.setFont(loadedFont);
				b.setActionCommand("attackingunitbutton");
				attackingArmyPanel.add(b, buttons);
				if (buttons.gridx == 1) {
					buttons.gridx = 0;
					buttons.gridy++;
				} else {
					buttons.gridx++;
				}

			}
			this.add(attackingArmyPanel);

			// ////////////////////////////////////////////////////////ATTACK
			// BUTTON///////////////////////////////////////////////////
			attackButton = new JButton("Attack");

			attackButton.setFont(largerLoadedFont);
			attackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			attackButton.setBounds(217, 265, 100, 40);
			attackButton.setActionCommand("startattack");
			this.add(attackButton);
			allButtons.add(attackButton);

			// //////////////////////////////////////////// DEFENDING
			// ARMY////////////////////////////////////////////////////////////////
			JTextArea defendingLabel = new JTextArea("Defending Army");
			defendingLabel.setFont(largerLoadedFont);
			defendingLabel.setBounds(205, 30, 200, 40);
			defendingLabel.setForeground(Color.WHITE);
			defendingLabel.setOpaque(false);
			defendingLabel.setEditable(false);

			this.add(defendingLabel);
			defendingArmyPanel = new JPanel();
			defendingArmyPanel.setBounds(-15, 10, 580, 250);
			defendingArmyPanel.setOpaque(false);
			defendingArmyPanel.setVisible(true);
			// defendingArmyPanel.setBorder(BorderFactory.createBevelBorder(0,
			// Color.black, Color.black));
			defendingArmyPanel.setLayout(new GridBagLayout());

			buttons.gridx = 0;
			buttons.gridy = 0;
			buttons.gridwidth = 1;
			buttons.fill = GridBagConstraints.HORIZONTAL;

			for (JButton b : defendingArmyButtons) {

				allButtons.add(b);
				b.setFont(loadedFont);
				b.setActionCommand("defendingUnitButton");
				defendingArmyPanel.add(b, buttons);
				if (buttons.gridx == 1) {
					buttons.gridx = 0;
					buttons.gridy++;
				} else {
					buttons.gridx++;
				}

			}
			this.add(defendingArmyPanel);

		}

	}

	public ArrayList<JButton> getDefendingArmyButtons() {
		return defendingArmyButtons;
	}

	public ArrayList<JButton> getAttackingArmyButtons() {
		return attackingArmyButtons;
	}

	public ArrayList<Unit> getDefendingUnits() {
		return defendingUnits;
	}

	public ArrayList<Unit> getAttackingUnits() {
		return attackingUnits;
	}

	public JButton getAttackButton() {
		return attackButton;
	}

	public JTextArea getLog() {
		return log;
	}

	public void setAttackButton(JButton attackButton) {
		this.attackButton = attackButton;
	}

	public void setLog(JTextArea log) {
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

		ArrayList<Unit> defendingArmy = a.getUnits();
		ArrayList<Unit> attackingArmy = b.getUnits();

		MainGameFrame frame = new MainGameFrame();
		BattleViewPanel p = new BattleViewPanel(defendingArmy, attackingArmy);
		frame.setmainPanel(p);

	}

}
