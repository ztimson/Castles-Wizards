=========================================================================================================================================================
....@@@@@@@.....@......@@@...@@@@@@@..@......@@@@@@...@@@............@.....@...@..@@@..........@.........@..@..@@@@@@@@@.....@.....@@@@@...@@@.....@@@...
...@.......@...@.@....@...@.....@.....@......@.......@...@..........@.@....@@..@..@..@.........@.........@..@.........@@....@.@....@....@..@..@...@...@..
..@............@.@.....@........@.....@......@........@.............@.@....@@..@..@...@.........@.......@...@.......@@......@.@....@....@..@...@...@.....
..@...........@...@.....@.......@.....@......@@@.......@...........@...@...@.@.@..@...@.........@...@...@...@.....@@.......@...@...@...@...@...@....@....
..@...........@@@@@......@......@.....@......@..........@..........@@@@@...@.@.@..@...@..........@.@.@.@....@....@.........@@@@@...@@@@....@...@.....@...
...@......@..@.....@..@...@.....@.....@......@.......@...@........@.....@..@..@@..@..@...........@.@.@.@....@..@@.........@.....@..@...@...@..@...@...@..
....@@@@@@...@.....@...@@@......@.....@@@@@..@@@@@@...@@@.........@.....@..@..@@..@@@.............@...@.....@..@@@@@@@@@..@.....@..@....@..@@@.....@@@...
=========================================================================================================================================================
Name: Zakary Timson																													Date: June 21, 2013

Castles and Wizards is a game engine that reads pre-made text files to create its enviroment. Chests, Conversations, Inventory, Maps, Character Traits
are all saved this way. The game comes with a basic map editor to further develop this concept. Maps used in the game follow a ##.txt format (00.txt
being the spawn) The default controls to move are WASD and SPACE as an action key to talk to shop keepers, open chests, talk to people and read signs.
When creating a new character you must type in the Name of the new character and click new game, after which you can use radio buttons to pick the
gender and then use the text fields to adjust player traits or skills (They must add up to 20) Health is used to boost your health (100+5p), Strength
boosts the attack (20+2p), Observation is used to see hidden paths to get across the world quickly (p), and agility determens the chances of dodging an
attack during a battle [(5+0.25p)/100], once this is done simply hit new Game again and the player will automaticly be taken to the game screen. To
load a game simply type in the name of the character and hit load (not case sensitive) if the character can not be found the load button will revert 
back to its normal stanse until it is pressed again. Music is set on loop in the background (14 tracks). The games story line can only be discovered 
through talking to people. The more you talk to them the more the player will understand about what is going on if the player does not talk to anyone 
it is more than likely the player will never figure out how to the beat the game. The map does not record the players current position, it only shows 
the different areas, signs can be found around the world that will use the different names of the areas to help the player locate there current position. 
All dialog (Talking to people or reading signs) can be found in the top right box. But be aware, just like the player the character also has a bad 
memmory, if the game is restarted the dialog panel will be wiped clean. In the inventory screen the player can pick his characters weapons simply by
clicking on the item in the items box and clicking the picture of were it can be equiped. When exploring the enviroment two areas can be walked on, 
first is the path, and second is off path were steps will be counted and after a random amount a battle will triger and a random eneamy will attack 
based on the players skill level. after defeting 3 main boss's the game will come to an end and credits will be displayed. Because of the complexity of 
the coding comments are not specific. They are only used to define what a block of code does. (Too many lines to explain everything) I hope you enjoy all
of the work and time I have put into this, Have a great summer!

=========================================================================================================================================================
Known Bugs                                  Weapons                      Armor & Items       Enimies
=========================================================================================================================================================
Clicking on a JList will shift the focus    Staff +5					 Shield +10			 Wolf 50 10
and the character will not be able to       Iron Sword +10				 Steel Shield +20	 Bandit 60 10-20
move. Work Around: click on a button,       Steel Sword +20				 Iron Armor +10		 Orc 100 20-30
they are set to refocus on JFrame.          Throwing Knife +30 (Ammo)	 Steel Armor +20	 Wizard 150 50 (High miss chance)
											Bow +40						 Helmet + 10		 Boss1 200 50
Can't create a character with space	in		Long Bow +50				 Potion +100		 Boss2 300 50
their name.									Arrow (Ammo)									 Boss3 500 50-60
																							 Skeleton
Lag time when character moves for the
first time.
