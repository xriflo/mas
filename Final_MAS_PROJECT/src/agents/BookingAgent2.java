package agents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import environment.Cell;
import environment.Environment;
import tools.Message;
import tools.Settings;
import utils.Constraint;
import utils.Entity;
import utils.StudentGroup;
import utils.Teacher;

public class BookingAgent2 {
	public RepresentativeAgent ra;
	public Environment env;
	public Cell currCell;
	public Cell bookedCell;
	public HashSet<Cell> prevCells;
	public Entity representingEntity;
	public Entity stalkingEntity;
	BookingAgent2 bookedPartner;
	public ArrayList<Constraint> constraints;
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
