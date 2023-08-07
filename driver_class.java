/*===============================================================================================================================
Maze Solver Program
Elili Marakathalingasivam
11/20/2022
Java, Version: 2022-09 (4.25.0) 20220908-1902
=================================================================================================================================
Problem Definition - Read a maze inputted by the user. Identify the location of the mouse, cheese and exit within the 
maze (if present). Determine the shortest path from the mouse to the cheese and from the cheese to the exit. 
Input - File name to access the maze text file that needs to be navigated through
Output - The blank maze 
       - The shortest path from the mouse to the cheese 
       - The shortest path from the cheese to the exit
       - The total shortest path from the mouse to the cheese to the exit 
Process - Asks user if they wish to use the program
		- Receives file name and reads maze
		- Identifies starting location of the mouse (along with cheese and exit if present - if not, the program will indicate)
        - Checks each step to determine of it is safe for the mouse to move through (left, right, up or down) - recursively 
        - Counts the number of steps required to reach cheese 
        - Determines which path is shortest to reach cheese through recursion
        - Displays number of steps/units to reach cheese and the maze of the path
        - Repeats this process from the cheese to the exit.
        - Displays the total path from the mouse to the cheese to the exit
        - Asks the user again if they wish to use the program  
================================================================================================================================
*/
import java.io.*;

/*===============================================================================================================================
Maze_ Class
Elili Marakathalingasivam
11/20/2022
Java, Version: 2022-09 (4.25.0) 20220908-1902
=================================================================================================================================
Class 
This class determines and displays the shortest path from the mouse to the cheese, the cheese to the exit, and the total path from the 
mouse to the cheese to the exit using recursion.
=================================================================================================================================
List of Identifiers
*STATIC VARIABLES*
let maze[][] = a two-dimensional array that stores the maze file inputted by the user and updates to store the shortest path from 
			   the start to the end location specified when run <type String>
let visited[][] = a two-dimensional array that stores the coordinates of the paths taken and visited as true <type boolean>
let visitedShortest[][] = a two-dimensional array that stores the coordinates of the shortest path taken <type boolean>
================================================================================================================================
*/
public class Maze_Elili {

	static String maze[][] = new String [8][12];
	static boolean[][] visited;
	static boolean [][]visitedShortest;
	/*Declaration and instantiation of static variables*/
	
