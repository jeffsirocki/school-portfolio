To launch the "Player" application:
	
	1. In Eclipse, navigate to:
		src/player/SplashPlayer.java
	2. Run As "Java Application"
	
	**Try resizing the game play window**
	
To launch the Builder application:
	
	1.In Eclipse, navigate to:
		src/builder/SplashBuilder
	2. Run As "Java Application"
	
For Developers:

	This SixesWILD application was designed to be flexible and easy to update/modify.
	In order to create new levels, game modes, and behaviors, you need only write code for the additional functionality you wish to add.
	No modifications are necessary to the core framework. The following are customizable features:
	
	-Level Type:
		Level types can be added by creating a class which extends the AbstractLevel class.
		Level type reflects the parameters that each level of that type must have.
			What kinds of squares are available to play?
			What kind of game is required to play this level?
			What unique/additional parameters does this level have?
		Level types MUST have a 'public static String type()' function to display them properly in menus
	
	-Square Types:
		Squares determine the behavior of the tiles on the board. 
		New Square types with different behaviors can be added by creating a class which extends AbstractSquare or AbstractContainer
		Squares which hold tiles and can pass tiles to other squares should additionally implement the ISource interface
		Each square has a getDrawer() method which returns an AbstractSquareDrawer object.
			If you wish to override the default square image drawn, you can create a new Drawer class which extends AbstractSquareDrawer
	
	-Game Types: (NOT YET IMPLEMENTED)
		Game types determine the highest level behavior of the game, such as win and loss conditions.
		Games store and manage parameters such as score information about the current game session.
		Different game types may have different rules.