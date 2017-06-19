package agents;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import environment.Cell;
import environment.Environment;
import nc.NC;
import nc.NC_CB_ba_brother_cell;
import nc.NC_CI_hasProjector_ba_ba;
import nc.NC_CI_hasProjector_ba_cell;
import nc.NC_CI_time_ba_cell;
import nc.NC_CP_cell_ba_ba;
import nc.NC_CP_hasProjector_ba_cell;
import nc.NC_CR_reservedCell_ba_ba;
import tools.Message;
import tools.PartnershipMessage;
import tools.PartnershipMessage.INFO;
import tools.Settings;
import utils.Constraint;
import utils.Entity;
import utils.HasProjecterConstraint;
import utils.StudentGroup;
import utils.Teacher;
import utils.TimeConstraint;

public class BookingAgent2 {
	public RepresentativeAgent ra;
	public Environment env;
	public Cell currCell;
	public Cell bookedCell;
	public HashSet<Cell> prevCells;
	public Entity representingEntity;
	public Entity stalkingEntity;
	public BookingAgent2 bookedPartner;
	public ArrayList<Constraint> constraints;
	public ArrayList<BookingAgent2> brothers;
	public HashSet<BookingAgent2> acquitanceBAs;
	public BookingAgent2 wantedPartner;
	public Float time;
	Queue<Message> messagesQueue = new LinkedList<Message>();
	
	public BookingAgent2(RepresentativeAgent ra, Environment env) {
		this.ra = ra;
		this.env = env;
		this.representingEntity = ra.entity;
		this.bookedCell = null;
		this.bookedPartner = null;
		this.constraints = new ArrayList<Constraint>();
		this.constraints.addAll(representingEntity.constraints);
		this.time = Settings.EPSILON;
		currCell = env.grid.cells.get(new Random().nextInt(env.grid.cells.size()));
		this.prevCells = new HashSet<Cell>();
		this.prevCells.add(currCell);
		this.messagesQueue = new LinkedList<>();
		this.wantedPartner = null;
		this.acquitanceBAs = new HashSet<BookingAgent2>();
	}
	
	public void processMessages() {
		HashSet<PartnershipMessage> datingMessages = new HashSet<PartnershipMessage>();
		while(messagesQueue.size()!=0) {
			Message msg = messagesQueue.poll();
			if(msg instanceof PartnershipMessage) {
				PartnershipMessage m = (PartnershipMessage)msg;
				
				System.out.println("Recv msg from "+m.from+ " to "+m.to+": "+m.info);
				switch(m.info) {
				case WANTYOU:
					datingMessages.add(m);
					break;
				case YES:
					datingMessages.add(m);
					break;
				case YES2:
					if(bookedPartner!=null) {
						bookedPartner.messagesQueue.add(new PartnershipMessage(this, bookedPartner, INFO.NOMORE));
						unpartnerBA();
					}
					partnerBA(m.from);
					break;
				case NO2:
					break;
				case NO:
					break;
				case NOMORE:
					unpartnerBA();
					break;
				}
			}
		}
		
		PartnershipMessage bestMessage = null;
		Float bestCost = Float.MAX_VALUE;
		for(PartnershipMessage m:datingMessages) {
			if(computeCostPartnership(m.from)<bestCost) {
				bestMessage = m;
				bestCost = computeCostPartnership(m.from);
			}
		}
		if(bestCost<computeCostPartnership(bookedPartner)) {
			for(PartnershipMessage m:datingMessages) {
				if(m.equals(bestMessage)) {
					if(m.info.equals(INFO.WANTYOU)) {
						m.from.messagesQueue.add(new PartnershipMessage(this, m.from, INFO.YES));
					}
					else if(m.info.equals(INFO.YES)) {
						m.from.messagesQueue.add(new PartnershipMessage(this, m.from, INFO.YES2));
						if(bookedPartner!=null) {
							bookedPartner.messagesQueue.add(new PartnershipMessage(this, bookedPartner, INFO.NOMORE));
							unpartnerBA();
						}
						partnerBA(m.from);
					}
				}
				else {
					if(m.info.equals(INFO.WANTYOU)) {
						m.from.messagesQueue.add(new PartnershipMessage(this, m.from, INFO.NO));
					}
					else if(m.info.equals(INFO.YES)) {
						m.from.messagesQueue.add(new PartnershipMessage(this, m.from, INFO.NO2));
					}
				}
			}
		}
		
	}
	
