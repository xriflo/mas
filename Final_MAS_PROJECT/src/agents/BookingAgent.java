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
import utils.HasProjecterConstraint;
import utils.StudentGroup;
import utils.Teacher;
import utils.TimeConstraint;

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
	public LinkedHashSet<Cell> incompetentCells;
	public LinkedHashSet<BookingAgent> incompetentPartners;
	public ArrayList<Constraint> constraints;
	public ArrayList<Constraint> constraintsOfBrothers;
	public ArrayList<Constraint> constraintsOfPartners;
	
	public BookingAgent(RepresentativeAgent ra, Environment env) {
		this.time = Settings.EPSILON;
		this.alive = true;
		this.ra = ra;
		this.env = env;
		this.memoryCells = new LinkedHashSet<Cell>();
		this.incompetentCells = new LinkedHashSet<Cell>();
		this.incompetentPartners = new LinkedHashSet<BookingAgent>();
		this.messages = new LinkedList<Message>();
		this.currCell = null;
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
	
	public boolean verifyPartnershipIncompetence(BookingAgent otherBA) {
		boolean isPartnerShipOk;
		if(incompetentPartners.contains(otherBA))
			isPartnerShipOk = false;
		else {
			if((representingEntity instanceof Teacher && otherBA.representingEntity instanceof StudentGroup) ||
					representingEntity instanceof StudentGroup && otherBA.representingEntity instanceof Teacher) {
				if (computeCostPartnership(otherBA) >= computeCostPartnership(currPartner))
					isPartnerShipOk = false;
				else
					isPartnerShipOk = true;
			}
			else
				isPartnerShipOk = false;
		}
		if(!isPartnerShipOk)
			incompetentPartners.add(otherBA);
		return isPartnerShipOk;
	}
	
	public boolean verifyReservationIncompetence(Cell otherCell) {
		boolean isReservationOk = true;
		if(incompetentCells.contains(otherCell))
			isReservationOk = false;
		else {
			if(representingEntity instanceof Teacher) {
				for(Constraint constrX:representingEntity.constraints) {
					if(constrX instanceof HasProjecterConstraint) {
						//TBD
						isReservationOk = isReservationOk && true;
					}
					else if(constrX instanceof TimeConstraint){
						TimeConstraint tc = (TimeConstraint)constrX;
						if(otherCell.day==tc.day || otherCell.time==tc.time)
							isReservationOk = isReservationOk && false;
					}
				}
			}
		}
		if(computeCostReservation(otherCell) >= computeCostReservation(currCell))
			isReservationOk = false;
		if(!isReservationOk)
			incompetentCells.add(otherCell);
		return isReservationOk;
	}
	
	public boolean verifyPartnershipConflict(BookingAgent otherBA) {
		boolean isPartnershipOk = false;
		if(otherBA!=currPartner && verifyPartnershipConflict(otherBA)) {
			if(computeCostPartnership(otherBA) < computeCostPartnership(currPartner))
				isPartnershipOk = true;
		}
		return isPartnershipOk;
	}
	
	public boolean verifyReservationConflict(Cell otherCell) {
		boolean isReservationOk = false;
		return isReservationOk;
	}
	
	public boolean verifyReservationUselessness(Cell cell) {
		return true;
		/*
		boolean isReservationOk = true;
		for(BookingAgent brother:ra.bas) {
			if(brother!=this && brother.currCell==currCell) {
				isReservationOk = false;
				break;
			}
		}
		return isReservationOk;
		*/
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
	
	
	public void partnerBA(BookingAgent otherBA) {
		currPartner = otherBA;
	}
	public void unpartnetBA(BookingAgent otherBA) {
		currPartner = null;
	}
	public void bookCell(Cell cell) {
		if(cell.equals(currCell) || memoryCells.contains(cell)) {
			if(this.representingEntity instanceof Teacher)
				cell.bookedByTeacher = this;
			else
				cell.bookedBySG = this;
		}
	}
	public void unbookCell(Cell cell) {
		if(this.representingEntity instanceof Teacher)
			cell.bookedByTeacher = null;
		else
			cell.bookedBySG = null;
	}
}


