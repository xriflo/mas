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
import utils.StudentGroup;
import utils.Teacher;

public class BookingAgent extends Agent {
	public Float time;
	public boolean alive;
	public Queue<Message> messages;
	public Entity representingEntity;
	public Entity stalkingEntity;
	public RepresentativeAgent ra;
	public Environment env;
	public Cell currCell;
	public Cell currReservedCell;
	public BookingAgent currPartner;
	public LinkedHashSet<Cell> memoryCells;
	public ArrayList<BookingAgent> partners;
	public ArrayList<BookingAgent> incompetentPartners;
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
		this.incompetentPartners = new ArrayList<BookingAgent>();
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
	
	public boolean verifyPartnershipIncompetence(BookingAgent other) {
		boolean isPartnerShipOk;
		if(incompetentPartners.contains(other))
			isPartnerShipOk = false;
		else {
			if((representingEntity instanceof Teacher && other.representingEntity instanceof StudentGroup) ||
					representingEntity instanceof StudentGroup && other.representingEntity instanceof Teacher) {
				if (computeCostPartnership(other) >= computeCostPartnership(currPartner))
					isPartnerShipOk = false;
				else
					isPartnerShipOk = true;
			}
			else
				isPartnerShipOk = false;
		}
		return isPartnerShipOk;
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


