package utils;

import agents.BookingAgent3;
import environment.Cell;

public class BookCellConstraint extends Constraint{
	public BookingAgent3 bookedBy;
	public Cell bookedCell;
	
	public BookCellConstraint(BookingAgent3 bookedBy, Cell bookedCell) {
		super();
		this.bookedBy = bookedBy;
		this.bookedCell = bookedCell;
	}
	
	
}
