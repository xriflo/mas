package environment;

import java.util.ArrayList;

import agents.Agent;
import agents.BookingAgent;
import agents.BookingAgent2;
import agents.RepresentativeAgent;

public class Environment {
	public GridSpace grid;
	public ArrayList<RepresentativeAgent> ras;
	public ArrayList<BookingAgent2> bas;
	
	public Environment(GridSpace grid) {
		this.grid = grid;
		this.ras = new ArrayList<RepresentativeAgent>();
		this.bas = new ArrayList<BookingAgent2>();
	}
	
	public ArrayList<BookingAgent2> getRoommatesForBA(BookingAgent ag) {
		ArrayList<BookingAgent2> roommates = new ArrayList<BookingAgent2>();
		for(BookingAgent2 ba:bas) {
			if(!ba.equals(ag) && ba.currCell.equals(ag.currCell))
				roommates.add(ba);
		}
		return roommates;
	}
}
