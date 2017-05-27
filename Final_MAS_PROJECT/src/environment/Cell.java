package environment;

import java.util.ArrayList;

import agents.BookingAgent;
import utils.Constraint;
import utils.Day;
import utils.Room;
import utils.Time;

public class Cell {
	public ArrayList<Constraint> constraints;
	public Day day;
	public Time time;
	public Room room;
	public BookingAgent bookedBy;
	
	public Cell(Day day, Time time, Room room) {
		this.constraints = new ArrayList<Constraint>();
		this.day = day;
		this.time = time;
		this.room = room;
		this.bookedBy = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Cell other = (Cell) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cell (day=" + day + ", time=" + time + ", room=" + room+ ", constraints=" + constraints + ")";
	}
	
	
}
