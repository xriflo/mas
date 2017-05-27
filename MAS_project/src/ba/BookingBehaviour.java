package ba;

import java.awt.image.SinglePixelPackedSampleModel;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.introspection.AddedBehaviour;
import jade.lang.acl.ACLMessage;

public class BookingBehaviour extends CyclicBehaviour{
	Agent a;
	public BookingBehaviour(Agent a) {
		super();
		this.a = a;
	}
	@Override
	public void action() {
		receiveMessages();
	}
	
	public void sendMessages() {
		
	}
	
	public void receiveMessages() {
		ACLMessage msg = myAgent.receive();
		if(msg!=null) {
			System.out.println("Message is: "+msg.getContent());
		}
	}
}
