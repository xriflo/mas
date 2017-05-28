package agents;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
	public Float time;
	public boolean alive;
	public Queue<Message> messages;
	public Entity stalkingEntity;
	public RepresentativeAgent ra;
	public Environment env;
	public Cell currCell;
	public Cell currReservedCell;
	public Agent currPartner;
	public LinkedHashSet<Cell> memoryCells;
	public ArrayList<BookingAgent> partners;
	public ArrayList<Constraint> constraints;
	public ArrayList<Constraint> constraintsOfBrothers;
	public ArrayList<Constraint> constraintsOfPartners;
	
	public BookingAgent(RepresentativeAgent ra, Environment env) {
		this.time = Settings.EPSILON;
		this.alive = true;
		this.ra = ra;
		this.env = env;
		this.memoryCells = new LinkedHashSet<Cell>();
		this.partners = new ArrayList<BookingAgent>();
		this.messages = new LinkedList<Message>();
		this.currReservedCell = null;
		this.currPartner = null;
		this.constraints = new ArrayList<Constraint>();
		this.constraintsOfBrothers = new ArrayList<Constraint>();
		this.constraintsOfPartners = new ArrayList<Constraint>();
	}
	
	public void doTheMonkeyBusiness() {
		if(alive) {
			processMessages();
			if(currPartner!=null && currReservedCell!=null) {
				if(Math.abs(computeCostReservation(currCell))<Settings.EPSILON)
					currCell = currReservedCell;
				else
					processCurrentCell();
			}
			else {
				moveToNextCell();
				addBAsToMemory();
				processEncountredBAs();
				if(!(currPartner!=null || currReservedCell!=null))
					processCurrentCell();
			}
		}
	}
	
	public void processMessages() {}
	public void processCurrentCell() {
		memoryCells.add(currCell);
	}
	public void moveToNextCell() {
		ArrayList<Cell> neighbours = env.grid.getNeighbours(currCell);
		currCell = neighbours.get(new Random().nextInt(neighbours.size()));
	}
	public void addBAsToMemory() {
		
	}
	public void processEncountredBAs() {}
	
	public void computeNonCompatibilityPartnership(BookingAgent otherBA) {
		
	}
	
	public void computeNonCompatibilityReservation(Cell otherCell) {
		
	}
	
	public Float computeCostPartnership(BookingAgent otherBA) {
		return 0.0F;
	}
	
	public Float computeCostReservation(Cell otherCell) {
		return 0.0F;
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
	public void sendMessageToAgent(Message msg, Agent ag) {
		if(ra.equals(ag) || partners.contains(ag)) {
			
		}
	}
}


