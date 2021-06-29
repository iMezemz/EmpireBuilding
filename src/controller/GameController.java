package controller;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Archer;
import units.Army;
import units.Unit;
import view.frames.MainGameFrame;
import view.panels.ArcheryRangePanel;
import view.panels.ArmyPanel;
import view.panels.BattleViewPanel;
import view.panels.CarriesCityName;
import view.panels.BarracksPanel;
import view.panels.BesiegingArmiesPanel;
import view.panels.CairoViewPanel;
import view.panels.CityArmiesPanel;
import view.panels.CityArmyPanel;
import view.panels.CityControlledArmiesPanel;
import view.panels.CityViewPanel;
import view.panels.EconomicalBuildingsPanel;
import view.panels.FarmPanel;
import view.panels.GameOverPanel;
import view.panels.IdleArmiesPanel;
import view.panels.ImagePanel;
import view.panels.MarchingArmiesPanel;
import view.panels.MarketPanel;
import view.panels.MilitaryBuildingsPanel;
import view.panels.PressableArmy;
import view.panels.PressableUnit;
import view.panels.RomeViewPanel;
import view.panels.SpartaViewPanel;
import view.panels.StablePanel;
import view.panels.WorldMapPanel;

public class GameController implements ActionListener, MouseListener {

	private MainGameFrame view;
	private Game model;
	private JButton selectedUnit;
	private Color defaultbgcolor;
	private Color defaultfgcolor;
	private ArrayList<Army> armiesMarchingToTarget;
	private static String[] actions = { "Attack", "Auto Resolve", "Lay Seige" };
	private JButton selectedAttackerUnit, selectedDefenderUnit;
	private String runningLog;

	public GameController(String PlayerName, String PlayerCity)
			throws IOException {
		this.model = new Game(PlayerName, PlayerCity);
		this.view = new MainGameFrame();
		this.bind(view.getmainPanel().getAllButtons());
		this.updatePlayerInfoBar();
		armiesMarchingToTarget = new ArrayList<Army>();
	}

	private void updatePlayerInfoBar() {
		view.getplayerInfo().setText("");
		view.getplayerInfo().append(
				model.getPlayer().toString() + "     Turns : "
						+ model.getCurrentTurnCount() + "/"
						+ model.getMaxTurnCount());

	}

	private void bind(ArrayList<JButton> viewButtons) {
		for (JButton b : viewButtons) {
			b.addActionListener(this);
		}
	}

	private void startView(ImagePanel newPanel) {
		view.setmainPanel(newPanel);
		this.bind(view.getmainPanel().getAllButtons());
		this.updatePlayerInfoBar();
	}

	public boolean checkControlledCity(String cityName) {
		for (City c : model.getPlayer().getControlledCities()) {
			if (c.getName().equals(cityName)) {
				return true;
			}
		}
		return false;
	}

	private static String askUserForAction(String[] choices, String s) {
		String selected = (String) JOptionPane.showInputDialog(null, s,
				"Choose Action", JOptionPane.PLAIN_MESSAGE, null, choices,
				choices[0]);
		return selected;
	}

	private static Army askUserForArmy(ArrayList<Army> controlledArmies,
			String s) {
		Army[] choices = new Army[controlledArmies.size()];
		for (int i = 0; i < choices.length; i++) {
			choices[i] = controlledArmies.get(i);
		}
		Army selected = (Army) JOptionPane.showInputDialog(null, s,
				"Choose Army", JOptionPane.PLAIN_MESSAGE, null, choices,
				choices[0]);
		return selected;
	}

