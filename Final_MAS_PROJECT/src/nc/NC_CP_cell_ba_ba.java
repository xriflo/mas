package nc;

import agents.BookingAgent2;
import environment.Cell;

public class NC_CP_cell_ba_ba extends NC{
	BookingAgent2 me;
	BookingAgent2 partner;
	Cell cell;
	
	public NC_CP_cell_ba_ba(BookingAgent2 me, BookingAgent2 partner, Cell cell) {
		super();
		this.me = me;
		this.partner = partner;
		this.cell = cell;
	}

	@Override
	public String toString() {
		return "NC_CP_cell_ba_ba [me=" + me + ", partner=" + partner + ", cell=" + cell + "]";
	}
	
	
}
