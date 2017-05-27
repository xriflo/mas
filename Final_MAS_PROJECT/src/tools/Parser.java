package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Constraint;
import utils.Day;
import utils.HasProjecterConstraint;
import utils.Room;
import utils.StudentGroup;
import utils.Teacher;
import utils.Time;
import utils.TimeConstraint;

public class Parser {
	public ArrayList<Time> times;
	public ArrayList<Day> days;
	public ArrayList<Teacher> teachers;
	public ArrayList<StudentGroup> students;
	public ArrayList<Room> rooms;
	public HashMap<Teacher, Integer> no_claases_to_take;
	public HashMap<Room, ArrayList<Constraint>> constraints_for_room;

	public void parseTestCase(String testCaseName) {
		try(BufferedReader br = new BufferedReader(new FileReader(testCaseName))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] tokens = line.split(":");
		    	switch(tokens[0]) {
		    	case "time":
		    		for(String interval:tokens[1].trim().split("\\s*,\\s*")) {
		    			String[] hours = interval.split("-");
		    			times.add(new Time(Integer.parseInt(hours[0]), Integer.parseInt(hours[1])));
		    		}
		    		break;
		    	case "days":
		    		for(String day:tokens[1].trim().split("\\s*,\\s*"))
		    			days.add(new Day(day));
		    		break;
		    	case "teachers":
		    		for(String teacher:tokens[1].trim().split("\\s*,\\s*"))
		    			teachers.add(new Teacher(teacher));
		    		break;
		    	case "time_constraints_for_teachers":
		    		for(String constraint:tokens[1].trim().split(" ")) {
		    			String[] params = constraint.split("\\W");
		    			Integer teacher_index = teachers.indexOf(new Teacher(params[0]));

		    			if(teacher_index!=-1) {
			    			Teacher teacher = teachers.get(teacher_index);
			    			TimeConstraint tc = new TimeConstraint(
			    					new Day(params[1]),
			    					new Time(Integer.parseInt(params[2]),Integer.parseInt(params[3])));
			    			teacher.constraints.add(tc);
		    			}
		    		}
		    		break;
		    	case "students":
		    		for(String sg:tokens[1].trim().split("\\s*,\\s*")) {
		    			students.add(new StudentGroup(sg));
		    		}
		    		break;
		    	case "rooms":
		    		for(String room:tokens[1].trim().split("\\s*,\\s*")) {
		    			rooms.add(new Room(room));
		    		}
		    		break;
		    	case "time_constraints_for_rooms":
		    		for(String constraint:tokens[1].trim().split(" ")) {
		    			String[] params = constraint.split("\\W");
		    			Integer room_index = rooms.indexOf(new Room(params[0]));

		    			if(room_index!=-1) {
			    			Room room = rooms.get(room_index);
			    			TimeConstraint tc = new TimeConstraint(
			    					new Day(params[1]),
			    					new Time(Integer.parseInt(params[2]),Integer.parseInt(params[3])));
			    			ArrayList<Constraint> constr = constraints_for_room.get(room);
			    			if(constr==null)
			    				constr = new ArrayList<Constraint>();
			    			constr.add(tc);
			    			constraints_for_room.put(room, constr);
		    			}
		    		}
		    		break;
		    	case "physical_constraints_for_rooms":
		    		System.out.println("aici");
		    		for(String constraint:tokens[1].trim().split(" ")) {
		    			String[] params = constraint.split("\\W");
		    			switch(params[0]) {
		    			case "hasProjector":
		    				Integer room_index = rooms.indexOf(new Room(params[1]));

			    			if(room_index!=-1) {
				    			Room room = rooms.get(room_index);
				    			HasProjecterConstraint pc = new HasProjecterConstraint(
				    					Boolean.parseBoolean(params[2]));
				    			ArrayList<Constraint> constr = constraints_for_room.get(room);
				    			if(constr==null)
				    				constr = new ArrayList<Constraint>();
				    			constr.add(pc);
				    			constraints_for_room.put(room, constr);
			    			}
		    				break;
	    				default:
		    				System.out.println("error on constraint: "+params[0]);
		    			}
		    		}
		    		break;
		    	case "no_classes_to_take":
		    		for(String constraint:tokens[1].trim().split(" ")) {
		    			String[] params = constraint.split("\\W");
		    			Integer teacher_index = teachers.indexOf(new Teacher(params[0]));

		    			if(teacher_index!=-1) {
			    			Teacher teacher = teachers.get(teacher_index);
			    			no_claases_to_take.put(teacher, Integer.parseInt(params[1]));
		    			}
		    		}
		    		break;
	    		default:
	    			System.out.println("error parsing file, unknown type: "+tokens[0]);
		    	}
		    }
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(constraints_for_room);
	}
	
	public Parser() {
		super();
		this.times = new ArrayList<Time>();
		this.days = new ArrayList<Day>();
		this.teachers = new ArrayList<Teacher>();
		this.students = new ArrayList<StudentGroup>();
		this.rooms = new ArrayList<Room>();
		this.no_claases_to_take = new HashMap<Teacher, Integer>();
		this.constraints_for_room = new HashMap<Room, ArrayList<Constraint>>();
	}

}