	/** main method:
	 * This procedural method is called automatically and is used to organize the calling of other methods defined in the class
	 * 
	 * List of Local Variables
	 * catcher - a variable used to indicate an error if the user inputs the file path incorrectly <type String>
	 * startRow - the row location of the starting point in a 2 dimensional array <type int>
	 * startCol - the column location of the starting point in a 2 dimensional array <type int>
	 * endRow - the row location of the ending point in a 2 dimensional array <type int>
	 * endCol - the column location of the ending point in a 2 dimensional array <type int>
	 * visitedShortestTotal[][] = the total/shortest path from the mouse to the cheese to the exit <type boolean>
	 * path1 - the length of units/steps taken to reach the cheese from the mouse <type int>
	 * path2 - the length of units/steps taken to reach the exit from the cheese <type int>
	 * 
	 * @param args <type String>
	 * @throws IOException
	 * @return void
	 */
	public static void main(String[] args) throws IOException {
		String catcher = "error"; // Declare and initialize String variable catcher
		int startRow, startCol, endRow, endCol; // Declare int variables startRow, startCol, endRow and endCol
		boolean visitedShortestTotal [][] = new boolean [8][12]; // Declare and initialize size of 2D boolean array visitedShortestTotal
		introToProgram(); // Calls method that prints information about program/welcome message
		while (useProgram().equals("YES")) { // calls method that asks user if they want to use the program, if it is 'yes' the program will loop until something else is inputted 
			System.out.println();
			Maze_Elili_PrivateStuff mE = new Maze_Elili_PrivateStuff(); // create object of Maze_Elili_PrivateStuff class to access methods of that class
			while (catcher.equals("error")) {
				try{
				maze = mE.arrayOfMaze(); // Asks user for maze file name. Checks if it is typed correctly and can be accessed, reads file and stores it in an array
				catcher = "placeholder"; // sets catcher to a new String value once condition is met
				} catch (FileNotFoundException e) {
					System.out.println("Looks like the file path was entered incorrectly. Please try again."); // Prints method if file name cannot be accessed
					}
				}
			if (catcher.equals("placeholder")) { // if catcher does not equal error String value, the program will run
				legend(); // Prints legend of the maze 
				System.out.println("_______________________________________________________________________");
				System.out.println();
				System.out.println("The blank maze is displayed below:");
				System.out.println();
				// Prints divider and message
				displayMaze(maze); // Calls method that prints the maze
				mE.getCheeseLocation(maze); // Calls getter method from other class that gets location of cheese
				mE.getExitLocation(maze); // Calls getter method from other class that gets location of exit
				if (mE.setCheeseRowLocation() == -1 && mE.setExitRowLocation() == -1)
					System.out.println("Shucks! The program can't find the cheese or an exit! Try adding an 'X' and a 'C'");
				else if (mE.setCheeseRowLocation() != -1 ) { // Calls setter method that finds cheese row. Makes sure that cheese position is within the original maze
					System.out.println("_______________________________________________________________________");
					System.out.println();
					// Prints divider
					mE.getMouseLocation(maze); // Calls getter method from other class that gets location of mouse
					startRow = mE.setMouseRowLocation(); // Initializes int variable startRow to an int value called from the mouse row setter method  
					startCol = mE.setMouseColLocation(); // Initializes int variable startCol to an int value called from the mouse column setter method  
					endRow = mE.setCheeseRowLocation(); // Initializes int variable endRow to an int value called from the cheese row setter method  
					endCol = mE.setCheeseColLocation(); // Initializes int variable endCol to an int value called from the cheese row setter method  
					int path1 = findShortestPathLength(maze,startRow,startCol, endRow,endCol); // Initializes path1 int variable to int returned from findShortestPathLength method
					if (path1!= -1) { // Makes sure that path exists from mouse to cheese in case blocked off but both locations exist
						System.out.println("The shortest path from the mouse (R) to the cheese (C) is " + path1 + " units:");
						System.out.println();
						// Displays message with path length
						maze[startRow][startCol] = "<"; // Sets R to < 
						maze[endRow][endCol] = "!"; // Sets cheese to ! (since the mouse moved to that location and ate the cheese)
						displayMaze(maze); // Displays path
						for (int i = 0; i < 8; i++) {
					  	      for (int j = 0; j < 12; j++) {
					  	    	  visited [i][j] = false; // resets visited array to all false (so method can be reused)
					  	    	  visitedShortestTotal [i][j] = visitedShortest [i][j]; // Stores shortestPath in new arrAy (to be used at the end when displaying the full path)
					  	    	  visitedShortest [i][j] = false; // resets visistedShortest to be reused in next method
					  	        if (maze[i][j].equals("#")) 
					  	        	maze [i][j]="."; // Resets maze so it goes back to its original layout without the path 
					  	      } 
					  	    }
						mE.getExitLocation(maze); // Calls getter method from other class that gets location of exit
						if (mE.setExitRowLocation() != -1 && path1!= -1) { // Calls setter method that finds cheese row. Makes sure that cheese position is within the original maze
																		   // also ensures that path from mouse to cheese exists (since the mouse needs to get to the cheese before the exit)
							System.out.println("_______________________________________________________________________");
							System.out.println();
							// Prints divider
							startRow = mE.setCheeseRowLocation(); // Initializes int variable startRow to an int value called from the cheese row setter method  
							startCol = mE.setCheeseColLocation(); // Initializes int variable startCol to an int value called from the cheese column setter method 
							mE.getExitLocation(maze);  // Calls getter method from other class that gets location of exit
							endRow = mE.setExitRowLocation(); // Initializes int variable endRow to an int value called from the exit row setter method  
							endCol = mE.setExitColLocation(); // Initializes int variable endCol to an int value called from the exit row setter method 
							int path2 = findShortestPathLength(maze,startRow,startCol, endRow,endCol);  // Initializes path2 int variable to int returned from findShortestPathLength method
							System.out.println("The shortest path from the cheese (C) to the exit (X) is " + path2 + " units:");
							System.out.println();
							// Displays message with path length
							maze[startRow][startCol] = "!"; // sets cheese to ! since that is where it was eaten
							maze[endRow][endCol] = ">"; // Sets exit to > (since the mouse moved to that location)
							displayMaze(maze); // Displays path
							System.out.println("_______________________________________________________________________");
							System.out.println();
							System.out.println("The TOTAL shortest path is " + (path2+path1) + " units:");
							System.out.println();
							// Prints border and message of shortest total path (adds previous paths together)
							for (int i = 0; i < 8; i++) {
						  	      for (int j = 0; j < 12; j++) {
						  	        if (visitedShortestTotal [i][j] == true) 
						  	        	maze [i][j]="#"; // Adds previous path from mouse to cheese to the maze
						  	      } 
						  	    }
							mE.getMouseLocation(maze); // Calls getter method from other class that gets location of mouse
							startRow = mE.setMouseRowLocation(); // Initializes int variable startRow to an int value called from the mouse row setter method  
							startCol = mE.setMouseColLocation(); // Initializes int variable startCol to an int value called from the mouse column setter method  
							maze[startRow][startCol] = "<"; // sets start to <
							displayMaze(maze); // Displays path from mouse to cheese to exit
							System.out.println("_______________________________________________________________________");
						} else { 
							System.out.println();
							System.out.println("Looks like there's no exit! Try adding a capital 'X' to the maze."); // Prints message when there is no exits identified in the maze
						}
					}
					
					else 
						System.out.println("Oh no! The mouse's path to the cheese seems to be fully blocked off!"); // Prints  message when all locations are identified but a path doesn't exist
				} else { 
					System.out.println();
					System.out.println("Looks like there's no cheese! Try adding a capital 'C' to the maze."); // Prints message when there is no cheese identified in the maze
				}
			}
		}
		System.out.println();
		System.out.println("Thank you for using the Maze Solver Program! Have a great day!"); // Prints end message
	} //end main method
	
