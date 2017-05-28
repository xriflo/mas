package utils;

import agents.BookingAgent;
import environment.Cell;

public class BookCellConstraint extends Constraint{
	public BookingAgent bookedBy;
	public Cell bookedCell;
	
	public BookCellConstraint(BookingAgent bookedBy, Cell bookedCell) {
		super();
		this.bookedBy = bookedBy;
		this.bookedCell = bookedCell;
	}
	
	
}
