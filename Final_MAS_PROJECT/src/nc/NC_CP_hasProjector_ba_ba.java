package nc;

import agents.BookingAgent2;
import environment.Cell;

public class NC_CP_hasProjector_ba_ba {
	BookingAgent2 me;
	BookingAgent2 other;
	Cell cell;
	
	public NC_CP_hasProjector_ba_ba(BookingAgent2 me, BookingAgent2 other, Cell cell) {
		super();
		this.me = me;
		this.other = other;
		this.cell = cell;
	}
}
