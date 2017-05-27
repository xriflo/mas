package environment;

import java.util.ArrayList;

import utils.Day;
import utils.Room;
import utils.Time;

public class GridSpace {
	public ArrayList<Day> days;
	public ArrayList<Time> times;
	public ArrayList<Room> rooms;
	
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
			neighboursOfDay.add(new Cell(days.get(day_index-1), cell.time, cell.room));
		if(day_index<(days.size()-1))
			neighboursOfDay.add(new Cell(days.get(day_index-1), cell.time, cell.room));
		return neighboursOfDay;
	}
	
	private ArrayList<Cell> getNextTime(Cell cell) {
		ArrayList<Cell> neighboursOfTime = new ArrayList<Cell>();
		Integer time_index = times.indexOf(cell.time);
		if(time_index>0)
			neighboursOfTime.add(new Cell(cell.day, times.get(time_index-1), cell.room));
		if(time_index<(times.size()-1))
			neighboursOfTime.add(new Cell(cell.day, times.get(time_index+1), cell.room));
		return neighboursOfTime;
	}
	
	private ArrayList<Cell> getNextRoom(Cell cell) {
		ArrayList<Cell> neighboursOfRoom = new ArrayList<Cell>();
		Integer room_index = rooms.indexOf(cell.room);
		if(room_index>0)
			neighboursOfRoom.add(new Cell(cell.day, cell.time, rooms.get(room_index-1)));
		if(room_index<rooms.size()-1)
			neighboursOfRoom.add(new Cell(cell.day, cell.time, rooms.get(room_index+1)));
		return neighboursOfRoom;
	}

	@Override
	public String toString() {
		String toStringVar = "GridSpace [";
		for(int i=0; i<days.size(); i++)
			for(int j=0; j<times.size(); j++)
				for(int k=0; k<rooms.size(); k++)
					toStringVar += new Cell(days.get(i), times.get(j), rooms.get(k))+ " ";
		return toStringVar;
	}
	
	
}
