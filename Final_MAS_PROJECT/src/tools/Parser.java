package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Constraint;
import utils.Day;
import utils.Entity;
import utils.HasProjecterConstraint;
import utils.Room;
import utils.StudentGroup;
import utils.Teacher;
import utils.Time;
import utils.TimeConstraint;
import utils.Tuple;

public class Parser {
	public ArrayList<Time> times;
	public ArrayList<Day> days;
	public ArrayList<Room> rooms;
	
	public ArrayList<Entity> entities;
	public ArrayList<StudentGroup> students;
	public ArrayList<Teacher> teachers;

	public HashMap<Teacher, Integer> no_classes_to_take;
	public HashMap<Teacher, Integer> no_classes_to_use_projector;
	public HashMap<Room, ArrayList<Constraint>> constraints_for_room;
	
	Integer no_students=0, no_teachers=0;
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
		    		for(String teacher:tokens[1].trim().split("\\s*,\\s*")) {
		    			Teacher t = new Teacher(teacher);
		    			entities.add(t);
		    			teachers.add(t);
		    		}
		    		break;
		    	case "time_constraints_for_teachers":
		    		for(String constraint:tokens[1].trim().split(" ")) {
		    			String[] params = constraint.split("\\W");
		    			Integer teacher_index = entities.indexOf(new Teacher(params[0]));

		    			if(teacher_index!=-1) {
			    			Teacher teacher = (Teacher)entities.get(teacher_index);
			    			TimeConstraint tc = new TimeConstraint(
			    					new Day(params[1]),
			    					new Time(Integer.parseInt(params[2]),Integer.parseInt(params[3])));
			    			teacher.constraints.add(tc);
		    			}
		    		}
		    		break;
		    	case "students":
		    		for(String sg:tokens[1].trim().split("\\s*,\\s*")) {
		    			StudentGroup s = new StudentGroup(sg);
		    			entities.add(s);
		    			students.add(s);
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
			    			room.constraints.add(tc);
			    				
		    			}
		    		}
		    		break;
		    	case "physical_constraints_for_rooms":
		    		for(String constraint:tokens[1].trim().split(" ")) {
		    			String[] params = constraint.split("\\W");
		    			switch(params[0]) {
		    			case "hasProjector":
		    				Integer room_index = rooms.indexOf(new Room(params[1]));

			    			if(room_index!=-1) {
				    			Room room = rooms.get(room_index);
				    			HasProjecterConstraint pc = new HasProjecterConstraint(
				    					Boolean.parseBoolean(params[2]));
				    			room.constraints.add(pc);
			    			}
		    				break;
	    				default:
		    				System.out.println("error on constraint: "+params[0]);
		    			}
		    		}
		    		break;
		    	case "no_classes_to_take":
		    		for(String t : tokens[1].split(",")) {
		    			String attrs[] = t.trim().split("\\W");
		    			Integer teacher_index =entities.indexOf(new Teacher(attrs[0]));
		    			if(teacher_index!=-1) {
		    				Teacher teacher = (Teacher)entities.get(teacher_index);
		    				for(Integer pos=1; pos<attrs.length; pos+=2) {
		    					switch(attrs[pos]) {
		    					case "no_classes":
		    						no_classes_to_take.put(teacher, Integer.parseInt(attrs[pos+1]));
		    						break;
		    					case "needs_projector":
		    						no_classes_to_use_projector.put(teacher, Integer.parseInt(attrs[pos+1]));
		    						break;
	    						default:
	    							System.out.println("error on attribute: "+attrs[pos]);
		    							
		    					}
		    				}
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
		
		for(Entity entity:entities) {
			if(entity instanceof Teacher) {
				Teacher t = (Teacher)entity;
				for(StudentGroup sg:students) {
					t.stalkingOtherEntities.put(sg, new Tuple<Integer, Integer>(no_classes_to_take.get(t), no_classes_to_use_projector.get(t)));
				}
			}
			else {
				StudentGroup sg = (StudentGroup)entity;
				for(Teacher t:teachers)
					sg.stalkingOtherEntities.put(t, new Tuple<Integer, Integer>(no_classes_to_take.get(t), no_classes_to_use_projector.get(t)));
			}
		}
	}
	
	public Parser() {
		super();
		this.times = new ArrayList<Time>();
		this.days = new ArrayList<Day>();
		this.rooms = new ArrayList<Room>();
		
		this.entities = new ArrayList<Entity>();
		this.students = new ArrayList<StudentGroup>();
		this.teachers = new ArrayList<Teacher>();
		
		this.no_classes_to_take = new HashMap<Teacher, Integer>();
		this.no_classes_to_use_projector = new HashMap<Teacher, Integer>();
		this.constraints_for_room = new HashMap<Room, ArrayList<Constraint>>();
	}

}