	public void doTheMonkeyBusiness() {
		time += 1F;
		processMessages();
		if(bookedPartner!=null && bookedCell!=null) {
			if(computeCostReservation(bookedCell)<1f) {
				System.out.println(this+": I am gone!");
				currCell = bookedCell;
			}
			else
				processCurrentCell();
		}
		else {
			moveToNextCell();
			processEncounteredBAs(env.getRoommatesForBA(this));
			if(!(bookedCell!=null || bookedPartner!=null)) {
				processCurrentCell();
			}
		}
	}
	
	public void processCurrentCell() {
		if(computeCostReservation(bookedCell) > computeCostReservation(currCell)) {
			unbookCell(bookedCell);
			bookCell(currCell);
			
		}
	}
	
	//---------------------function processEncounteredBAs---------------------
	public void processEncounteredBAs(ArrayList<BookingAgent2> encounteredBAs) {
		for(BookingAgent2 encounteredBA:encounteredBAs) {
			if( (this.representingEntity instanceof Teacher && encounteredBA.representingEntity instanceof StudentGroup) ||
				(this.representingEntity instanceof StudentGroup && encounteredBA.representingEntity instanceof Teacher)) {
				acquitanceBAs.add(encounteredBA);
			}
		}
		
		Float minCost = Float.MAX_VALUE;
		for(BookingAgent2 encounteredBA : acquitanceBAs) {
			if(computeCostPartnership(encounteredBA) < minCost) {
				minCost = computeCostPartnership(encounteredBA);
			}
		}
		
		HashSet<BookingAgent2> chosenBAs = new HashSet<BookingAgent2>();
		for(BookingAgent2 encounteredBA : acquitanceBAs) {
			if(computeCostPartnership(encounteredBA) <= minCost)
				chosenBAs.add(encounteredBA);
		}
				
		for(BookingAgent2 chosenBA : chosenBAs) {
			chosenBA.messagesQueue.add(new PartnershipMessage(this, chosenBA, INFO.WANTYOU));
		}
	}
	
	//---------------------function nonCompatiblePartnership---------------------
	public ArrayList<NC> nonCompatiblePartnership(BookingAgent2 otherBA) {
		ArrayList<NC> listOfNonCompatibilities = new ArrayList<NC>();
		for(Constraint myConstr:constraints) {
			for(Constraint otherConstr:otherBA.constraints) {
				if(myConstr instanceof HasProjecterConstraint && otherConstr instanceof HasProjecterConstraint) {
					//System.out.println(myConstr + " vs " + otherConstr);
					if(!myConstr.equals(otherConstr)) {
						listOfNonCompatibilities.add(new NC_CI_hasProjector_ba_ba(this, otherBA));
					}
				}
			}
		}
		
		if(otherBA.bookedCell!=null) {
			boolean brother_has_same_cell = false;
			BookingAgent2 which_brother=null;
			for(BookingAgent2 brother:brothers) {
				if(otherBA.bookedCell.equals(brother.bookedCell)) {
					brother_has_same_cell = true;
					which_brother = brother;
				}
			}
			if(brother_has_same_cell) {
				listOfNonCompatibilities.add(new NC_CB_ba_brother_cell(this, which_brother, otherBA.bookedCell));
			}
		}
		
		if(bookedCell!=null) {
			for(Constraint constr:otherBA.constraints) {
				if(constr instanceof TimeConstraint) {
					TimeConstraint tc = (TimeConstraint)constr;
					if(tc.day==bookedCell.day && tc.time==bookedCell.time) {
						listOfNonCompatibilities.add(new NC_CR_reservedCell_ba_ba(this, otherBA, bookedCell));
					}
				}
			}
		}
		return listOfNonCompatibilities;
	}
	
