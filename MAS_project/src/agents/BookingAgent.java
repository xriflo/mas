package agents;

import jade.core.Agent;

public class BookingAgent extends Agent{
	
	@Override
	protected void setup() {
		 System.out.println("Hello! Agent "+getName());
	}
}
