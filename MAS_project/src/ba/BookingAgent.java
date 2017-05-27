package ba;

import java.util.UUID;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class BookingAgent extends Agent{
	String uniqueID;
	
	@Override
	protected void setup() {
		uniqueID = UUID.randomUUID().toString();
		System.out.println("Hello! Agent "+getName());
		addBehaviour(new BookingBehaviour(this));
		
		addBehaviour(new Ac);
	}
}