	public ArrayList<NC> nonCompatibleReservation(Cell cell) {
		ArrayList<NC> listOfNonCompatibilities = new ArrayList<NC>();
		
		for(Constraint roomConstr:cell.room.constraints) {
			if(roomConstr instanceof TimeConstraint) {
				listOfNonCompatibilities.add(new NC_CI_time_ba_cell(this, cell));
			}
			else if(roomConstr instanceof HasProjecterConstraint) {
				for(Constraint meConstr:constraints) {
					if(meConstr instanceof HasProjecterConstraint) {
						if(!roomConstr.equals(meConstr)) {
							listOfNonCompatibilities.add(new NC_CI_hasProjector_ba_cell(this, cell));
						}
					}
				}
			}
		}
		
		boolean brother_has_same_cell = false;
		BookingAgent2 which_brother=null;
		for(BookingAgent2 brother:brothers) {
			if(cell.equals(brother.bookedCell)) {
				brother_has_same_cell = true;
				which_brother = brother;
			}
		}
		if(brother_has_same_cell) {
			listOfNonCompatibilities.add(new NC_CB_ba_brother_cell(this, which_brother, cell));
		}
		
		if(bookedPartner!=null) {
			for(Constraint partnerConst:bookedPartner.constraints) {
				if(partnerConst instanceof TimeConstraint) {
					TimeConstraint tc = (TimeConstraint)partnerConst;
					if(tc.day==cell.day&&tc.time==cell.time) {
						listOfNonCompatibilities.add(new NC_CP_cell_ba_ba(this, bookedPartner, cell));
					}
				}
				if(partnerConst instanceof HasProjecterConstraint) {
					for(Constraint roomConstr:cell.constraints) {
						if(roomConstr instanceof HasProjecterConstraint) {
							listOfNonCompatibilities.add(new NC_CP_hasProjector_ba_cell(this, bookedPartner, cell));
						}
					}
				}
			}
		}
		return listOfNonCompatibilities;
	}
	
	public ArrayList<Float> assignWeight(ArrayList<NC> ncs) {
		ArrayList<Float> weights = new ArrayList<Float>();
		for(NC nc:ncs) {
			weights.add(10000f);
		}
		return weights;
	}
	
	public Float computeCostPartnership(BookingAgent2 otherBA) {
		if(otherBA==null)
			return Float.MAX_VALUE;
		ArrayList<NC> listOfNCPartnership = nonCompatiblePartnership(otherBA);
		ArrayList<Float> weights = assignWeight(listOfNCPartnership);
		Float difficulty = 0F;
		for(Integer i=0; i<listOfNCPartnership.size(); i++) {
			difficulty += weights.get(i);
		}
		//System.out.println("For "+this+" and "+otherBA+" the cost is: "+difficulty/time);
		//System.out.println("Also the list of NC is: "+listOfNCPartnership);
		return difficulty/time;
	}
	
	public Float computeCostReservation(Cell cell) {
		if(cell==null)
			return Float.MAX_VALUE;
		ArrayList<NC> listOfNCReservation = nonCompatibleReservation(cell);
		ArrayList<Float> weights = assignWeight(listOfNCReservation);
		Float difficulty = 0F;
		for(Integer i=0; i<listOfNCReservation.size(); i++) {
			difficulty += weights.get(i);
		}
		//System.out.println("For agent "+this+" the difficulty of cell "+cell+" is: "+difficulty);
		return difficulty;
	}
	
	public void moveToNextCell() {
		ArrayList<Cell> neighbours = env.grid.getNeighbours(currCell);
		currCell = neighbours.get(new Random().nextInt(neighbours.size()));
		this.prevCells.add(currCell);
	}
	
	public void partnerBA(BookingAgent2 otherBA) {
			bookedPartner = otherBA;
	}
	
	public void unpartnerBA() {
		this.bookedPartner = null;
	}
	
	public void bookCell(Cell otherCell) {
		if(otherCell.equals(currCell) || prevCells.contains(otherCell)) {
			bookedCell = otherCell;
			if(representingEntity instanceof Teacher && bookedCell.bookedByTeacher==null)
				bookedCell.bookedByTeacher = this;
			else if(representingEntity instanceof StudentGroup && bookedCell.bookedBySG==null)
				bookedCell.bookedBySG = this;
		}
	}
	
	public void unbookCell(Cell otherCell) {
		if(representingEntity instanceof Teacher && bookedCell.bookedByTeacher==this)
			bookedCell.bookedByTeacher = null;
		else if(representingEntity instanceof StudentGroup && bookedCell.bookedBySG==this)
			bookedCell.bookedBySG = null;
	}

	@Override
	public String toString() {
		return representingEntity.toString();
	}
	
	
}
