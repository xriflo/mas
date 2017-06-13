package nc;

import agents.BookingAgent2;
import environment.Cell;

public class NC_CB_ba_brother_cell extends NC{
	public BookingAgent2 me;
	public BookingAgent2 brother;
	public Cell cell;
	
	public NC_CB_ba_brother_cell(BookingAgent2 me, BookingAgent2 brother, Cell cell) {
		super();
		this.me = me;
		this.brother = brother;
		this.cell = cell;
	}
}
