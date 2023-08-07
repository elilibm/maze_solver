/*===============================================================================================================================
Maze_Elili_PrivateStuff Class
Elili Marakathalingasivam
11/20/2022
Java, Version: 2022-09 (4.25.0) 20220908-1902
=================================================================================================================================
Class 
This class determines the user input file used to create, read and navigate the maze. This class also privately stores the 
location of the mouse, cheese and exit (if they exist) within the inputted maze. 
=================================================================================================================================
List of Identifiers
*STATIC VARIABLES (PRIVATE)*
let mouse[] = a one-dimensional array that stores the position/location of the mouse within the original maze <type int>
let cheese[] = a one-dimensional array that stores the position/location of the cheese within the original maze <type int>
let exit[] = a one-dimensional array that stores the position/location of the exit within the original maze <type int>
================================================================================================================================
*/
import java.io.*;
public class Maze_Elili_PrivateStuff {
	
	private static int [] mouse = new int[2];
	private static int [] cheese = {-1,-1};
	private static int [] exit = {-1,-1};
	/*Declaration and instantiation of static variables*/
	
	/** arrayOFMaze method:
	 * This functional method asks the user to input a file path and reads each line of the file and stores all variables in 
	 * that line. Then the temp array breaks up the line into its individual values and store that in the maze array
	 * This maze array, as a result, has all vAlues of the file accurately stored in it, each with a unique location
	 * This entire maze is returned.
	 * 
	 * List of Local Variables
	 * maze = two-dimensional array that stores the maze file inputted by the user and updates to store the shortest path from 
	 * 		   the start to the end location specified when run <type String>
	 * br = a BufferedReader object that reads user input <type BufferedReader>
	 * filePath = the name of the file inputted by the user <typwe String>
	 * input = a BufferedReader object used for keyboard input to get and read file <type BufferedReader>
	 * line = the values of one line of the read maze <type String>
	 * temp = a one dimensional array used to store each value in the line in a position <type String>
	 * 
	 * @param none
	 * @throws IOException
	 * @return maze - two-dimensional array that stores the maze file inputted by the user and updates to store the shortest path from 
	 * 				  the start to the end location specified when run <type String>
	 */
	protected static String[][] arrayOfMaze() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Declaration and Instantiation of a BufferedReader object
		String [][] maze = new String [8][12]; // Initializes the size of the maze array
		String line, filePath; // Declares String variable line and filePath
		System.out.println("Please enter the full file path name only (without .txt)");
		System.out.println("NOTE: The file must be in the java project file.");
		// Displays instructions
		filePath = br.readLine(); // Reads user input and stores in String variable filePath
		BufferedReader input; // Creates BufferedReader object named input
		input = new BufferedReader(new FileReader(filePath +".txt")); //input of file name that must already be in java project folder
		int i = 0; // Sets the row number of the maze to 0 (first row)
		String temp[] = new String[12]; // Creates a temporary array to store the value of each row
		line = input.readLine(); // Reads each line of the inputted maze file
		while(line!=null) { // Makes sure there is text in the line read
			temp=line.split(" "); // Splits each line onto individual characters and removes spaces
			for (int j=0;j<12;j++){
				maze [i][j] = temp[j]; // Sets values of characters into positions of the maze
			}
			i++; // Increases and goes to the next row (so that all rows are filled)
			line = input.readLine(); // Reads this next line of the maze file
		}
		return maze; // Returns complete 2D array of the inputted maze
	}// end arrayOFMaze method
	
	/** locationOfMouse method:
	 * This procedural method searches the maze for the mouse location and and stores the position/coordinates of the mouse 
	 * in a separate one-dimensional array.
	 * 
	 * @param maze - two-dimensional array that stores the maze file inputted by the user and updates to store the shortest path from 
	 * 				 the start to the end location specified when run <type String>
	 * @return mouse[] - a one-dimensional array that stores the position/location of the mouse within the original maze <type int>
	 */
	private static int[] locationOfMouse(String [][] maze) { 
		for (int i=0; i<8; i++) {
			for (int j=0;j<12;j++) {

				if (maze[i][j].equals("R")||maze[i][j].equals("M")||maze[i][j].equals("r")||maze[i][j].equals("m")) { // Searches array for R or M to find location of mouse
					mouse[0] = i; // Assigns row position of mouse to mouse [0]
					mouse[1] = j; // Assigns column position of mouse to mouse [1]
				}
				}
			}
		return mouse; // Returns 1D mouse array
	}// end locationOfMouse method
	

	/**getMouseLocation method:
	 * This functional method is a getter method that gets the private mouse array stored in the locationOfMouse method
	 * 
	 * @param maze - two-dimensional array that stores the maze file inputted by the user and updates to store the shortest path from 
	 * 				 the start to the end location specified when run <type String>
	 * @return locationOFMouse(maze) = mouse[] - a one-dimensional array that stores the position/location of the mouse within the 
	 * 											 original maze <type int>
	 */

	int[] getMouseLocation(String[][]maze){ 
		return locationOfMouse(maze);
	} // end getMouseLocation method
	
	/**setMouseRowLocation method:
	 * This functional method is a setter method that sets the return value to a specific integer of the array
	 * This integer value is the row position of the mouse in the maze array
	 * 
	 * @param none 
	 * @return mouse[0] - a one-dimensional array that stores the position/location of the mouse within the 
	 * 					  original maze <type int>
	 */
	int setMouseRowLocation () {
		return mouse[0];
	} // end setMouseRowLocation method
	
	/**setMouseColLocation method:
	 * This functional method is a setter method that sets the return value to a specific integer of the array
	 * This integer value is the column position of the mouse in the maze array
	 * 
	 * @param none 
	 * @return mouse[1] - a one-dimensional array that stores the position/location of the mouse within the 
	 * 					  original maze <type int>
	 */
	int setMouseColLocation () {
		return mouse[1]; 
	} // end setMouseColLocation method
	
	/** locationOfCheese method:
	 * This procedural method searches the maze for the cheese location and and stores the position/coordinates of the cheese 
	 * in a separate one-dimensional array.
	 * 
	 * @param maze - two-dimensional array that stores the maze file inputted by the user and updates to store the shortest path from 
	 * 				  the start to the end location specified when run <type String>
	 * @return cheese[] - a one-dimensional array that stores the position/location of the cheese within the original maze <type int>
	 */
	private static int[] locationOfCheese(String[][] maze){ 
		for (int i=0; i<8; i++) {
			for (int j=0;j<12;j++) {

				if (maze[i][j].equals("C")||maze[i][j].equals("c")) { // Searches array for C to find location of cheese
					cheese [0] = i; // Assigns row position of cheese to cheese [0]
					cheese [1] = j; // Assigns column position of cheese to cheese [1]
				}
				}
			}
		return cheese; // returns 1D cheese array
		
	} // end locationOfCheese method
	
	/**getCheeseLocation method:
	 * This functional method is a getter method that gets the private cheese array stored in the locationOfCheese method
	 * 
	 * @param maze - two-dimensional array that stores the maze file inputted by the user and updates to store the shortest path from 
	 * 				 the start to the end location specified when run <type String>
	 * @return locationOfCheese(maze) = cheese[] - a one-dimensional array that stores the position/location of the cheese within the 
	 * 											   original maze <type int>
	 */
	int[] getCheeseLocation(String[][]maze) { 
		return locationOfCheese(maze);
	} // end getCheeseLocation method
	
	/**setCheeseRowLocation method:
	 * This functional method is a setter method that sets the return value to a specific integer of the array
	 * This integer value is the row position of the cheese in the maze array
	 * 
	 * @param none 
	 * @return cheese[0] - a one-dimensional array that stores the position/location of the cheese within the 
	 * 					  original maze <type int>
	 */
	int setCheeseRowLocation () {
		return cheese[0];
	} // end setCheeseRowLocation method
	
	/**setCheeseColLocation method:
	 * This functional method is a setter method that sets the return value to a specific integer of the array
	 * This integer value is the column position of the cheese in the maze array
	 * 
	 * @param none 
	 * @return cheese[1] - a one-dimensional array that stores the position/location of the cheese within the 
	 * 					  original maze <type int>
	 */
	int setCheeseColLocation () {
		return cheese[1]; 
	} // end setCheeseColLocation method
	
	/** locationOfExit method:
	 * This procedural method searches the maze for the exit location and and stores the position/coordinates of the exit 
	 * in a separate one-dimensional array.
	 * 
	 * @param maze - two-dimensional array that stores the maze file inputted by the user and updates to store the shortest path from 
	 * 				  the start to the end location specified when run <type String>
	 * @return exit[] - a one-dimensional array that stores the position/location of the exit within the original maze <type int>
	 */
	private static int[] locationOfExit(String[][] maze){ 
		for (int i=0; i<8; i++) {
			for (int j=0;j<12;j++) {

				if (maze[i][j].equals("X")|| maze[i][j].equals("x")) { // Searches array for E to find location of exit
					exit [0] = i; // Assigns row position of exit to exit [0]
					exit [1] = j; // Assigns column position of exit to exit [1]
				}
				}
			}
		return exit; // Returns 1D exit array
	} // end locationOfExit method
	
	/**getExitLocation method:
	 * This functional method is a getter method that gets the private exit array stored in the locationOfCheese method
	 * 
	 * @param maze - two-dimensional array that stores the maze file inputted by the user and updates to store the shortest path from 
	 * 				 the start to the end location specified when run <type String>
	 * @return locationOfExit(maze) = exit[] - a one-dimensional array that stores the position/location of the exit within the 
	 * 										   original maze <type int>
	 */
	int[] getExitLocation(String[][]maze) throws IOException{ 
		return locationOfExit(maze);
	} // end getExitLocation method
	
	/**setExitRowLocation method:
	 * This functional method is a setter method that sets the return value to a specific integer of the array
	 * This integer value is the row position of the exit in the maze array
	 * 
	 * @param none 
	 * @return exit[0] - a one-dimensional array that stores the position/location of the exit within the 
	 * 					 original maze <type int>
	 */
	int setExitRowLocation () {
		return exit[0];
	} // end setExitRowLocation method
	
	/**setExitColLocation method:
	 * This functional method is a setter method that sets the return value to a specific integer of the array
	 * This integer value is the column position of the exit in the maze array
	 * 
	 * @param none 
	 * @return exit[1] - a one-dimensional array that stores the position/location of the exit within the 
	 * 					 original maze <type int>
	 */
	int setExitColLocation () {
		return exit[1]; 
	} // end setExitColLocation method
} // end Maze_Elili_PrivateStuff class
