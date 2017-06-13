package nc;

import agents.BookingAgent2;
import environment.Cell;

public class NC_CI_time_ba_cell extends NC{
	public BookingAgent2 me;
	public Cell me_cell;
	public Cell cell;
	
	public NC_CI_time_ba_cell(BookingAgent2 me, Cell cell, Cell me_cell) {
		super();
		this.me = me;
		this.cell = cell;
		this.me_cell = me_cell;
	}
}
