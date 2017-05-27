package environment;

import java.util.ArrayList;
import java.util.HashMap;

import utils.Constraint;
import utils.Day;
import utils.HasProjecterConstraint;
import utils.Room;
import utils.Time;
import utils.TimeConstraint;

public class GridSpace {
	public ArrayList<Day> days;
	public ArrayList<Time> times;
	public ArrayList<Room> rooms;
	public ArrayList<Cell> cells;
	
	public GridSpace(ArrayList<Day> days, ArrayList<Time> times, ArrayList<Room> rooms, HashMap<Room, ArrayList<Constraint>> constraints_for_room) {
		this.days = days;
		this.times = times;
		this.rooms = rooms;
		this.cells = new ArrayList<Cell>();
		
		for(Day day:days)
			for(Time time:times)
				for(Room room:rooms) {
					Cell cell = new Cell(day, time, room);
					cell.constraints = new ArrayList<Constraint>();
					ArrayList<Constraint> constraintForRoom = constraints_for_room.get(room);
					if(constraintForRoom!=null) {
						for(Constraint constr:constraintForRoom) {
							if(constr instanceof HasProjecterConstraint)
								cell.constraints.add(constr);
							else if(constr instanceof TimeConstraint) {
								TimeConstraint tc = (TimeConstraint)constr;
								if(tc.day.equals(day) && tc.time.equals(time))
									cell.constraints.add(tc);
							}
						}
						
					}
					cells.add(cell);
				}
		
	}
	
	private Cell getSpecificCell(Day day, Time time, Room room) {
		Cell cell = new Cell(day, time, room);
		Integer cell_index = cells.indexOf(cell);
		return cells.get(cell_index);
	}
	
	public ArrayList<Cell> getNeighbours(Cell cell) {
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		neighbours.addAll(getNextDay(cell));
		neighbours.addAll(getNextTime(cell));
		neighbours.addAll(getNextRoom(cell));
		return neighbours;
	}
	
	private ArrayList<Cell> getNextDay(Cell cell) {
		ArrayList<Cell> neighboursOfDay = new ArrayList<Cell>();
		Integer day_index = days.indexOf(cell.day);
		if(day_index>0)
			neighboursOfDay.add(getSpecificCell(days.get(day_index-1), cell.time, cell.room));
		if(day_index<(days.size()-1))
			neighboursOfDay.add(getSpecificCell(days.get(day_index-1), cell.time, cell.room));
		return neighboursOfDay;
	}
	
	private ArrayList<Cell> getNextTime(Cell cell) {
		ArrayList<Cell> neighboursOfTime = new ArrayList<Cell>();
		Integer time_index = times.indexOf(cell.time);
		if(time_index>0)
			neighboursOfTime.add(getSpecificCell(cell.day, times.get(time_index-1), cell.room));
		if(time_index<(times.size()-1))
			neighboursOfTime.add(getSpecificCell(cell.day, times.get(time_index+1), cell.room));
		return neighboursOfTime;
	}
	
	private ArrayList<Cell> getNextRoom(Cell cell) {
		ArrayList<Cell> neighboursOfRoom = new ArrayList<Cell>();
		Integer room_index = rooms.indexOf(cell.room);
		if(room_index>0)
			neighboursOfRoom.add(getSpecificCell(cell.day, cell.time, rooms.get(room_index-1)));
		if(room_index<rooms.size()-1)
			neighboursOfRoom.add(getSpecificCell(cell.day, cell.time, rooms.get(room_index+1)));
		return neighboursOfRoom;
	}

	@Override
	public String toString() {
		String toStringVar = "GridSpace [\n";
		for(Cell cell:cells)
			System.out.println(cell);
		toStringVar += "\n";
		return toStringVar;
	}
	
	
}
