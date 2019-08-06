/* Name: Zak Timson
 * Date: May 20, 2013
 * File: mapEditor.java
 * ==============================================
 * Description: This program handles creating and
 * editing maps on a 25x25 grid for Castles and
 * Dragons.
 */

// Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;


// main class
public class mapEditor {

	// Container
	static JFrame frame1 = new JFrame("Map Editor"); // Window
	static JPanel panel1 = new JPanel(new FlowLayout()); // Tile Selecting panel
	static JPanel panel2 = new JPanel(new GridLayout(25, 25)); // Display tiles

	// Root Directory
	static String dir = (new File(" ").getAbsolutePath().trim()) + "Objects/";

	// Radio Buttons for tile types
	static String[] item = new String[] { "Bricks", "Stone", "StoneSlab",
			"Tree", "SnowPacked", "StonePath", "Floor", "Wood", "Dirt", "Ice",
			"Snow", "Bolder", "Fire", "Lava", "Water", "Chest", "Shop", "Sign",
			"Person", "StoneH", "StoneV", "LadderDown", "LadderUp", "Blood",
			"Bones" };
	static String[] id = new String[item.length]; // Assigns a corresponding
													// number to all tiles
	static JRadioButton[] rb = new JRadioButton[item.length]; // radio buttons
																// for the tiles

	// GUI for map editor
	static JTextField tfld1 = new JTextField(".txt"); // save/load the map
	static JButton btn1 = new JButton("Save"); // button to save
	static JButton btn2 = new JButton("Load"); // button to load
	static JButton[][] button = new JButton[25][25]; // buttons for the tiles
	static String[][] value = new String[25][25]; // corresponding array to hold
													// tile values

	// icons
	static ImageIcon[] image = new ImageIcon[item.length]; // image icons

