package nc;

import agents.BookingAgent2;

public class NC_CI_hasProjector_ba_ba extends NC{
	public BookingAgent2 me;
	public BookingAgent2 other;
	
	public NC_CI_hasProjector_ba_ba(BookingAgent2 me, BookingAgent2 other) {
		this.me = me;
		this.other = other;
	}
}
