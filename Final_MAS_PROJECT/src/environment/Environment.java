package environment;

import java.util.ArrayList;

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
}
