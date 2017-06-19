package nc;

import agents.BookingAgent2;
import environment.Cell;

public class NC_CI_hasProjector_ba_cell extends NC{
	public BookingAgent2 me;
	public Cell cell;
	public NC_CI_hasProjector_ba_cell(BookingAgent2 me, Cell cell) {
		super();
		this.me = me;
		this.cell = cell;
	}
	@Override
	public String toString() {
		return "NC_CI_hasProjector_ba_cell [me=" + me + ", cell=" + cell + "]";
	}
	
	
}
