package agents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import environment.Cell;
import environment.Environment;
import tools.Message;
import tools.Settings;
import utils.Constraint;
import utils.Entity;

public class BookingAgent extends Agent {
	Float time;
	public Queue<Message> messages;
	public Entity stalkingEntity;
	public RepresentativeAgent ra;
	public Environment env;
	public Cell currCell;
	public Cell currReservationCell;
	public ArrayList<Cell> memoryCells;
	public ArrayList<BookingAgent> partners;
	public ArrayList<Constraint> constraints;
	public ArrayList<Constraint> constraintsOfBrothers;
	public ArrayList<Constraint> constraintsOfPartners;
	
	public BookingAgent(RepresentativeAgent ra, Environment env) {
		this.time = Settings.EPSILON;
		this.ra = ra;
		this.env = env;
		this.memoryCells = new ArrayList<Cell>();
		this.partners = new ArrayList<BookingAgent>();
		this.messages = new LinkedList<Message>();
		this.currReservationCell = null;
		this.constraints = new ArrayList<Constraint>();
		this.constraintsOfBrothers = new ArrayList<Constraint>();
		this.constraintsOfPartners = new ArrayList<Constraint>();
	}
	
	public void doTheMonkeyBusiness() {
		runPerceivePhase();
		runDecisionPhase();
		runActionPhase();
	}
	
	public void runPerceivePhase() {
		
	}
	
	public void runDecisionPhase() {
		
	}
	
	public void runActionPhase() {
		
	}
	
	public void computeNonCompatibilityPartnership(BookingAgent otherBA) {
		
	}
	
	public void computeNonCompatibilityReservation(Cell otherCell) {
		
	}
	
	public void computeCostPartnership(BookingAgent otherBA) {
		
	}
	
	public void computeCostReservation(Cell otherCell) {
		
	}
	
	
	public void partnerBA(BookingAgent ba) {
		partners.add(ba);
	}
	public void unpartnetBA(BookingAgent ba) {
		partners.remove(ba);
	}
	public void bookCell(Cell cell) {
		if(cell.equals(currCell) || memoryCells.contains(cell)) {
			cell.bookedBy = this;
		}
	}
	public void unbookCell(Cell cell) {
		cell.bookedBy = null;
	}
	public void moveToAnotherCell() {
		ArrayList<Cell> neighbours = env.grid.getNeighbours(currCell);
	}
	public void sendMessageToAgent(Message msg, Agent ag) {
		if(ra.equals(ag) || partners.contains(ag)) {
			
		}
	}
}


