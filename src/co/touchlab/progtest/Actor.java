package co.touchlab.progtest;

import java.util.Random;

public class Actor {

	// type: Line(L), Still(S), Veer Left(VL), Veer Right(VR), Random(R)
	private String type;
	// Position is row, column. Zero based
	private int[] position = new int[2];
	// Direction is 0-7, indicating up, up-right, right, down-right, down,
	// down-left, left, up-left.
	private int direction;

	// The constructor when text input line has 4 values
	// Could be any type
	public Actor(String t, String r, String c, String d) {
		type = t;
		position[0] = Integer.parseInt(r);
		position[1] = Integer.parseInt(c);
		direction = Integer.parseInt(d);
	}

	// The constructor when text input line has 3 values
	// which is only possible for S and R, I assume 0 for direction but
	// it honestly meaningless for S and R
	public Actor(String t, String r, String c) {
		type = t;
		position[0] = Integer.parseInt(r);
		position[1] = Integer.parseInt(c);
		direction = 0;
	}

	// The constructor when text input line has 1 value
	// which is only possible for R, I assume 0 for position and direction
	public Actor(String t) {
		type = t;
		position[0] = 0;
		position[1] = 0;
		direction = 0;
	}
	
	// Make the actor do its task
	public void Act(int frame, int maxw, int maxh)
	{
		if (type.equals("R"))
		{
			Random r = new Random();
			position[0] = r.nextInt(maxw);
			position[1] = r.nextInt(maxh);
			printFrame(frame);
		}
		
		if (type.equals("S"))
		{
			printFrame(frame);
		}
		
		if (type.equals("L"))
		{
			getNewPosition(maxw,maxh);
			printFrame(frame);
		}
		
		// My understanding of VR/VL doesn't match the instruction's example VR/VL output
		// I am going to simply assume VR/VL changes directions every frame.
		if (type.equals("VL"))
		{
			getNewPosition(maxw,maxh);
			printFrame(frame);
			changeDirCounter();
		}
		
		if (type.equals("VR"))
		{
			getNewPosition(maxw,maxh);
			printFrame(frame);
			changeDirClock();
		}
	}
	
	private void getNewPosition(int maxr, int maxc)
	{
		if (direction == 0)
		{
			position[0] = position[0] - 1;
		} 
		else if (direction == 1)
		{
			position[0] = position[0] - 1;
			position[1] = position[1] + 1;
		}else if (direction == 2)
		{
			position[1] = position[1] + 1;
		}
		else if (direction == 3)
		{
			position[0] = position[0] + 1;
			position[1] = position[1] + 1;
		}
		else if (direction == 4)
		{
			position[0] = position[0] + 1;
		}
		else if (direction == 5)
		{
			position[0] = position[0] + 1;
			position[1] = position[1] - 1;
		}
		else if (direction == 6)
		{
			position[1] = position[1] - 1;
		}
		else if (direction == 7)
		{
			position[0] = position[0] - 1;
			position[1] = position[1] - 1;
		}
		
		checkBounds(maxr, maxc);
	}
	
	//Makes sure we are still within the grid
	private void checkBounds(int maxr, int maxc)
	{
		if (position[0] < 0)
			position [0] =0;
		
		if (position[0]>maxr)
			position[0]=maxr;
		
		if (position[1] < 0)
			position[1] =0;
		
		if (position[1] >maxc)
			position[1] = maxc;
	}
	
	public void changeDirCounter()
	{
		if (direction <= 0)
			direction = 7;
		else
			direction --;
	}
	
	public void changeDirClock()
	{
		if (direction >= 7)
			direction = 0;
		else
			direction ++;
	}
	
	public void printFrame(int f)
	{
		System.out.println(f+","+type+","+position[0]+","+position[1]);
	}
}
