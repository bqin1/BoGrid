package co.touchlab.progtest;

import java.util.ArrayList;

public class Grid {
	
	private ArrayList<Actor> Actors;
	private int gridwidth;
	private int gridheight;
	private int numframes;
	
	public Grid(int w, int h, int f, ArrayList<Actor> a)
	{
		Actors = a;
		gridwidth = w;
		gridheight = h;
		numframes = f;
	}
	
	// Makes each actor do its task for number of frames
	public void runFrame()
	{
		//0th frame is non-moving
		for (int c=0;c<Actors.size();c++)
			Actors.get(c).printFrame(0);
		
		//Act on each frame
		for (int c=1;c<numframes;c++)
		{
			for (int d=0;d<Actors.size();d++)
			{
				Actors.get(d).Act(c,gridwidth, gridheight);
			}
		}
	}
}
