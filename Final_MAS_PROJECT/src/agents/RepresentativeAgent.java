package agents;

import java.util.ArrayList;

import utils.Entity;

public class RepresentativeAgent {
	Entity entity;
	Integer no_be;
	ArrayList<BookingAgent> be;
	
	public RepresentativeAgent(Entity entity) {
		this.no_be = 0;
		this.entity = entity;
		this.be = new ArrayList<BookingAgent>();
	}

	public void setNo_be(Integer no_be) {
		this.no_be = no_be;
		for(int i=0; i<no_be; i++) {
			be.add(new BookingAgent());
		}
	}	
}
