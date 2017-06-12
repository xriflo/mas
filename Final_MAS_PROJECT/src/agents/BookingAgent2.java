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

public class BookingAgent2 {
	public RepresentativeAgent ra;
	public Environment env;
	public Cell currCell;
	public HashSet<Cell> prevCells;
	public Entity representingEntity;
	public Entity stalkingEntity;
	public ArrayList<Constraint> constraints;
	public Float time;
	public LinkedBlockingQueue<Message> messages;
	
	public BookingAgent2(RepresentativeAgent ra, Environment env) {
		this.ra = ra;
		this.env = env;
		this.representingEntity = ra.entity;
		this.constraints = new ArrayList<Constraint>();
		this.constraints.addAll(representingEntity.constraints);
		this.time = Settings.EPSILON;
		this.messages = new LinkedBlockingQueue<Message>();
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
}
