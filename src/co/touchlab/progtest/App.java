package co.touchlab.progtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
	private static final String FILENAME = "src\\testgrid.txt";
	private static ArrayList<Actor> Actors;
	private static int gridwidth;
	private static int gridheight;
	private static int numframes;
    
    public static void main( String[] args )
    {
    	readText();
    	readInput(); 
    	
    	Grid g = new Grid(gridwidth, gridheight, numframes, Actors);
    	g.runFrame();
    }
    
    // Reads in testgrid.txt which resides in src folder
    private static void readText()
    {
        BufferedReader br = null;
        FileReader fr = null;
        
        try {
     	   fr = new FileReader(FILENAME);
     	   br = new BufferedReader(fr);
     	   
     	   String line;
     	   Actors = new ArrayList<Actor>();
     	   
     	   //populates the actor list with many actors
     	   while ((line = br.readLine()) != null)
     	   {
     		  //System.out.println(line);
     		  processText(line);
     	   }
     	   
        }catch (IOException e)
        {
     	   System.out.println("Error reading textfile of actors, program exiting...");
     	   e.printStackTrace();
     	   System.exit(0);
        }
    }
    
    // Processes a line of text into an actor and added to Arraylist
    // I make the assumption that the input text file will only contain
    // lines that follow the format of 
    // type, row, height, direction
    // type, row, height
    // type
    private static void processText(String line)
    {
    	String[] list = line.split(",");
    	
    	if (Actors != null)
    	{
    		// There can only be 3 different cases for each line:
    		// R which can have length of 4, 3, or 1 
    		// S which can have length of 4 or 3
    		// L, VR, VL which must have 4 length
    		
    		// Line length is 1 case
    		if (list.length == 1)
    		{
    			Actors.add(new Actor(list[0]));
    		}
    			
    		// Line length is 3 case
    		if (list.length == 3)
    		{
    			Actors.add(new Actor(list[0], list[1], list[2]));
    		}
    		
    		// Line length is 3 case
    		if (list.length == 4)
    		{
    			Actors.add(new Actor(list[0], list[1], list[2], list[3]));
    		}
    	}

    }
    
    // Reads in width and height of grid, also # of frames
    private static void readInput()
    {
        try{
            Scanner reader = new Scanner(System.in);
     	    System.out.println("Please enter the width of grid: ");
            gridwidth = reader.nextInt();
            System.out.println("Please enter the height of grid: ");
            gridheight = reader.nextInt();
            System.out.println("Please enter the number of frames: ");
            numframes = reader.nextInt();
        }catch (Exception e){
     	   System.out.println("Error reading user input, program exiting...");
     	   e.printStackTrace();
     	   System.exit(0);
        }
    }
}