	private static City askUserForCityToConequer(ArrayList<City> filteredCities) {
		City[] choices = new City[filteredCities.size()];
		for (int i = 0; i < choices.length; i++) {
			choices[i] = filteredCities.get(i);
		}
		City s = (City) JOptionPane.showInputDialog(null,
				"Choose a city to conquer", "Choose City",
				JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
		return s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonClicked = (JButton) e.getSource();
		String typeOfButton = buttonClicked.getActionCommand();

		if (typeOfButton.equalsIgnoreCase("BackToMapView")) {
			startView(new WorldMapPanel());
		} else if (typeOfButton.equals("Marching Armies")
				|| typeOfButton.equals("BackToMARCHING")) {
			startView(new MarchingArmiesPanel(model.getPlayer()
					.getControlledArmies()));
		} else if (typeOfButton.equals("Idle Armies")
				|| typeOfButton.equals("BackToIDLE")) {
			selectedUnit = null;
			startView(new IdleArmiesPanel(model.getPlayer()
					.getControlledArmies(), model.getAvailableCities(), model
					.getPlayer().getControlledCities()));
		} else if (typeOfButton.equals("Besieging Armies")
				|| typeOfButton.equals("BackToBESIEGING")) {
			startView(new BesiegingArmiesPanel(model.getPlayer()
					.getControlledArmies(), model.getAvailableCities()));
		} else if (typeOfButton.equals("GotoArmy")) {
			PressableArmy currentView = (PressableArmy) view.getmainPanel();
			if (currentView instanceof IdleArmiesPanel
					|| currentView instanceof BesiegingArmiesPanel
					|| currentView instanceof MarchingArmiesPanel) {
				startView(currentView.getArmyPanels().get(
						currentView.getArmyButtons().indexOf(buttonClicked)));
			} else {
				CityControlledArmiesPanel current = (CityControlledArmiesPanel) currentView;
				startView(current.getCityArmyPanels().get(
						current.getArmyButtons().indexOf(buttonClicked)));
			}
		} else if (typeOfButton.equals("UnitButton")) {
			if (selectedUnit == null) {
				selectedUnit = buttonClicked;
				this.defaultbgcolor = buttonClicked.getBackground();
				this.defaultfgcolor = buttonClicked.getForeground();
				buttonClicked.setBackground(Color.DARK_GRAY);
				buttonClicked.setForeground(Color.WHITE);
			} else if (selectedUnit == buttonClicked) {
				buttonClicked.setBackground(this.defaultbgcolor);
				buttonClicked.setForeground(this.defaultfgcolor);
				selectedUnit = null;
			} else {
				selectedUnit.setBackground(this.defaultbgcolor);
				selectedUnit.setForeground(this.defaultfgcolor);
				selectedUnit = buttonClicked;
				buttonClicked.setBackground(Color.DARK_GRAY);
				buttonClicked.setForeground(Color.WHITE);
			}
		} else if (typeOfButton.equalsIgnoreCase("Initiatearmy")) {
			if (selectedUnit != null) {
				PressableUnit a = (PressableUnit) view.getmainPanel();

				Unit u = a.getUnitArray().get(
						a.getUnits().indexOf(selectedUnit));
				City unitCity = null;
				for (City c : model.getPlayer().getControlledCities()) {
					if (u.getParentArmy().getCurrentLocation() == c.getName()) {
						unitCity = c;
					}
				}
				Army previousArmy = u.getParentArmy();
				model.getPlayer().initiateArmy(unitCity, u);
				selectedUnit = null;
				if (a instanceof ArmyPanel) {
					startView(new ArmyPanel(previousArmy, "Idle", true));
				} else {
					startView(new CityArmyPanel(previousArmy,
							unitCity.getName(), true));
				}
			}
		} else if (typeOfButton.equalsIgnoreCase("relocateunit")) {
			if (selectedUnit != null) {
				PressableUnit currentView = (PressableUnit) view.getmainPanel();

				try {
					Unit unitToBeRelocated = currentView.getUnitArray().get(
							currentView.getUnits().indexOf(selectedUnit));
					Army previousArmy = unitToBeRelocated.getParentArmy();
					askUserForArmy(model.getPlayer().getControlledArmies(),
							"Select an army to relocate the selected unit to")
							.relocateUnit(unitToBeRelocated);
					selectedUnit = null;
					if (currentView instanceof ArmyPanel) {
						startView(new ArmyPanel(previousArmy, "Idle", true));
					} else {
						startView(new CityArmyPanel(previousArmy,
								previousArmy.getCurrentLocation(), true));
					}
				} catch (MaxCapacityException e1) {
					JOptionPane.showMessageDialog(view, e1.getMessage(),
							"Error!", JOptionPane.ERROR_MESSAGE);
					selectedUnit.setBackground(this.defaultbgcolor);
					selectedUnit.setForeground(this.defaultfgcolor);
					selectedUnit = null;
				} catch (NullPointerException np) {
					JOptionPane.showMessageDialog(view, "No army selected",
							"Error!", JOptionPane.ERROR_MESSAGE);
					selectedUnit.setBackground(this.defaultbgcolor);
					selectedUnit.setForeground(this.defaultfgcolor);
					selectedUnit = null;

				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(view,
							"Must initiate a controlled army first", "Error!",
							JOptionPane.ERROR_MESSAGE);
					selectedUnit.setBackground(this.defaultbgcolor);
					selectedUnit.setForeground(this.defaultfgcolor);
					selectedUnit = null;
				}

			}
		} else if (typeOfButton.equalsIgnoreCase("end turn")) {
			if (model.getCurrentTurnCount() == model.getMaxTurnCount() && model.getAvailableCities().size() 
					!= model.getPlayer().getControlledCities().size()) {
				GameOverPanel gameOverPanel = null;
				try {
					gameOverPanel = new GameOverPanel();
					gameOverPanel.getClose().addActionListener(this);
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameOverPanel.getGameResult().append("You Lost!");
				view.getContentPane().removeAll();
				view.setmainPanel(gameOverPanel);
			}
			
			ArrayList<City> targets = new ArrayList<City>();
			for (Army a : this.armiesMarchingToTarget) {
				for (City c : model.getAvailableCities()) {
					if (c.getName().equalsIgnoreCase(a.getTarget())) {
						targets.add(c);
					}
				}
			}
			Iterator<Army> iterControlledArmies = model.getPlayer()
					.getControlledArmies().iterator();
			while (iterControlledArmies.hasNext()) {
				Army army = iterControlledArmies.next();
				if (army.getUnits().size() == 0) {
					iterControlledArmies.remove();
				}
			}
			
			model.endTurn();
			this.updatePlayerInfoBar();

			int i = 0;
			Iterator<Army> iter = armiesMarchingToTarget.iterator();
			while (iter.hasNext()) {
				Army a = iter.next();
				if ((a.getDistancetoTarget()) == 0) {
					String action = askUserForAction(actions,
							"Choose an action to perform on the targeted city");
					if (action.equals("Attack")) {
						if (targets.get(i).getDefendingArmy().getUnits().size() == 0) {
							model.occupy(a, targets.get(i).getName());
							JOptionPane.showMessageDialog(view,
									"You successfully occupied the city ");
							if(model.getPlayer().getControlledArmies().size()== 
									model.getAvailableCities().size()) {
								GameOverPanel gameOverPanel = null;
								try {
									gameOverPanel = new GameOverPanel();
									gameOverPanel.getClose().addActionListener(this);
								} catch (FontFormatException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								gameOverPanel.getGameResult().append("You Won!");
								view.getContentPane().removeAll();
								view.setmainPanel(gameOverPanel);
							}
						} else {
							runningLog = "";
							Attack(a, targets.get(i).getDefendingArmy());
						}
					} else if (action.equalsIgnoreCase("Auto Resolve")) {
						try {
							model.autoResolve(a, targets.get(i)
									.getDefendingArmy());
							if (a.getUnits().size() == 0) {
								Iterator<Army> iterForRemove = model
										.getPlayer().getControlledArmies()
										.iterator();
								while (iterForRemove.hasNext()) {
									Army army = iterForRemove.next();
									if (army.getUnits().size() == 0) {
										iterForRemove.remove();
									}
								}
								JOptionPane.showMessageDialog(view,
										"You Lost The Battle");
							} else {
								JOptionPane.showMessageDialog(view,
										"You Won The Battle");
							}
						} catch (FriendlyFireException e1) {
							JOptionPane.showMessageDialog(view,
									e1.getMessage(), "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
					} else if (action.equalsIgnoreCase("Lay Seige")) {
						try {
							model.getPlayer().laySiege(a, targets.get(i));
						} catch (TargetNotReachedException
								| FriendlyCityException e1) {
							JOptionPane.showMessageDialog(view,
									e1.getMessage(), "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
					}
					iter.remove();
				}

				i++;

			}
			
			for(Army a : model.getPlayer().getControlledArmies()){
				if(a.getCurrentStatus().toString().equalsIgnoreCase("BESIEGING") && getCityByName(a.getCurrentLocation()).getTurnsUnderSiege() == 3){
					JOptionPane.showMessageDialog(view, "", "", arg3);
					Attack(a,getCityByName(a.getCurrentLocation()).getDefendingArmy());
				}
			}

		} else if (typeOfButton.equalsIgnoreCase("targetCity")) {
			Army toAttack = null;
			City c = null;
			try {
				ArrayList<Army> idleArmies = new ArrayList<Army>();
				for (Army a : model.getPlayer().getControlledArmies()) {
					if (a.getCurrentStatus().toString()
							.equalsIgnoreCase("idle") && !armiesMarchingToTarget.contains(a)) {
						idleArmies.add(a);
					}
				}
				toAttack = askUserForArmy(
						idleArmies,
						"Select an army to attack with (Armies are ordered in the same order as shown in view)");
				ArrayList<City> availableForAttackCities = (ArrayList<City>) model
						.getAvailableCities().clone();
				availableForAttackCities.removeAll(model.getPlayer()
						.getControlledCities());
				c = askUserForCityToConequer(availableForAttackCities);
				model.targetCity(toAttack, c.getName());
				this.updatePlayerInfoBar();
				armiesMarchingToTarget.add(toAttack);

			} catch (ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(view,
						"No initiated armies to attack with", "Error!",
						JOptionPane.ERROR_MESSAGE);
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(view,
						"Please select an army to attack with", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (typeOfButton.equalsIgnoreCase("Cairo")) {
			if (checkControlledCity("Cairo")) {

				this.startView(new CairoViewPanel());
			} else {
				JOptionPane.showMessageDialog(view, "Not a controlled city",
						"Error!", JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("Rome")) {
			if (checkControlledCity("Rome")) {

				this.startView(new RomeViewPanel());
			} else {
				JOptionPane.showMessageDialog(view, "Not a controlled city",
						"Error!", JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("Sparta")) {
			if (checkControlledCity("Sparta")) {

				this.startView(new SpartaViewPanel());
			} else {
				JOptionPane.showMessageDialog(view, "Not a controlled city",
						"Error!", JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("militarybuildingsbutton")) {
			this.startView(new MilitaryBuildingsPanel(((CityViewPanel) view
					.getmainPanel()).getCityName()));
		} else if (typeOfButton.equalsIgnoreCase("economicalbuildingsbutton")) {
			this.startView(new EconomicalBuildingsPanel(((CityViewPanel) view
					.getmainPanel()).getCityName()));
		} else if (typeOfButton.equalsIgnoreCase("Backtocityview")) {
			CarriesCityName p = (CarriesCityName) view.getmainPanel();
			this.startView(new CityViewPanel("images/"
					+ p.getCityName().toLowerCase() + "CityView.png", p
					.getCityName()));
		} else if (typeOfButton.equalsIgnoreCase("gotobarracks")) {
			CarriesCityName p = (CarriesCityName) view.getmainPanel();
			ArrayList<MilitaryBuilding> militaryBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(p.getCityName())) {
					militaryBuildings = c.getMilitaryBuildings();
				}
			}
			this.startView(new BarracksPanel(p.getCityName(), militaryBuildings));
		} else if (typeOfButton.equalsIgnoreCase("gotoarcheryrange")) {
			CarriesCityName p = (CarriesCityName) view.getmainPanel();
			ArrayList<MilitaryBuilding> militaryBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(p.getCityName())) {
					militaryBuildings = c.getMilitaryBuildings();
				}
			}
			this.startView(new ArcheryRangePanel(p.getCityName(),
					militaryBuildings));
		} else if (typeOfButton.equalsIgnoreCase("gotostable")) {
			CarriesCityName p = (CarriesCityName) view.getmainPanel();
			ArrayList<MilitaryBuilding> militaryBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(p.getCityName())) {
					militaryBuildings = c.getMilitaryBuildings();
				}
			}
			this.startView(new StablePanel(p.getCityName(), militaryBuildings));
		} else if (typeOfButton
				.equalsIgnoreCase("backtomilitarybuildingspanel")) {
			CarriesCityName c = (CarriesCityName) view.getmainPanel();
			this.startView(new MilitaryBuildingsPanel(c.getCityName()));
		} else if (typeOfButton.equalsIgnoreCase("gotofarm")) {
			CarriesCityName currentPanel = (CarriesCityName) view
					.getmainPanel();
			ArrayList<EconomicBuilding> myEconomicalBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(currentPanel.getCityName())) {
					myEconomicalBuildings = c.getEconomicalBuildings();
				}
			}
			this.startView(new FarmPanel(currentPanel.getCityName(),
					myEconomicalBuildings));
		} else if (typeOfButton.equalsIgnoreCase("gotomarket")) {
			CarriesCityName currentPanel = (CarriesCityName) view
					.getmainPanel();
			ArrayList<EconomicBuilding> myEconomicalBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(currentPanel.getCityName())) {
					myEconomicalBuildings = c.getEconomicalBuildings();
				}
			}
			this.startView(new MarketPanel(currentPanel.getCityName(),
					myEconomicalBuildings));
		} else if (typeOfButton
				.equalsIgnoreCase("backtoeconomicalbuildingspanel")) {
			CarriesCityName c = (CarriesCityName) view.getmainPanel();
			this.startView(new EconomicalBuildingsPanel(c.getCityName()));
		} else if (typeOfButton.equalsIgnoreCase("buyfarm")) {
			FarmPanel currentView = (FarmPanel) view.getmainPanel();
			ArrayList<EconomicBuilding> myEconomicBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
					myEconomicBuildings = c.getEconomicalBuildings();
				}
			}
			try {
				model.getPlayer().build("farm", currentView.getCityName());
				this.startView(new FarmPanel(currentView.getCityName(),
						myEconomicBuildings));
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("buymarket")) {
			MarketPanel currentView = (MarketPanel) view.getmainPanel();
			ArrayList<EconomicBuilding> myEconomicBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
					myEconomicBuildings = c.getEconomicalBuildings();
				}
			}
			try {
				model.getPlayer().build("market", currentView.getCityName());
				this.startView(new MarketPanel(currentView.getCityName(),
						myEconomicBuildings));
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("buybarracks")) {
			BarracksPanel currentView = (BarracksPanel) view.getmainPanel();
			ArrayList<MilitaryBuilding> myMilitaryBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
					myMilitaryBuildings = c.getMilitaryBuildings();
				}
			}
			try {
				model.getPlayer().build("barracks", currentView.getCityName());
				this.startView(new BarracksPanel(currentView.getCityName(),
						myMilitaryBuildings));
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("buyarcheryrange")) {
			ArcheryRangePanel currentView = (ArcheryRangePanel) view
					.getmainPanel();
			ArrayList<MilitaryBuilding> myMilitaryBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
					myMilitaryBuildings = c.getMilitaryBuildings();
				}
			}
			try {
				model.getPlayer().build("archeryrange",
						currentView.getCityName());
				this.startView(new ArcheryRangePanel(currentView.getCityName(),
						myMilitaryBuildings));
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("buystable")) {
			StablePanel currentView = (StablePanel) view.getmainPanel();
			ArrayList<MilitaryBuilding> myMilitaryBuildings = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
					myMilitaryBuildings = c.getMilitaryBuildings();
				}
			}
			try {
				model.getPlayer().build("stable", currentView.getCityName());
				this.startView(new StablePanel(currentView.getCityName(),
						myMilitaryBuildings));
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (typeOfButton.equalsIgnoreCase("upgradefarm")) {
			FarmPanel currentView = (FarmPanel) view.getmainPanel();
			ArrayList<EconomicBuilding> myEconomicBuildings = null;
			try {
				for (City c : model.getPlayer().getControlledCities()) {
					if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
						myEconomicBuildings = c.getEconomicalBuildings();
						for (EconomicBuilding b : myEconomicBuildings) {
							if (b instanceof Farm) {
								model.getPlayer().upgradeBuilding(b);
							}
						}
					}
				}
				this.startView(new FarmPanel(currentView.getCityName(),
						myEconomicBuildings));
				this.updatePlayerInfoBar();
			} catch (BuildingInCoolDownException | MaxLevelException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("upgrademarket")) {
			MarketPanel currentView = (MarketPanel) view.getmainPanel();
			ArrayList<EconomicBuilding> myEconomicBuildings = null;
			try {
				for (City c : model.getPlayer().getControlledCities()) {
					if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
						myEconomicBuildings = c.getEconomicalBuildings();
						for (EconomicBuilding b : myEconomicBuildings) {
							if (b instanceof Market) {
								model.getPlayer().upgradeBuilding(b);
							}
						}
					}
				}
				this.startView(new MarketPanel(currentView.getCityName(),
						myEconomicBuildings));
				this.updatePlayerInfoBar();
			} catch (BuildingInCoolDownException | MaxLevelException
					| NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("recruitunit")) {
			String cityName = ((CarriesCityName) view.getmainPanel())
					.getCityName();
			try {
				if (view.getmainPanel() instanceof ArcheryRangePanel) {
					model.getPlayer().recruitUnit("Archer", cityName);
				} else if (view.getmainPanel() instanceof BarracksPanel) {
					model.getPlayer().recruitUnit("Infantry", cityName);
				} else if (view.getmainPanel() instanceof StablePanel) {
					model.getPlayer().recruitUnit("Cavalry", cityName);
				}
				this.updatePlayerInfoBar();
			} catch (NotEnoughGoldException | MaxRecruitedException
					| BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("upgradebarracks")) {
			BarracksPanel currentView = (BarracksPanel) view.getmainPanel();
			ArrayList<MilitaryBuilding> myMilitaryBuildings = null;
			try {
				for (City c : model.getPlayer().getControlledCities()) {
					if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
						myMilitaryBuildings = c.getMilitaryBuildings();
						for (MilitaryBuilding b : myMilitaryBuildings) {
							if (b instanceof Barracks) {
								model.getPlayer().upgradeBuilding(b);
							}
						}
					}
				}
				this.startView(new BarracksPanel(currentView.getCityName(),
						myMilitaryBuildings));
			} catch (BuildingInCoolDownException | MaxLevelException
					| NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("upgradearcheryrange")) {
			ArcheryRangePanel currentView = (ArcheryRangePanel) view
					.getmainPanel();
			ArrayList<MilitaryBuilding> myMilitaryBuildings = null;
			try {
				for (City c : model.getPlayer().getControlledCities()) {
					if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
						myMilitaryBuildings = c.getMilitaryBuildings();
						for (MilitaryBuilding b : myMilitaryBuildings) {
							if (b instanceof ArcheryRange) {
								model.getPlayer().upgradeBuilding(b);
							}
						}
					}
				}
				this.startView(new ArcheryRangePanel(currentView.getCityName(),
						myMilitaryBuildings));
			} catch (BuildingInCoolDownException | MaxLevelException
					| NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("upgradestable")) {
			StablePanel currentView = (StablePanel) view.getmainPanel();
			ArrayList<MilitaryBuilding> myMilitaryBuildings = null;
			try {
				for (City c : model.getPlayer().getControlledCities()) {
					if (c.getName().equalsIgnoreCase(currentView.getCityName())) {
						myMilitaryBuildings = c.getMilitaryBuildings();
						for (MilitaryBuilding b : myMilitaryBuildings) {
							if (b instanceof Stable) {
								model.getPlayer().upgradeBuilding(b);
							}
						}
					}
				}
				this.startView(new StablePanel(currentView.getCityName(),
						myMilitaryBuildings));
			} catch (BuildingInCoolDownException | MaxLevelException
					| NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(view, e1.getMessage(), "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("cityarmiesbutton")) {
			CarriesCityName currentView = (CarriesCityName) view.getmainPanel();
			this.startView(new CityArmiesPanel((currentView.getCityName())));
		} else if (typeOfButton.equalsIgnoreCase("gotodefendingarmy")) {
			CarriesCityName currentView = (CarriesCityName) view.getmainPanel();
			City currentCity = null;
			for (City c : model.getPlayer().getControlledCities()) {
				if (c.getName().equalsIgnoreCase(currentView.getCityName()))
					currentCity = c;
			}
			this.startView(new CityArmyPanel(currentCity.getDefendingArmy(),
					currentCity.getName(), true));
		} else if (typeOfButton.equalsIgnoreCase("gotocityarmiespanel")) {
			String cityName = ((CarriesCityName) view.getmainPanel())
					.getCityName();
			this.startView(new CityArmiesPanel(cityName));
		} else if (typeOfButton.equalsIgnoreCase("gotocontrolledarmies")) {
			CarriesCityName currentView = (CarriesCityName) view.getmainPanel();
			this.startView(new CityControlledArmiesPanel(currentView
					.getCityName(), model.getPlayer().getControlledArmies()));
		} else if (typeOfButton.equalsIgnoreCase("defendingUnitButton")) {
			if (selectedDefenderUnit == null) {
				selectedDefenderUnit = buttonClicked;
				this.defaultbgcolor = buttonClicked.getBackground();
				this.defaultfgcolor = buttonClicked.getForeground();
				buttonClicked.setBackground(Color.GREEN);
				buttonClicked.setForeground(Color.WHITE);
			} else if (selectedDefenderUnit == buttonClicked) {
				buttonClicked.setBackground(this.defaultbgcolor);
				buttonClicked.setForeground(this.defaultfgcolor);
				selectedDefenderUnit = null;
			} else {
				selectedDefenderUnit.setBackground(this.defaultbgcolor);
				selectedDefenderUnit.setForeground(this.defaultfgcolor);
				selectedDefenderUnit = buttonClicked;
				buttonClicked.setBackground(Color.GREEN);
				buttonClicked.setForeground(Color.WHITE);
			}
		} else if (typeOfButton.equalsIgnoreCase("attackingUnitButton")) {
			if (selectedAttackerUnit == null) {
				selectedAttackerUnit = buttonClicked;
				this.defaultbgcolor = buttonClicked.getBackground();
				this.defaultfgcolor = buttonClicked.getForeground();
				buttonClicked.setBackground(Color.RED);
				buttonClicked.setForeground(Color.WHITE);
			} else if (selectedAttackerUnit == buttonClicked) {
				buttonClicked.setBackground(this.defaultbgcolor);
				buttonClicked.setForeground(this.defaultfgcolor);
				selectedAttackerUnit = null;
			} else {
				selectedAttackerUnit.setBackground(this.defaultbgcolor);
				selectedAttackerUnit.setForeground(this.defaultfgcolor);
				selectedAttackerUnit = buttonClicked;
				buttonClicked.setBackground(Color.RED);
				buttonClicked.setForeground(Color.WHITE);
			}
		} else if (typeOfButton.equalsIgnoreCase("startattack")) {

			if (selectedAttackerUnit != null && selectedDefenderUnit != null) {
				BattleViewPanel currentView = (BattleViewPanel) view
						.getmainPanel();
				Unit attackingUnit = currentView.getAttackingUnits().get(
						currentView.getAttackingArmyButtons().indexOf(
								selectedAttackerUnit));
				Unit defendingUnit = currentView.getDefendingUnits().get(
						currentView.getDefendingArmyButtons().indexOf(
								selectedDefenderUnit));
				int oldDefendingHealth = defendingUnit.getCurrentSoldierCount();

				try {
					attackingUnit.attack(defendingUnit);
					runningLog += attackingUnit.toString()
							+ " Attacked "
							+ defendingUnit.toString()
							+ " and the defending unit lost "
							+ (oldDefendingHealth - defendingUnit
									.getCurrentSoldierCount()) + " Soldiers"
							+ "\n";
					ArrayList<Unit> defendingUnits = currentView
							.getDefendingUnits();
					ArrayList<Unit> attackingUnits = currentView
							.getAttackingUnits();
					attackingUnit = attackingUnits.get((int) Math.random()
							* attackingUnits.size());
					defendingUnit = defendingUnits.get((int) Math.random()
							* defendingUnits.size());
					int oldAttackingHealth = attackingUnit
							.getCurrentSoldierCount();
					defendingUnit.attack(attackingUnit);
					runningLog += defendingUnit.toString()
							+ " Attacked "
							+ attackingUnit.toString()
							+ " and the defending unit lost "
							+ (oldAttackingHealth - attackingUnit
									.getCurrentSoldierCount()) + " Soldiers"
							+ "\n";
					BattleViewPanel p = new BattleViewPanel(
							currentView.getDefendingUnits(),
							currentView.getAttackingUnits());
					if (defendingUnits.size() > 0 && attackingUnits.size() > 0) {
						p.getLog().setText(runningLog);
					}
					if (defendingUnits.size() > 0 && attackingUnits.size() == 0) {
						Iterator<Army> iterForRemove = model.getPlayer()
								.getControlledArmies().iterator();
						while (iterForRemove.hasNext()) {
							Army army = iterForRemove.next();
							if (army.getUnits().size() == 0) {
								iterForRemove.remove();
							}
						}
					} else if (defendingUnits.size() == 0
							&& attackingUnits.size() > 0) {
						model.occupy(attackingUnits.get(0).getParentArmy(),
								attackingUnits.get(0).getParentArmy()
										.getCurrentLocation());
					}
					startView(p);
					selectedAttackerUnit = null;
					selectedDefenderUnit = null;

				} catch (FriendlyFireException e1) {
					JOptionPane.showMessageDialog(view, e1.getMessage(),
							"Error!", JOptionPane.ERROR_MESSAGE);
				}
			} else if (selectedAttackerUnit == null
					&& selectedDefenderUnit != null) {
				JOptionPane.showMessageDialog(view,
						"Please choose an attacking unit", "Error!",
						JOptionPane.ERROR_MESSAGE);
			} else if (selectedAttackerUnit != null
					&& selectedDefenderUnit == null) {
				JOptionPane.showMessageDialog(view,
						"Please choose a defending unit to attack", "Error!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane
						.showMessageDialog(
								view,
								"Please choose an attacking unit and a defending unit to initiate an attack",
								"Error!", JOptionPane.ERROR_MESSAGE);
			}
		} else if (typeOfButton.equalsIgnoreCase("attackfromsiege")) {
			ArrayList<Army> currentlyBesieging = new ArrayList<Army>();
			for (Army a : model.getPlayer().getControlledArmies()) {
				if (a.getCurrentStatus().toString().equalsIgnoreCase("BESIEGING")) {
					currentlyBesieging.add(a);
				}
			}
			try {
				Army toAttack = askUserForArmy(currentlyBesieging,
						"Choose an army from the currently beseiging armies to initiate an attack");
				for (City c : model.getAvailableCities()) {
					if (toAttack.getCurrentLocation().equalsIgnoreCase(c.getName())) {
						Attack(toAttack,c.getDefendingArmy());
					}
				}
			} catch (ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(view,
						"Armies preparing to seige...", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}

		}

		else if (typeOfButton.equalsIgnoreCase("dispose")) {
			view.dispose();
		}
		



	}
	private City getCityByName(String cityName){
		for(City c : model.getAvailableCities()){
			if(c.getName().equalsIgnoreCase(cityName))
				return c;
		}
		return null;
	}

	private void Attack(Army attackerArmy, Army defendingArmy) {
		this.startView(new BattleViewPanel(defendingArmy.getUnits(),
				attackerArmy.getUnits()));
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

	public static void main(String[] args) throws IOException {
		new GameController("Hussein", "Cairo");
	}
}
