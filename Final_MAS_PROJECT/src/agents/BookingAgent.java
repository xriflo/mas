package agents;

import environment.Cell;
import environment.Environment;
import utils.Entity;

public class BookingAgent {
	Entity stalkingEntity;
	RepresentativeAgent ra;
	Environment env;
	Cell currCell;
	
	public BookingAgent(RepresentativeAgent ra, Environment env) {
		this.ra = ra;
		this.env = env;
	}
}