	/** introToProgram method:
	 * This procedural method outputs all of the information regarding the program and how it will work for the user. 
	 * 
	 * @param none
	 * @return void
	 */
	static void introToProgram () {
		System.out.println("Hello! Welcome to the Maze Solver Program!");
		System.out.println();
		System.out.println("Here is how it will work...");
		System.out.println("1. Input the file path to your text file maze");
		System.out.println("2. The program will display your blank maze");
		System.out.println("3. If there is a cheese and an exit, the maze will display the shortest");
		System.out.println("   path from the mouse to the cheese, from the cheese to the exit, and  ");
		System.out.println("   from the mouse to the cheese to the exit.");
		// Displays introduction information to program
	}// end output
	
	/** useProgram method:
	 * This functional method reads user input and returns that value fully capitalized (to account for case sensitivity) 
	 * 
	 * List of Local Variables 
	 * br = a BufferedReader object that reads user input <type BufferedReader>
	 * response = the input for whether the user wishes to continue with or end the program <type String>
	 * 
	 * @param none
	 * @throws IOException
	 * @return the response variable fully capitalized
	 */
	static String useProgram() throws IOException {
		String response; // creates String response local variable
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  // Declaration and Instantiation of a BufferedReader object
		System.out.println();
		System.out.println("Would you like to give this program a go? Type 'yes'. ");
		System.out.println("If not, type anything else to end it. "); 
		// Prompts user for input and displays instructions
		response = br.readLine(); // Reads and stores String response in variable
		return response.toUpperCase(); // Returns upper case String response to take case sensitivity into account
	}// end useProgram method
	
	/** introToProgram method:
	 * This procedural method outputs the legend of the maze, indicating how it works and what the displayed solution means. 
	 * 
	 * @param none
	 * @return void
	 */
	static void legend() {
		System.out.println("_______________________________________________________________________");
		System.out.println();
		System.out.println("Legend:");
		System.out.println();
		System.out.println("B = Barrier");
		System.out.println("R = Mouse/Rat");
		System.out.println("C = Cheese");
		System.out.println("X = Exit");
		System.out.println(". = Potential path");
		System.out.println("# = Shortest path");
		System.out.println("! = Where cheese was eaten");
		System.out.println("< = Start point");
		System.out.println("> = End point");
		// Displays legend information to make path taken very clear
