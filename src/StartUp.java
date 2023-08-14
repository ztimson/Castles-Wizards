// Libraries
import java.io.*;
import java.util.Scanner;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Start Menu
public class StartUp implements Runnable {

	static String dir = (new File(" ").getAbsolutePath()).trim() + "Objects/",
			helmet, range, melee, armor, shield, loadFile, name, gender, map,
			move = "", animatedDir;
	static JFrame frame1 = new JFrame("Castles and Wizards"),
			frame2 = new JFrame("Castles and Wizards: FIGHT!");
	static JLayeredPane pane = new JLayeredPane(), pane3 = new JLayeredPane();
	static JPanel panel1 = new JPanel(), panel2 = new JPanel(),
			panel3 = new JPanel(), panel4 = new JPanel(), panel5 = new JPanel(
					new BorderLayout()), panel6 = new JPanel(),
			panel7 = new JPanel(new BorderLayout()), inv = new JPanel(),
			sideScroll = new JPanel(new BorderLayout()),
			fpanel1 = new JPanel(), fpanel2 = new JPanel(new BorderLayout()),
			fpanel3 = new JPanel(new BorderLayout());
	static JButton[] btn = new JButton[4];
	static JButton play = new JButton("Play"), editor = new JButton(
			"Map Editor"), exit = new JButton("Quit"), newGame = new JButton(
			"New Game"), load = new JButton("Load Game"),
			helmetB = new JButton(), rangeB = new JButton(),
			meleeB = new JButton(), armorB = new JButton(),
			shieldB = new JButton(), potion = new JButton("Use Potion"),
			btn1 = new JButton("Inventory"), btn2 = new JButton("Map"),
			btn3 = new JButton("Save"), btn4 = new JButton("Exit"),
			buyIt = new JButton("Buy >>>"), sellIt = new JButton("<<< Sell");
	static JLabel[][] lbl = new JLabel[25][25],
			placeHolder = new JLabel[25][25];
	static JLabel mapImage = new JLabel(new ImageIcon(dir + "Images/map.gif")),
			spotHolder = new JLabel(), moneyLBL = new JLabel("Money: "),
			enemyImage = new JLabel(), enemyStats = new JLabel(),
			playerStats = new JLabel(), playerImage = new JLabel(),
			Sprite = new JLabel(), characterName = new JLabel();
	static JTextField tfld = new JTextField();
	static JList<String> list = new JList<String>(),
			inventoryList = new JList<String>(), buy = new JList<String>(),
			sell = new JList<String>();
	static DefaultListModel<String> mdl = new DefaultListModel<String>(),
			mdl2 = new DefaultListModel<String>(),
			mdl3 = new DefaultListModel<String>(),
			mdl4 = new DefaultListModel<String>();
	static JScrollPane pane2 = new JScrollPane(inventoryList);
	static Color color1 = new Color(255, 255, 255, 255);
	static JTextField[] skill = new JTextField[4];
	static JRadioButton male = new JRadioButton("Male"),
			feMale = new JRadioButton("Female");
	static ButtonGroup group1 = new ButtonGroup();
	static String[][] action = new String[25][25];
	static String[] item = new String[] { "Bricks", "Stone", "StoneSlab",
			"Tree", "SnowPacked", "StonePath", "Floor", "Wood", "Dirt", "Ice",
			"Snow", "Bolder", "Fire", "Lava", "Water", "Chest", "Shop", "Sign",
			"Person", "StoneH", "StoneV", "LadderDown", "LadderUp", "Blood",
			"Bones" }, id = new String[item.length];
	static ImageIcon[] image = new ImageIcon[item.length],
			animate = new ImageIcon[12];
	static ImageIcon enemyIcon = new ImageIcon();
	static int[][] value = new int[25][25], lineOfSight = new int[25][25];
	static int[] inventory = new int[13];
	static int xTest = 0, yTest = 0, helmetVal, rangeVal, meleeVal, armorVal,
			shieldVal, price = 0, eHealth = 0, eCurentHealth = 0, eAttack = 0,
			xChar = 0, yChar = 0, xSpot = 0, ySpot = 0, xHist = 0, yHist = 0,
			health, curHealth, strength, observation, money, steps = 0,
			randomSteps = 0, dirX = 0, dirY = 0;;
	static boolean active = false, foot = true, place = false,
			mapToggle = false, equipToggle = false, playToggle = false,
			shopToggle = false, clicked = false, boss1 = false, boss2 = false,
			boss3 = false;
	static double eAccuracy = 0, agility;
	static KeyHandler onPress = new KeyHandler();
	static ButtonHandler click = new ButtonHandler();
	static Thread chestHandler = new Thread(new StartUp());
	static Timer pause = null;

	// Create GUI
	public static void main(String[] args) {

		(new Thread(new StartUp())).start(); // start music

		// Load Background
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(dir + "Images/background.jpg"));

		// set up background panel
		panel1.setBounds(0, 0, 750, 750);
		panel1.add(background);
		panel1.setOpaque(true);
		pane.add(panel1, new Integer(0), 0);

		// set up play menu
		JLabel lbl = new JLabel("Enter Character Name:");
		panel2.setBounds(50, 310, 650, 130);
		panel2.setOpaque(true);
		panel2.setBackground(new Color(50, 0, 0, 255));
		panel2.setVisible(playToggle);
		lbl.setForeground(new Color(255, 255, 255, 255));
		panel2.add(lbl);
		tfld.setPreferredSize(new Dimension(626, 25));
		panel2.add(tfld);
		newGame.setPreferredSize(new Dimension(310, 50));
		newGame.addActionListener(click);
		panel2.add(newGame);
		load.setPreferredSize(new Dimension(310, 50));
		load.addActionListener(click);
		panel2.add(load);
		pane.add(panel2, new Integer(1), 0);

		// Set up Panel 4 (menu)
		panel4.setBackground(new Color(150, 0, 0, 255));
		panel4.setPreferredSize(new Dimension(200, 750));
		play.setPreferredSize(new Dimension(192, 50));
		play.addActionListener(click);
		panel4.add(play);
		editor.setPreferredSize(new Dimension(192, 50));
		editor.addActionListener(click);
		panel4.add(editor);
		exit.setPreferredSize(new Dimension(192, 50));
		exit.addActionListener(click);
		panel4.add(exit);

