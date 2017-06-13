package agents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import environment.Cell;
import environment.Environment;
import nc.NC;
import nc.NC_CB_ba_brother_cell;
import nc.NC_CI_hasProjector_ba_ba;
import nc.NC_CP_cell_ba_ba;
import tools.Message;
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
	public Float time;
	
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
	}
	
	public void doTheMonkeyBusiness() {
		
	}
	
	public ArrayList<NC> nonCompatiblePartnership(BookingAgent2 otherBA) {
		ArrayList<NC> listOfNonCompatibilities = new ArrayList<NC>();
		for(Constraint myConstr:constraints) {
			for(Constraint otherConstr:otherBA.constraints) {
				if(myConstr instanceof HasProjecterConstraint && otherConstr instanceof HasProjecterConstraint) {
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
						listOfNonCompatibilities.add(new NC_CP_cell_ba_ba(this, otherBA, bookedCell));
					}
				}
			}
		}
		return listOfNonCompatibilities;
	}
	
	public ArrayList<NC> nonCompatibleReservation(Cell cell) {
		ArrayList<NC> listOfNonCompatibilities = new ArrayList<NC>();
		
		return listOfNonCompatibilities;
	}
	
	public void moveToNextCell() {
		ArrayList<Cell> neighbours = env.grid.getNeighbours(currCell);
		currCell = neighbours.get(new Random().nextInt(neighbours.size()));
		this.prevCells.add(currCell);
	}
	
	public void partnerBA(BookingAgent2 otherBA) {
		if(	((representingEntity instanceof Teacher) && (otherBA.representingEntity instanceof StudentGroup)) ||
			((representingEntity instanceof StudentGroup) && (otherBA.representingEntity instanceof Teacher))) {
			bookedPartner = otherBA;
		}
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
}
