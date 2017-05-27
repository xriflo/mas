package agents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import environment.Cell;
import environment.Environment;
import tools.Message;
import utils.Entity;

public class BookingAgent extends Agent{
	Queue<Message> messages;
	Entity stalkingEntity;
	RepresentativeAgent ra;
	Environment env;
	Cell currCell;
	ArrayList<Cell> memoryCells;
	ArrayList<BookingAgent> otherBAs;
	
	public BookingAgent(RepresentativeAgent ra, Environment env) {
		this.ra = ra;
		this.env = env;
		getACell();
		this.memoryCells = new ArrayList<Cell>();
		this.otherBAs = new ArrayList<BookingAgent>();
		this.messages = new LinkedList<Message>();
	}
	
	public void getACell() {
		currCell = env.grid.cells.get(new Random().nextInt(env.grid.cells.size()));
	}
	
	public void doTheMonkeyBusiness() {
		
	}
	
	public void partnerBA(BookingAgent ba) {
		otherBAs.add(ba);
	}
	public void unpartnetBA(BookingAgent ba) {
		otherBAs.remove(ba);
	}
	public void bookCell(Cell cell) {
		if(cell.equals(currCell)||memoryCells.contains(cell)) {
			
		}
	}
	public void unbookCell(Cell cell) {
		
	}
	public void moveToAnotherCell() {
		ArrayList<Cell> neighbours = env.grid.getNeighbours(currCell);
	}
	public void sendMessage(Message msg) {
		
	}
} 