		// Set up frame
		frame1.add(panel4, BorderLayout.EAST);
		frame1.add(pane, BorderLayout.CENTER);
		frame1.setSize(new Dimension(955, 778));
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setResizable(false);
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
		frame1.requestFocus();
	}

	// Music
	public void run() {
		// variables
		File sound1 = null;
		int length = 0;

		while (true) { // loop music
			int random = (int) (1 + Math.random() * 13.999); // pick random song

			// songs
			sound1 = new File(dir + "Sounds/track" + random + ".wav");
			if (random == 1) {
				length = 382000;
			} else if (random == 2) {
				length = 188000;
			} else if (random == 3) {
				length = 188000;
			} else if (random == 4) {
				length = 189000;
			} else if (random == 5) {
				length = 386000;
			} else if (random == 6) {
				length = 160000;
			} else if (random == 7) {
				length = 214000;
			} else if (random == 8) {
				length = 590000;
			} else if (random == 9) {
				length = 396000;
			} else if (random == 10) {
				length = 340000;
			} else if (random == 11) {
				length = 510000;
			} else if (random == 12) {
				length = 282000;
			} else if (random == 13) {
				length = 267000;
			} else if (random == 14) {
				length = 252000;
			}

			// play song and wait for it to finish
			try {
				AudioInputStream audioIn = AudioSystem
						.getAudioInputStream(sound1);
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
				Thread.sleep(length);
			} catch (Exception e) {
			}
		}
	}

	// Game
	public static class Game implements Runnable {

		// Main Method
		public static void main(String[] args) throws Exception {

			// Load Character file
			Scanner scan1 = new Scanner(new File(dir + "Saves/" + loadFile
					+ "/character.txt"));
			name = scan1.next(); // name
			gender = scan1.next(); // gender
			map = scan1.next(); // current map
			xSpot = scan1.nextInt(); // position on map
			ySpot = scan1.nextInt(); // position on map
			curHealth = scan1.nextInt(); // current health
			health = scan1.nextInt(); // total health
			strength = scan1.nextInt(); // strength stat
			observation = scan1.nextInt(); // observation stat
			agility = scan1.nextDouble(); // agility stat
			money = scan1.nextInt(); // money
			helmet = scan1.next(); // helmet equipped
			range = scan1.next(); // ranged weapon equipped
			melee = scan1.next(); // melee weapon equipped
			armor = scan1.next(); // armor equipped
			shield = scan1.next(); // shield equipped
			scan1.close();

			// apply stat "boosts" for equiped items
			if (helmet.equals("helmet")) {
				helmetVal = 10;
			} else {
				helmetVal = 0;
			}
			if (range.equals("knife")) {
				rangeVal = 30;
			} else if (range.equals("bow")) {
				rangeVal = 40;
			} else if (range.equals("long")) {
				rangeVal = 50;
			} else {
				rangeVal = 0;
			}
			if (melee.equals("staff")) {
				meleeVal = 5;
			} else if (melee.equals("iron")) {
				meleeVal = 10;
			} else if (melee.equals("steel")) {
				meleeVal = 20;
			} else {
				meleeVal = 0;
			}
			if (armor.equals("iron")) {
				armorVal = 10;
			} else if (armor.equals("steel")) {
				armorVal = 20;
			} else {
				armorVal = 0;
			}
			if (shield.equals("shield")) {
				shieldVal = 10;
			} else if (shield.equals("steel")) {
				shieldVal = 20;
			} else {
				shieldVal = 0;
			}

			// scan inventory
			Scanner scan2 = new Scanner(new File(dir + "Saves/" + loadFile
					+ "/inventory.txt"));
			for (int i = 0; i < 13; i++) {
				inventory[i] = scan2.nextInt();
			}
			scan2.close();

			// pick sprite for character based on gender
			animatedDir = dir + "images/" + gender + "/";
			Sprite.setIcon(new ImageIcon(animatedDir + "downC.gif"));
			randomSteps = (int) (Math.random() * 10) + 15;

			// Load tile images
			for (int i = 0; i < item.length; i++) {
				image[i] = new ImageIcon(dir + "/Images/Tiles/" + item[i]
						+ ".jpg");
				if (i < 10) {
					id[i] = "0" + Integer.toString(i);
				} else {
					id[i] = Integer.toString(i);
				}
			}

			// panel the character will move around on
			panel3.setOpaque(false);
			pane.add(panel3, new Integer(2), 0);

			// Side Panel
			panel4.setVisible(false);
			JPanel panel4 = new JPanel();
			panel4.removeAll();
			panel4.setBackground(new Color(150, 0, 0, 255));
			panel4.setPreferredSize(new Dimension(200, 750));
			frame1.add(panel4, BorderLayout.EAST);
			list.setModel(mdl);
			JScrollPane scroll = new JScrollPane(list);
			scroll.setPreferredSize(new Dimension(192, 520));
			panel4.add(scroll);
			btn1.setPreferredSize(new Dimension(192, 50));
			btn1.addActionListener(click);
			panel4.add(btn1);
			btn2.setPreferredSize(new Dimension(192, 50));
			btn2.addActionListener(click);
			panel4.add(btn2);
			btn3.setPreferredSize(new Dimension(192, 50));
			btn3.addActionListener(click);
			panel4.add(btn3);
			btn4.setPreferredSize(new Dimension(192, 50));
			btn4.addActionListener(click);
			panel4.add(btn4);
			panel4.setVisible(true);

			// Panel to view Inventory
			panel5.setOpaque(true);
			panel5.setBounds(50, 50, 650, 650);
			panel5.setBackground(new Color(50, 0, 0, 255));
			pane.add(panel5, new Integer(3), 0);
			panel5.setVisible(equipToggle);
			characterName = new JLabel(name, SwingConstants.CENTER);
			characterName.setFont(new Font("Vivaldi", Font.BOLD, 50));
			characterName.setForeground(new Color(255, 255, 255, 255));
			panel5.add(characterName, BorderLayout.NORTH);
			inv.setBackground(new Color(50, 0, 0, 255));
			spotHolder.setPreferredSize(new Dimension(115, 150));
			helmetB.setBackground(new Color(50, 0, 0, 255));
			helmetB.setFocusable(false);
			helmetB.setBorderPainted(false);
			helmetB.setIcon(new ImageIcon(dir + "Images/helmet.gif"));
			helmetB.setPreferredSize(new Dimension(150, 150));
			helmetB.addActionListener(click);
			rangeB.setBackground(new Color(50, 0, 0, 255));
			rangeB.setFocusable(false);
			rangeB.setBorderPainted(false);
			rangeB.setIcon(new ImageIcon(dir + "Images/range.gif"));
			rangeB.setPreferredSize(new Dimension(100, 100));
			rangeB.addActionListener(click);
			meleeB.setBackground(new Color(50, 0, 0, 255));
			meleeB.setFocusable(false);
			meleeB.setBorderPainted(false);
			meleeB.setIcon(new ImageIcon(dir + "Images/melee.gif"));
			meleeB.setPreferredSize(new Dimension(100, 100));
			meleeB.addActionListener(click);
			armorB.setBackground(new Color(50, 0, 0, 255));
			armorB.setFocusable(false);
			armorB.setBorderPainted(false);
			armorB.setIcon(new ImageIcon(dir + "Images/armor.gif"));
			armorB.setPreferredSize(new Dimension(200, 200));
			armorB.addActionListener(click);
			shieldB.setBackground(new Color(50, 0, 0, 255));
			shieldB.setFocusable(false);
			shieldB.setBorderPainted(false);
			shieldB.setIcon(new ImageIcon(dir + "Images/shield.gif"));
			shieldB.setPreferredSize(new Dimension(100, 100));
			shieldB.addActionListener(click);
			panel5.add(inv, BorderLayout.CENTER);
			sideScroll.add(pane2, BorderLayout.CENTER);
			potion.setBackground(new Color(50, 0, 0, 255));
			potion.setForeground(new Color(255, 255, 255, 255));
			potion.setPreferredSize(new Dimension(200, 100));
			potion.addActionListener(click);
			sideScroll.add(potion, BorderLayout.SOUTH);
			panel5.add(sideScroll, BorderLayout.EAST);
			pane2.setPreferredSize(new Dimension(200, 350));
			inventoryList.setModel(mdl2);

			// Panel to view Map
			panel6.setOpaque(false);
			panel6.setBounds(0, 0, 750, 750);
			panel6.add(mapImage);
			pane.add(panel6, new Integer(4), 0);
			panel6.setVisible(mapToggle);

			mapLoader(); // Load the map

			// buffer character sprite images
			new ImageIcon(animatedDir + "upC.gif");
			new ImageIcon(animatedDir + "upR.gif");
			new ImageIcon(animatedDir + "upL.gif");
			new ImageIcon(animatedDir + "downC.gif");
			new ImageIcon(animatedDir + "downR.gif");
			new ImageIcon(animatedDir + "downL.gif");
			new ImageIcon(animatedDir + "leftC.gif");
			new ImageIcon(animatedDir + "leftR.gif");
			new ImageIcon(animatedDir + "leftL.gif");
			new ImageIcon(animatedDir + "rightC.gif");
			new ImageIcon(animatedDir + "rightR.gif");
			new ImageIcon(animatedDir + "rightL.gif");

			frame1.addKeyListener(onPress); // start looking for key presses
		}// close Main Method

		// Load a new map into the game
		private static void mapLoader() throws Exception {

			// adjust panel 1 (world)
			panel1 = new JPanel(new GridLayout(25, 25));
			pane.remove(panel1);
			panel1.setOpaque(true);
			panel1.setBackground(new Color(0, 0, 0, 255));
			panel1.setBounds(0, 0, 750, 750);
			pane.add(panel1, new Integer(0), 0);
			panel2.setVisible(false);
			panel2 = new JPanel(new GridLayout(25, 25));
			pane.remove(panel2);
			panel2.setOpaque(false);
			panel2.setBounds(0, 0, 750, 750);
			pane.add(panel2, new Integer(1), 0);

			// Read map file
			Scanner scan2 = new Scanner(new File(dir + "Maps/" + map));
			// read, store, and display every tile
			for (int y = 0; y < 25; y++) {
				for (int x = 0; x < 25; x++) {
					action[x][y] = new String();
					String tile = scan2.next();
					for (int i = 0; i < item.length; i++) {
						if (id[i].equals(tile)) {
							value[x][y] = Integer.parseInt(id[i]);
							lbl[x][y] = new JLabel();
							panel1.remove(lbl[x][y]);
							lbl[x][y].setIcon(image[i]);
							panel1.add(lbl[x][y]);
						}
					}
				}
			}
			scan2.close(); // close the scanner

			// make a second pass for tile that require two images (layered)
			Scanner scan3 = new Scanner(new File(dir + "Maps/MapInfo/" + map));
			for (int y = 0; y < 25; y++) {
				for (int x = 0; x < 25; x++) {
					placeHolder[x][y] = new JLabel();
					panel2.remove(placeHolder[x][y]); // create & reset place holders for grid layout

					//set the tile to the tile below it
					if (value[x][y] == 11 || value[x][y] == 12
							|| value[x][y] >= 15 && value[x][y] <= 18
							|| value[x][y] >= 21) {

						try {
							lbl[x][y].setIcon(lbl[x][y + 1].getIcon());
						} catch (Exception e) {
						}
					}

					//load overlapping image
					if (value[x][y] == 11) { // bolder
						placeHolder[x][y].setIcon(new ImageIcon(dir
								+ "Images/Tiles/bolder.gif"));
					} else if (value[x][y] == 12) {//fire
						placeHolder[x][y].setIcon(new ImageIcon(dir
								+ "Images/Tiles/fire.gif"));
					} else if (value[x][y] == 23) { // blood
						placeHolder[x][y].setIcon(new ImageIcon(dir
								+ "Images/Tiles/blood.gif"));
					} else if (value[x][y] == 24) { // bones
						placeHolder[x][y].setIcon(new ImageIcon(dir
								+ "Images/Tiles/bones.gif"));
					} else if (value[x][y] == 16) { // shop
						placeHolder[x][y].setIcon(new ImageIcon(dir
								+ "Images/Tiles/shop.gif"));
					}

					// load tiles that have a corresponding action
					if (value[x][y] == 15 || value[x][y] >= 17
							&& value[x][y] <= 22) {
						action[x][y] = scan3.nextLine();
						if (value[x][y] == 15) { // chests
							placeHolder[x][y].setIcon(new ImageIcon(dir
									+ "Images/Tiles/chest.gif"));
						} else if (value[x][y] == 17) { // sign
							placeHolder[x][y].setIcon(new ImageIcon(dir
									+ "Images/Tiles/sign.gif"));
						} else if (value[x][y] == 18) { // person
							placeHolder[x][y].setIcon(new ImageIcon(dir
									+ "Images/sprites/" + scan3.nextLine()));
						} else if (value[x][y] == 19 || value[x][y] == 20) {
							if (observation < Integer
									.parseInt(scan3.nextLine())) {
								lbl[x][y].setIcon(image[Integer.parseInt(scan3 // map
										.nextLine())]);
							} else {
								scan3.nextLine();
							}

						//test to see if the players observation is high enough to see pathway
						} else if (value[x][y] == 21) {
							if (observation < Integer
									.parseInt(scan3.nextLine())) {
								lbl[x][y].setIcon(image[Integer.parseInt(scan3
										.nextLine())]);
							} else {
								scan3.nextLine();
								placeHolder[x][y].setIcon(new ImageIcon(dir
										+ "Images/Tiles/ladderDown.gif"));
							}
						} else if (value[x][y] == 22) {
							placeHolder[x][y].setIcon(new ImageIcon(dir
									+ "Images/Tiles/ladderUp.gif"));
						}
					}
					panel2.add(placeHolder[x][y]);
				}
			}
			scan3.close(); // close the scanner

			// Refresh
			pane.validate();
			pane.repaint();

			// Position person
			if (place == true) {
				xSpot = xHist;
				ySpot = yHist;
			}
			xChar = (xSpot * 30);
			yChar = ySpot * 30 - 10;
			panel3.setBounds(xChar, yChar, 30, 35);
			panel3.add(Sprite); // add character to map
			panel2.setVisible(true);

			try {
				Game.save(); // save game when map is loaded
			} catch (Exception e) {
			}
		}// close mapLoader method

		// Load Inventory Items
		private static void items() throws Exception {
			mdl2.removeAllElements();
			for (int i = 0; i < 13; i++) {
				if (i == 0 && inventory[i] > 0) { // staff
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Staff");
					}
				} else if (i == 1 && inventory[i] > 0) { // iron sword
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Iron Sword");
					}
				} else if (i == 2 && inventory[i] > 0) { // steel sword
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Steel Sword");
					}
				} else if (i == 3 && inventory[i] > 0) { // throwing knife
					mdl2.addElement("Throwing Knife x" + inventory[i]);
				} else if (i == 4 && inventory[i] > 0) { // bow
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Bow");
					}
				} else if (i == 5 && inventory[i] > 0) { // long bow
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Long Bow");
					}
				} else if (i == 6 && inventory[i] > 0) { // arrow
					mdl2.addElement("Arrow x" + inventory[i]);
				} else if (i == 7 && inventory[i] > 0) { // shield
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Shield");
					}
				} else if (i == 8 && inventory[i] > 0) { // steel shield
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Steel Shield");
					}
				} else if (i == 9 && inventory[i] > 0) { // iron armor
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Iron Armor");
					}
				} else if (i == 10 && inventory[i] > 0) { // steel armor
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Steel Armor");
					}
				} else if (i == 11 && inventory[i] > 0) { // helmet
					for (int i2 = 0; i2 < inventory[i]; i2++) {
						mdl2.addElement("Helmet");
					}
				} else if (i == 12 && inventory[i] > 0) { // potions
					mdl2.addElement("Potion x" + inventory[i]);
				}

				//stats
				panel5.setVisible(false);
				inv.removeAll();
				inv.add(spotHolder);
				inv.add(helmetB);
				inv.add(rangeB);
				inv.add(meleeB);
				inv.add(armorB);
				inv.add(shieldB);
				JLabel bonus = new JLabel("<HTML> Deffence Bonus<br>Helmet: +"
						+ helmetVal + "<HTML><br>Armor: +" + armorVal
						+ "<HTML><br>Shield: +" + shieldVal
						+ "<HTML><br><br>Attack Bonus <br>Melee: +" + meleeVal
						+ "<HTML><br>Ranged: +" + rangeVal);
				bonus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				bonus.setForeground(color1);
				inv.add(bonus);
				JLabel info = new JLabel("Health: " + curHealth + "/" + health
						+ "    Money: $" + money, SwingConstants.CENTER);
				info.setFont(new Font("Times New Roman", Font.BOLD, 30));
				info.setForeground(new Color(255, 255, 255, 255));
				inv.add(info, BorderLayout.SOUTH);
				panel5.setVisible(equipToggle);
			}
		}

		// Action based on the tile being stood on
		public static void check() throws Exception {
			if (value[xSpot][ySpot] >= 19 && value[xSpot][ySpot] <= 22) { // change maps
				place = true;
				if (value[xSpot][ySpot] == 19) {
					if (xSpot > 12) {
						xHist = 1;
					} else {
						xHist = 23;
					}
					yHist = ySpot;
				} else if (value[xSpot][ySpot] == 20) {
					if (ySpot > 12) {
						yHist = 1;
					} else {
						yHist = 23;
					}
					xHist = xSpot;
				} else if (value[xSpot][ySpot] == 21
						|| value[xSpot][ySpot] == 22) {
					xHist = xSpot;
					yHist = ySpot;
				}
				// chance of observation skill going up
				if (5 >= (Math.random() * 99) + 1) {
					observation++;
					mdl.addElement("Your Observation went up!");
				}
				map = action[xSpot][ySpot]; // pick next map
				Game.mapLoader(); // load next map
			} else if (value[xSpot][ySpot] >= 8 && value[xSpot][ySpot] <= 10) {
				steps++;
			}

			// chance of being attacked
			if (randomSteps == steps) {
				steps = 0;
				Game.fight();
				randomSteps = (int) (Math.random() * 10) + 15;
			}
		}

		// open load and update chests
		public void run() {
			boolean empty = true;
			Scanner scan4 = null;
			int[] chestItems = new int[14];
			try {
				scan4 = new Scanner(new File(dir + "Saves/" + loadFile + "/"
						+ action[xTest][yTest]));
			} catch (Exception e2) {
			}

			// check to see if chest is empty
			for (int i = 0; i < 14; i++) {
				chestItems[i] = scan4.nextInt();
				if (chestItems[i] != 0) {
					empty = false;
				}
			}

			//display items gained
			if (empty == true) {
				mdl.addElement("Empty");
			} else if (empty == false) {
				mdl.addElement("You Picked Up:");
				for (int i = 0; i < 14; i++) {
					if (i == 13) {
						money = money + chestItems[i];
					} else {
						inventory[i] = inventory[i] + chestItems[i];
					}
					if (i == 0 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Staff");
						}
					} else if (i == 1 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Iron Sword");
						}
					} else if (i == 2 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Steel Sword");
						}
					} else if (i == 3 && chestItems[i] > 0) {
						mdl.addElement("Throwing Knife x" + chestItems[i]);
					} else if (i == 4 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Bow");
						}
					} else if (i == 5 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Long Bow");
						}
					} else if (i == 6 && chestItems[i] > 0) {
						mdl.addElement("Arrow x" + chestItems[i]);
					} else if (i == 7 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Shield");
						}
					} else if (i == 8 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Steel Shield");
						}
					} else if (i == 9 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Iron Armor");
						}
					} else if (i == 10 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Steel Armor");
						}
					} else if (i == 11 && chestItems[i] > 0) {
						for (int i2 = 0; i2 < chestItems[i]; i2++) {
							mdl.addElement("Helmet");
						}
					} else if (i == 12 && chestItems[i] > 0) {
						mdl.addElement("Potion x" + chestItems[i]);
					} else if (i == 13 && chestItems[i] > 0) {
						mdl.addElement("$" + chestItems[i]);
					}
				}

				// update chest to being empty
				PrintWriter out3 = null;
				try {
					out3 = new PrintWriter(new File(dir + "Saves/" + loadFile
							+ "/" + action[xTest][yTest]));
				} catch (Exception e3) {
				}
				for (int i = 0; i < 14; i++) {
					out3.println("0");
				}
				out3.close();
			}

			try {
				Game.items(); // update items
				Game.save(); // save game
			} catch (Exception e1) {
			}
		}

		// Save Game
		public static void save() {
			PrintWriter out = null;
			try {
				out = new PrintWriter(new File(dir + "Saves/" + loadFile
						+ "/character.txt"));
			} catch (FileNotFoundException e1) {
			}

			// save character file
			out.println(name);
			out.println(gender);
			out.println(map);
			out.println(xSpot);
			out.println(ySpot);
			out.println("");
			out.println(curHealth);
			out.println(health);
			out.println(strength);
			out.println(observation);
			out.println(agility);
			out.println("");
			out.println(money);
			out.println("");
			out.println(helmet);
			out.println(range);
			out.println(melee);
			out.println(armor);
			out.println(shield);
			out.close();

			// save inventory file
			PrintWriter out2 = null;
			try {
				out2 = new PrintWriter(new File(dir + "Saves/" + loadFile
						+ "/inventory.txt"));
			} catch (FileNotFoundException e1) {
			}
			for (int i = 0; i < 13; i++) {
				out2.println(inventory[i]);
			}
			out2.close();

			// give feedback
			mdl.addElement("Game Saved...");
			mdl.addElement(" ");
		}

		// Fighting
		public static void fight() {
			frame2 = new JFrame();
			fpanel1 = new JPanel();
			fpanel2 = new JPanel(new BorderLayout());
			fpanel3 = new JPanel(new BorderLayout());

			fpanel1.setPreferredSize(new Dimension(600, 115));
			fpanel1.setBackground(new Color(50, 0, 0, 255));
			for (int i = 0; i < 4; i++) {
				btn[i] = new JButton();
				btn[i].setPreferredSize(new Dimension(390, 50));
				btn[i].addActionListener(click);
				fpanel1.add(btn[i]);
			}
			btn[0].setText("Melee Attack");
			btn[1].setText("Ranged Attack");
			btn[2].setText("Potion");
			btn[3].setText("Run");

			if (boss1 == true) {
				eHealth = 300;
				eCurentHealth = eHealth;
				eAttack = 100;
				eAccuracy = 0.15;
				enemyIcon = new ImageIcon(dir + "Images/Sprites/boss1I.gif");
			} else if (boss2 == true) {
				eHealth = 300;
				eCurentHealth = eHealth;
				eAttack = 100;
				eAccuracy = 0.15;
				enemyIcon = new ImageIcon(dir + "Images/Sprites/boss2I.gif");
			} else if (boss3 == true) {
				eHealth = 500;
				eCurentHealth = eHealth;
				eAttack = 100;
				eAccuracy = 0.15;
				enemyIcon = new ImageIcon(dir + "Images/Sprites/boss3I.gif");
			} else {
				int enemy = health + strength + meleeVal;
				if (enemy > 350) { // Wizard
					enemy = 4;
				} else if (enemy > 300) { // Orc
					enemy = 3;
				} else if (enemy > 250) { // Zombie
					enemy = 2;
				} else if (enemy > 200) { // Bandit
					enemy = 1;
				} else { // wolf
					enemy = 0;
				}
				enemy = (int) (Math.random() * enemy) + 1;
				if (enemy == 1) { // wolf
					eHealth = 75;
					eCurentHealth = eHealth;
					eAttack = 20;
					eAccuracy = 0.15;
					enemyIcon = new ImageIcon(dir + "Images/Sprites/wolf.gif");
				} else if (enemy == 2) { // bandit
					eHealth = 125;
					eCurentHealth = eHealth;
					eAttack = 40;
					eAccuracy = 0.1;
					enemyIcon = new ImageIcon(dir + "Images/Sprites/bandit.gif");
				} else if (enemy == 3) { // Zombie
					eHealth = 100;
					eCurentHealth = eHealth;
					eAttack = 50;
					eAccuracy = 0.1;
					enemyIcon = new ImageIcon(dir + "Images/Sprites/zombie.gif");
				} else if (enemy == 4) { // orc
					eHealth = 150;
					eCurentHealth = eHealth;
					eAttack = 75;
					eAccuracy = 0.15;
					enemyIcon = new ImageIcon(dir + "Images/Sprites/orc.gif");
				} else if (enemy == 5) { // wizard
					eHealth = 200;
					eCurentHealth = eHealth;
					eAttack = 75;
					eAccuracy = 0.35;
					enemyIcon = new ImageIcon(dir + "Images/Sprites/wizard.gif");
				}
			}

			// visuals
			enemyStats.setText(eCurentHealth + "/" + eHealth);
			enemyImage.setIcon(enemyIcon);
			fpanel2.add(enemyStats, BorderLayout.WEST);
			fpanel2.add(enemyImage, BorderLayout.CENTER);
			fpanel2.setOpaque(false);
			fpanel2.setBounds(450, 0, 400, 300);
			fpanel2.setVisible(true);
			pane3.add(fpanel2, new Integer(0), 0);
			if (gender.equals("male")) {
				playerImage
						.setIcon(new ImageIcon(dir + "Images/Male/Fight.gif"));
			} else {
				playerImage.setIcon(new ImageIcon(dir
						+ "Images/Female/Fight.gif"));
			}
			playerStats.setText(curHealth + "/" + health);
			fpanel3.add(playerStats, BorderLayout.EAST);
			fpanel3.add(playerImage, BorderLayout.CENTER);
			fpanel3.setOpaque(false);
			fpanel3.setBounds(0, 160, 300, 300);
			fpanel3.setVisible(true);
			pane3.add(fpanel3, new Integer(1), 0);

			// load window
			frame2.add(pane3, BorderLayout.CENTER);
			frame2.add(fpanel1, BorderLayout.SOUTH);
			frame2.setSize(new Dimension(800, 600));
			frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame2.setResizable(false);
			frame2.setLocationRelativeTo(null);
			frame2.setVisible(true);
			frame2.requestFocus();
		}

		// Opponents turn
		public static void opponent() throws Exception {
			new Thread(new Runnable() {
				public void run() {
					try {
						// enemy attacks
						enemyStats.setText(eCurentHealth + "/" + eHealth);
						fpanel2.setBounds(400, 50, 400, 300);
						Thread.sleep(100);
						fpanel2.setBounds(450, 0, 400, 300);
						Thread.sleep(250);

						// he missed
						if (agility + eAccuracy >= (int) (Math.random() * 99) + 1) {
							// Possibility of your agility stat going up
							if (20 >= (Math.random()*99)+1){
							agility = agility+0.25;
							mdl.addElement("Your Agility went up!");
							}
							fpanel3.setBounds(200, 160, 300, 300);
							Thread.sleep(250);
							fpanel3.setBounds(0, 160, 300, 300);
						} else { // he hit you
							// chance of health stat going up
							if (2 >= (Math.random()*99)+1){
								health = health+5;
								curHealth = curHealth+5;
								mdl.addElement("Your Health went up!");
								}
							curHealth = curHealth - eAttack;
							//animate
							for (int i = 0; i < 3; i++) {
								Thread.sleep(100);
								fpanel3.setVisible(false);
								Thread.sleep(100);
								fpanel3.setVisible(true);
							}
						}
						// if the player died
						playerStats.setText(curHealth + "/" + health);
						if (curHealth <= 0) {
							for (int c = 1; c < 16; c++) {
								fpanel3.setBounds(0, 25 * c, 400, 300);
								Thread.sleep(50);
							}
							frame1.setVisible(false);
							frame1.dispose();
							frame2.setVisible(false);
							frame2.dispose();
							JOptionPane.showMessageDialog(null, "Game Over", "You Died", 0);
							Thread.sleep(3000);
							System.exit(0);
						}
					} catch (Exception e) {
					}
				}
			}).start();
		}

		// move character
		public static void move() throws Exception {
			active = true;
			if (move.equals("up")) {
				dirX = 0;
				dirY = -1;
			} else if (move.equals("down")) {
				dirX = 0;
				dirY = +1;
			} else if (move.equals("left")) {
				dirX = -1;
				dirY = 0;
			} else if (move.equals("right")) {
				dirX = +1;
				dirY = 0;
			}

			// change image to the direction the character
			Sprite.setIcon(new ImageIcon(animatedDir + move + "C.gif"));
			new Thread(new Runnable() {
				public void run() {
					try {
						// check to see if the player can walk there
						if (value[xSpot + dirX][ySpot + dirY] >= 4
								&& value[xSpot + dirX][ySpot + dirY] <= 10
								|| value[xSpot + dirX][ySpot + dirY] >= 19
								&& value[xSpot + dirX][ySpot + dirY] <= 23) {

							// update position and animate
							xSpot = xSpot + dirX;
							ySpot = ySpot + dirY;
							Thread.sleep(200);
							xChar = xChar + dirX * 15;
							yChar = yChar + dirY * 15;
							panel3.setBounds(xChar, yChar, 30, 35);
							if (foot == true) {
								Sprite.setIcon(new ImageIcon(animatedDir + move
										+ "R.gif"));
							} else {
								Sprite.setIcon(new ImageIcon(animatedDir + move
										+ "L.gif"));
							}
							foot = foot == false;
							Thread.sleep(200);
							xChar = xChar + dirX * 15;
							yChar = yChar + dirY * 15;
							panel3.setBounds(xChar, yChar, 30, 35);
							Sprite.setIcon(new ImageIcon(animatedDir + move
									+ "C.gif"));
							Game.check(); // check to see if the character is standing on anything
						}
					} catch (Exception e) {
					}
					active = false;
				}
			}).start();
		}
	}

	// BUTTONS!!!
	private static class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == play) { // Play Game
				playToggle = playToggle == false;
				panel2.setVisible(playToggle);
			} else if (e.getSource() == editor) { // Editor
				mapEditor.main(null);
			} else if (e.getSource() == exit) { // Exit
				System.exit(0);
			} else if (e.getSource() == newGame) { // New Game
				// if the character stats field has been updated
				if (clicked == true) {
					// check to makes sure stats add up to 20
					if (Integer.parseInt(skill[0].getText())
							+ Integer.parseInt(skill[1].getText())
							+ Integer.parseInt(skill[2].getText())
							+ Integer.parseInt(skill[3].getText()) == 20) {

						// get name
						loadFile = tfld.getText();
						(new File(dir + "Saves/" + loadFile)).mkdirs();

						// copy chests
						boolean contin = true, continu = true;
						for (int i = 1; contin == true; i++) {
							PrintWriter paste = null;
							Scanner copy = null;
							try {
								copy = new Scanner(new File(dir + "Chests/" + i
										+ ".txt"));
								paste = new PrintWriter(new File(dir + "Saves/"
										+ loadFile + "/" + i + ".txt"));
							} catch (Exception e2) {
								contin = false;
							}
							while (continu == true) {

								try {
									paste.println(copy.nextLine());
								} catch (Exception e2) {
									paste.close();
									continu = false;
								}
							}
						}

						// create inventory
						PrintWriter out = null;
						try {
							out = new PrintWriter(new File(dir + "Saves/"
									+ loadFile + "/inventory.txt"));
							for (int i = 0; i < 14; i++) {
								out.println("0");
							}
							out.close();
							out = new PrintWriter(new File(dir + "Saves/"
									+ loadFile + "/character.txt"));
						} catch (FileNotFoundException e2) {
						}

						// create character file
						out.println(loadFile);
						if (male.isSelected() == true) {
							out.println("male");
						} else {
							out.println("female");
						}
						out.println("00.txt");
						out.println("9");
						out.println("11");
						out.println("");
						out.println(100 + Integer.parseInt(skill[0].getText()) * 5);
						out.println(100 + Integer.parseInt(skill[0].getText()) * 5);
						out.println(20 + Integer.parseInt(skill[1].getText()) * 2);
						out.println(skill[2].getText());
						out.println(5 + Integer.parseInt(skill[3].getText()) * 0.25);
						for (int i = 0; i < 6; i++){
						out.println("0");
						}
						out.close();

						// load game
						try {
							Game.main(null);
						} catch (Exception e1) {
							e1.printStackTrace();
						}

					} else { // if skill points do not have a sum of 20
						panel2.setVisible(false);
						JLabel error = new JLabel(
								"Skill points do not have a sum of 20");
						error.setForeground(new Color(150, 0, 0, 255));
						panel2.add(error);
						panel2.setVisible(true);
					}
				// if the character fields need to be filled
				} else if (clicked == false) {
					clicked = true;
					panel2.setVisible(false);
					JLabel[] catagory = new JLabel[8];
					for (int i = 0; i < 8; i++) {
						catagory[i] = new JLabel();
						catagory[i]
								.setForeground(new Color(255, 255, 255, 255));
						if (i < 4) {
							skill[i] = new JTextField("5");
							skill[i].setPreferredSize(new Dimension(30, 20));
						}
					}
					panel2.add(catagory[0]);
					panel2.add(catagory[1]);
					panel2.add(male);
					panel2.add(feMale);
					panel2.add(catagory[2]);
					panel2.add(catagory[3]);
					catagory[0]
							.setText("___________________________________________________________________________________________");
					catagory[1].setText("Gender:");
					catagory[2]
							.setText("___________________________________________________________________________________________");
					catagory[3]
							.setText("Spend 20 points to updrade the following:");
					catagory[4].setText("Health:");
					catagory[5].setText("Strength:");
					catagory[6].setText("Observation:");
					catagory[7].setText("Agility:");
					JPanel nested1 = new JPanel();
					nested1.setBackground(new Color(50, 0, 0, 255));
					nested1.setPreferredSize(new Dimension(650, 30));
					nested1.add(catagory[4]);
					nested1.add(skill[0]);
					nested1.add(catagory[5]);
					nested1.add(skill[1]);
					nested1.add(catagory[6]);
					nested1.add(skill[2]);
					nested1.add(catagory[7]);
					nested1.add(skill[3]);
					panel2.add(nested1);
					male.setForeground(new Color(255, 255, 255, 255));
					male.setBackground(new Color(50, 0, 0, 255));
					group1.add(male);
					feMale.setForeground(new Color(255, 255, 255, 255));
					feMale.setBackground(new Color(50, 0, 0, 255));
					group1.add(feMale);
					panel2.setBounds(50, 200, 650, 260);
					panel2.setVisible(true);
				}
			} else if (e.getSource() == load) { // Load Game
				loadFile = tfld.getText();
				try {
					Game.main(null);
				} catch (Exception e1) {
				}
			} else if (e.getSource() == btn1) { // Inventory
				equipToggle = equipToggle == false;
				try {
					Game.items();
				} catch (Exception e1) {
				}
				panel5.setVisible(equipToggle);
			} else if (e.getSource() == btn2) { // Map
				mapToggle = mapToggle == false;
				panel6.setVisible(mapToggle);
			} else if (e.getSource() == btn3) { // Save
				Game.save();
			} else if (e.getSource() == btn4) { // Exit
				System.exit(0);
			} else if (e.getSource() == helmetB) { // Helmet
				try {
					if (curHealth - helmetVal != 0
							&& (inventoryList.getSelectedValue()).toString()
									.equals("Helmet")) {
						if (helmet.equals("helmet")) {
							inventory[11]++;
						}
						color1 = new Color(255, 255, 255, 255);
						curHealth = curHealth - helmetVal;
						health = health - helmetVal;
						helmet = "helmet";
						helmetVal = 10;
						inventory[11]--;
					} else {
						color1 = new Color(255, 0, 0, 255);
					}
					curHealth = curHealth + helmetVal;
					health = health + helmetVal;
					Game.items();
				} catch (Exception e2) {
					color1 = new Color(255, 0, 0, 255);
				}
			} else if (e.getSource() == rangeB) { // Ranged
				try {
					if (inventoryList.getSelectedValue().toString()
							.equals("Long Bow")) {
						if (range.equals("bow")) {
							inventory[4]++;
						} else if (range.equals("long")) {
							inventory[5]++;
						}
						color1 = new Color(255, 255, 255, 255);
						range = "long";
						rangeVal = 50;
						inventory[5]--;
					} else if (inventoryList.getSelectedValue().toString()
							.equals("Bow")) {
						if (range.equals("bow")) {
							inventory[4]++;
						} else if (range.equals("long")) {
							inventory[5]++;
						}
						color1 = new Color(255, 255, 255, 255);
						range = "bow";
						rangeVal = 40;
						inventory[4]--;
					} else if ((inventoryList.getSelectedValue().toString())
							.substring(0, 14).equals("Throwing Knife")) {
						if (range.equals("bow")) {
							inventory[4]++;
						} else if (range.equals("long")) {
							inventory[5]++;
						}
						color1 = new Color(255, 255, 255, 255);
						range = "knife";
						rangeVal = 30;
					} else {
						color1 = new Color(255, 0, 0, 255);
					}
					Game.items();
				} catch (Exception e2) {
					color1 = new Color(255, 0, 0, 255);
				}
			} else if (e.getSource() == meleeB) { // Sword
				try {
					if (inventoryList.getSelectedValue().toString()
							.equals("Staff")) {
						if (melee.equals("staff")) {
							inventory[0]++;
						} else if (melee.equals("iron")) {
							inventory[1]++;
						} else if (melee.equals("steel")) {
							inventory[2]++;
						}
						color1 = new Color(255, 255, 255, 255);
						melee = "staff";
						meleeVal = 5;
						inventory[0]--;
					} else if (inventoryList.getSelectedValue().toString()
							.equals("Iron Sword")) {
						if (melee.equals("staff")) {
							inventory[0]++;
						} else if (melee.equals("iron")) {
							inventory[1]++;
						} else if (melee.equals("steel")) {
							inventory[2]++;
						}
						color1 = new Color(255, 255, 255, 255);
						melee = "iron";
						meleeVal = 10;
						inventory[1]--;
					} else if (inventoryList.getSelectedValue().toString()
							.equals("Steel Sword")) {
						if (melee.equals("staff")) {
							inventory[0]++;
						} else if (melee.equals("iron")) {
							inventory[1]++;
						} else if (melee.equals("steel")) {
							inventory[2]++;
						}
						color1 = new Color(255, 255, 255, 255);
						melee = "steel";
						meleeVal = 20;
						inventory[2]--;
					} else {
						color1 = new Color(255, 0, 0, 255);
					}
					Game.items();
				} catch (Exception e2) {
					color1 = new Color(255, 0, 0, 255);
				}
			} else if (e.getSource() == armorB) { // Armor
				try {
					if (curHealth - 10 != 0
							&& (inventoryList.getSelectedValue()).toString()
									.equals("Iron Armor")) {
						if (armor.equals("iron")) {
							inventory[9]++;
						} else if (armor.equals("steel")) {
							inventory[10]++;
						}
						color1 = new Color(255, 255, 255, 255);
						curHealth = curHealth - armorVal;
						health = health - armorVal;
						armor = "iron";
						armorVal = 10;
						inventory[9]--;
					} else if (curHealth - 20 != 0
							&& (inventoryList.getSelectedValue()).toString()
									.equals("Steel Armor")) {
						if (armor.equals("iron")) {
							inventory[9]++;
						} else if (armor.equals("steel")) {
							inventory[10]++;
						}
						color1 = new Color(255, 255, 255, 255);
						curHealth = curHealth - armorVal;
						health = health - armorVal;
						armor = "steel";
						armorVal = 20;
						inventory[10]--;
					} else {
						color1 = new Color(255, 0, 0, 255);
					}
					curHealth = curHealth + armorVal;
					health = health + armorVal;
					Game.items();
				} catch (Exception e2) {
					color1 = new Color(255, 0, 0, 255);
				}
			} else if (e.getSource() == shieldB) { // Shield
				try {
					if (curHealth - 10 != 0
							&& (inventoryList.getSelectedValue()).toString()
									.equals("Shield")) {
						if (shield.equals("shield")) {
							inventory[7]++;
						} else if (shield.equals("steel")) {
							inventory[8]++;
						}
						color1 = new Color(255, 255, 255, 255);
						curHealth = curHealth - shieldVal;
						health = health - shieldVal;
						shield = "shield";
						shieldVal = 10;
						inventory[7]--;
					} else if (curHealth - 20 != 0
							&& (inventoryList.getSelectedValue()).toString()
									.equals("Steel Shield")) {
						if (shield.equals("shield")) {
							inventory[7]++;
						} else if (shield.equals("steel")) {
							inventory[8]++;
						}
						color1 = new Color(255, 255, 255, 255);
						curHealth = curHealth - shieldVal;
						health = health - shieldVal;
						shield = "steel";
						shieldVal = 20;
						inventory[8]--;
					} else {
						color1 = new Color(255, 0, 0, 255);
					}
					curHealth = curHealth + shieldVal;
					health = health + shieldVal;
					Game.items();
				} catch (Exception e2) {
					color1 = new Color(255, 0, 0, 255);
				}
			} else if (e.getSource() == potion && inventory[12] > 0) { // Use
																		// Potion
																		// (Inventory)
				if (curHealth + 100 > health) {
					curHealth = health;
				} else {
					curHealth = curHealth + 100;
				}
				inventory[12]--;
				try {
					Game.items();
				} catch (Exception e1) {
				}
			} else if (e.getSource() == btn[0] && active == false) { // Melee
				active = true;
				eCurentHealth = eCurentHealth - (strength + meleeVal);
				enemyStats.setText(eCurentHealth + "/" + eHealth);
				new Thread(new Runnable() {
					public void run() {
						try {
							fpanel3.setBounds(50, 110, 300, 300);
							Thread.sleep(100);
							fpanel3.setBounds(0, 160, 300, 300);
							Thread.sleep(250);
							for (int i = 0; i < 3; i++) {
								Thread.sleep(100);
								fpanel2.setVisible(false);
								Thread.sleep(100);
								fpanel2.setVisible(true);
							}
							if (eCurentHealth <= 0) {
								for (int c = 1; c < 25; c++) {
									fpanel2.setBounds(450, 25 * c, 400, 300);
									Thread.sleep(50);
								}
								frame2.setVisible(false);
								frame2.dispose();
								money = money + (int)((Math.random()*300)+1);
								mdl.addElement("You now have $" + money);
							} else {
								Thread.sleep(900);
								Game.opponent();
							}
						} catch (Exception e) {
						}
						active = false;
					}
				}).start();
				if (2 >= (Math.random() * 99) + 1) {
					strength = strength + 2;
					mdl.addElement("Your Strength went up!");
				}
			} else if (e.getSource() == btn[1] && active == false) { // Ranged
				active = true;
				if (range.equals("bow") || range.equals("long")) {
					if (inventory[6] > 0) {
						inventory[6]--;
					} else {
						active = false;
					}
				} else if (range.equals("knife")) {
					if (inventory[3] > 0) {
						inventory[3]--;
					} else {
						active = false;
					}
				} else {
					active = false;
				}
				if (active == true) {
					eCurentHealth = eCurentHealth - (strength + rangeVal);
					enemyStats.setText(eCurentHealth + "/" + eHealth);
					new Thread(new Runnable() {
						public void run() {
							try {
								fpanel3.setBounds(50, 110, 300, 300);
								Thread.sleep(100);
								fpanel3.setBounds(0, 160, 300, 300);
								Thread.sleep(250);
								for (int i = 0; i < 3; i++) {
									Thread.sleep(100);
									fpanel2.setVisible(false);
									Thread.sleep(100);
									fpanel2.setVisible(true);
								}
								if (eCurentHealth <= 0) {
									for (int c = 1; c < 25; c++) {
										fpanel2.setBounds(450, 25 * c, 400, 300);
										Thread.sleep(50);
									}
									frame2.setVisible(false);
									frame2.dispose();
									if(boss1==true){
										mdl.addElement(action[xTest][yTest]);
										boss1=false;
									}else if (boss2 == true){
										mdl.addElement(action[xTest][yTest]);
										boss2=false;
									}else if (boss3 == true){
										JOptionPane.showMessageDialog(null, "YOU WON!", "YOU WON!", 0);
										frame1.setVisible(false);
										frame1.dispose();
										frame2.setVisible(false);
										frame2.dispose();
										Thread.sleep(5000);
										System.exit(0);
									}
									money = money + (int)((Math.random()*300)+1);
									mdl.addElement("You now have $" + money);
								} else {
									Thread.sleep(900);
									Game.opponent();
								}
							} catch (Exception e) {
							}
							active = false;
						}
					}).start();
				}
				if (2 >= (Math.random() * 99) + 1) {
					strength = strength + 2;
					mdl.addElement("Your Strength went up!");
				}
			} else if (e.getSource() == btn[2] && active == false) { // Use
																		// Potion
																		// (Combat)
				active = true;
				if (inventory[12] > 0) {
					if (curHealth + 100 > health) {
						curHealth = health;
					} else {
						curHealth = curHealth + 100;
					}
					inventory[12]--;
					playerStats.setText(curHealth + "/" + health);
					try {
						Thread.sleep(400);
						Game.opponent();
					} catch (Exception e1) {
					}
				}
				active = false;
			} else if (e.getSource() == btn[3] && active == false) { // Run
				active = true;
				if (agility + 40 >= (int) (Math.random() * 99) + 1) {
					frame2.setVisible(false);
					frame2.dispose();
					mdl.addElement("You got away.");
					mdl.addElement(" ");
				} else {
					try {
						Game.opponent();
					} catch (Exception e1) {
					}
				}
				active = false;
			}
			if (e.getSource() != editor && e.getSource() != btn[0]
					&& e.getSource() != btn[1] && e.getSource() != btn[2]
					&& e.getSource() != btn[3]) {
				frame1.requestFocus();
			}
		}
	}

	@SuppressWarnings("serial")
	private static class KeyHandler extends JFrame implements KeyListener {

		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_W && active == false) { // Up
				move = "up";
				try {
					Game.move();
				} catch (Exception e2) {
				}
			} else if (e.getKeyCode() == KeyEvent.VK_S && active == false) { // Down
				move = "down";
				try {
					Game.move();
				} catch (Exception e2) {
				}
			} else if (e.getKeyCode() == KeyEvent.VK_A && active == false) { // Right
				move = "left";
				try {
					Game.move();
				} catch (Exception e2) {
				}
			} else if (e.getKeyCode() == KeyEvent.VK_D && active == false) { // Left
				move = "right";
				try {
					Game.move();
				} catch (Exception e2) {
				}
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) { // Action Button
				if (move.equals("up")) {
					xTest = xSpot;
					yTest = ySpot - 1;
				} else if (move.equals("down")) {
					xTest = xSpot;
					yTest = ySpot + 1;
				} else if (move.equals("left")) {
					xTest = xSpot - 1;
					yTest = ySpot;
				} else if (move.equals("right")) {
					xTest = xSpot + 1;
					yTest = ySpot;
				}
				if (value[xTest][yTest] == 15) {
					if (chestHandler.isAlive() == false) {
						(new Thread(new Game())).start();
					}
				} else if (value[xTest][yTest] == 16) {

				} else if (value[xTest][yTest] == 17) {
					if(placeHolder[xTest][yTest].getIcon().equals("boss1.gif")){
						boss1=true;
						try{
							Game.fight();
						}catch(Exception e2){
						}
					}else if(placeHolder[xTest][yTest].getIcon().equals("boss2.gif")){
						boss2=true;
						try{
							Game.fight();
						}catch(Exception e2){
						}
					}else if(placeHolder[xTest][yTest].getIcon().equals("boss3.gif")){
						boss3=true;
						try{
							Game.fight();
						}catch(Exception e2){
						}
					}else{
					mdl.addElement(action[xTest][yTest]);
					mdl.addElement(" ");
					}
				} else if (value[xTest][yTest] == 18) {
					mdl.addElement(action[xTest][yTest]);
					mdl.addElement(" ");
				}
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}
}