package agents;

import jade.core.Agent;

public class EnvironmentAgent extends Agent{
	@Override
	protected void setup() {
		 System.out.println("Hello! Agent "+getName());
	}
}
