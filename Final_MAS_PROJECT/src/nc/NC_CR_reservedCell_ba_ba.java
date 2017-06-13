package nc;

import agents.BookingAgent2;
import environment.Cell;

public class NC_CR_reservedCell_ba_ba extends NC{
	public BookingAgent2 me;
	public BookingAgent2 other;
	public Cell reservedCell;
	
	public NC_CR_reservedCell_ba_ba(BookingAgent2 me, BookingAgent2 other, Cell reservedCell) {
		super();
		this.me = me;
		this.other = other;
		this.reservedCell = reservedCell;
	}
	
	
}
