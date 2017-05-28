package environment;

import java.util.ArrayList;

import agents.Agent;
import agents.BookingAgent;
import agents.RepresentativeAgent;

public class Environment {
	public GridSpace grid;
	public ArrayList<RepresentativeAgent> ras;
	public ArrayList<BookingAgent> bas;
	
	public Environment(GridSpace grid) {
		this.grid = grid;
		this.ras = new ArrayList<RepresentativeAgent>();
		this.bas = new ArrayList<BookingAgent>();
	}
	
	public ArrayList<BookingAgent> getRoommatesForBA(BookingAgent ag) {
		ArrayList<BookingAgent> roommates = new ArrayList<BookingAgent>();
		for(BookingAgent ba:bas) {
			if(!ba.equals(ag) && ba.currCell.equals(ag.currCell))
				roommates.add(ba);
		}
		return roommates;
	}
}