	// main method
	public static void main(String[] args) {

		// Button Handler
		ButtonHandler onClick = new ButtonHandler();

		// Radio Button group
		ButtonGroup group1 = new ButtonGroup();

		// reset panels
		panel2.removeAll();
		panel2.setVisible(false);
		panel1.removeAll();
		panel1.setVisible(false);

		// Tile selector panel1
		for (int i = 0; i < item.length; i++) {
			if (i == 0) {
				JLabel lbl1 = new JLabel("Wall__________________________");
				lbl1.setForeground(new Color(0, 0, 0, 255));
				panel1.add(lbl1);
			} else if (i == 4) {
				JLabel lbl2 = new JLabel("Path__________________________");
				lbl2.setForeground(new Color(0, 0, 0, 255));
				panel1.add(lbl2);
			} else if (i == 8) {
				JLabel lbl3 = new JLabel("Off Path______________________");
				lbl3.setForeground(new Color(0, 0, 0, 255));
				panel1.add(lbl3);
			} else if (i == 11) {
				JLabel lbl4 = new JLabel("Obsticals_____________________");
				lbl4.setForeground(new Color(0, 0, 0, 255));
				panel1.add(lbl4);
			} else if (i == 15) {
				JLabel lbl5 = new JLabel("Interact______________________");
				lbl5.setForeground(new Color(0, 0, 0, 255));
				panel1.add(lbl5);
			} else if (i == 19) {
				JLabel lbl6 = new JLabel("Map___________________________");
				lbl6.setForeground(new Color(0, 0, 0, 255));
				panel1.add(lbl6);
			} else if (i == 23) {
				JLabel lbl7 = new JLabel("Misc__________________________");
				lbl7.setForeground(new Color(0, 0, 0, 255));
				panel1.add(lbl7);
			}

			// Group Options and finalize
			rb[i] = new JRadioButton(item[i]);
			image[i] = new ImageIcon(dir + "/Images/Tiles/" + item[i] + ".jpg");
			rb[i].setPreferredSize(new Dimension(120, 25));
			group1.add(rb[i]);
			panel1.add(rb[i]);
		}

		// Load file images
		image[11] = new ImageIcon(dir + "/Images/Tiles/" + item[11] + ".gif");
		image[12] = new ImageIcon(dir + "/Images/Tiles/" + item[12] + ".gif");
		image[15] = new ImageIcon(dir + "/Images/Tiles/" + item[15] + ".gif");
		image[16] = new ImageIcon(dir + "/Images/Tiles/" + item[16] + ".gif");
		image[17] = new ImageIcon(dir + "/Images/Tiles/" + item[17] + ".gif");
		image[18] = new ImageIcon(dir + "/Images/Tiles/" + item[18] + ".gif");
		image[21] = new ImageIcon(dir + "/Images/Tiles/" + item[21] + ".gif");
		image[22] = new ImageIcon(dir + "/Images/Tiles/" + item[22] + ".gif");
		image[23] = new ImageIcon(dir + "/Images/Tiles/" + item[23] + ".gif");
		image[24] = new ImageIcon(dir + "/Images/Tiles/" + item[24] + ".gif");

		// Assign values to tiles
		for (int i = 0; i < item.length; i++) {
			if (i < 10) {
				id[i] = "0" + Integer.toString(i);
			} else {
				id[i] = Integer.toString(i);
			}
		}

		// GUI Components to make save and load buttons
		JLabel lbl8 = new JLabel("==============================");
		lbl8.setForeground(new Color(0, 0, 0, 255));
		panel1.add(lbl8);
		tfld1.setPreferredSize(new Dimension(201, 25));
		panel1.add(tfld1);
		btn1.setPreferredSize(new Dimension(99, 25));
		btn1.addActionListener(onClick);
		panel1.add(btn1);
		btn2.setPreferredSize(new Dimension(99, 25));
		btn2.addActionListener(onClick);
		panel1.add(btn2);
		panel1.setPreferredSize(new Dimension(250, 750));
		panel1.setBackground(new Color(150, 0, 0, 255));

		// Finalizes window
		panel2.setVisible(true);
		panel1.setVisible(true);

		// Load Tiles
		for (int y = 0; y < 25; y++) {
			for (int x = 0; x < 25; x++) {
				button[x][y] = new JButton();
				button[x][y].setPreferredSize(new Dimension(30, 30));
				button[x][y].addActionListener((ActionListener) onClick);
				button[x][y].setIcon(image[3]);
				panel2.add(button[x][y]);
				value[x][y] = "03";
			}
		}

		// Display Frame
		panel2.setPreferredSize(new Dimension(625, 750));
		panel2.setBackground(new Color(0, 0, 0, 255));
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setSize(new Dimension(1075, 750));
		frame1.add(panel1, BorderLayout.WEST);
		frame1.add(panel2, BorderLayout.CENTER);
		frame1.setVisible(true);
	} // close main method

	private static class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btn1) { // Save Map
				PrintWriter out = null;
				final File file1 = new File(dir + "Maps/" + tfld1.getText()); // write
																				// location

				try {
					out = new PrintWriter(file1);
				} catch (Exception e2) {
				}

				for (int y = 0; y < 25; y++) {
					for (int x = 0; x < 25; x++) {
						out.print(value[x][y] + " "); // write cell
					}
					out.println(""); // next row
				}
				out.close();
			}
			if (e.getSource() == btn2) { // Load a map
				String map = tfld1.getText(); // file name
				Scanner scan1 = null;
				try {
					scan1 = new Scanner(new File(dir + "Maps/" + map)); // open
																		// scanner
				} catch (FileNotFoundException e1) {
				}

				for (int y = 0; y < 25; y++) {
					for (int x = 0; x < 25; x++) {
						String tile = scan1.next(); // scan cell
						for (int i = 0; i < item.length; i++) {
							if (id[i].equals(tile)) { // figure out what tile it
														// is
								value[x][y] = id[i]; // set value
								button[x][y].setIcon(image[i]); // set
																// corresponding
																// image
							}
						}
					}
				}
				scan1.close();// Close Scanner
			} else { // Tile handler to change icon of selected Tile
				for (int y = 0; y < 25; y++) {
					for (int x = 0; x < 25; x++) {
						if (button[x][y] == e.getSource()) {
							for (int i = 0; i < item.length; i++) {
								if (rb[i].isSelected()) {
									button[x][y].setIcon(image[i]);
									value[x][y] = id[i];
								}
							}
						}
					}
				}
			} // Close Button identifier
		} // Close Action Performed
	} // Close Button Listener
}// Close class