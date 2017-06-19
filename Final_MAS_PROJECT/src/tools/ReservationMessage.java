package tools;

import agents.BookingAgent2;
import environment.Cell;

public class ReservationMessage extends Message{
	public enum INFO {WANTCELL, YES, NO;}
	
	public BookingAgent2 from;
	public BookingAgent2 to;
	public Cell cell;
	
	public INFO info;
	public ReservationMessage(BookingAgent2 from, BookingAgent2 to, INFO info, Cell cell) {
		this.from = from;
		this.to = to;
		this.info = info;
		this.cell = cell;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cell == null) ? 0 : cell.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationMessage other = (ReservationMessage) obj;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (info != other.info)
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	
	
	
}
