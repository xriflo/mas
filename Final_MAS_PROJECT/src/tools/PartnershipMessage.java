package tools;

import agents.BookingAgent2;

public class PartnershipMessage extends Message{
	public enum INFO {WANTYOU, YES, NO, YES2, NO2, NOMORE;}
	
	public BookingAgent2 from;
	public BookingAgent2 to;
	
	public INFO info;
	public PartnershipMessage(BookingAgent2 from, BookingAgent2 to, INFO info) {
		this.from = from;
		this.to = to;
		this.info = info;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		PartnershipMessage other = (PartnershipMessage) obj;
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
