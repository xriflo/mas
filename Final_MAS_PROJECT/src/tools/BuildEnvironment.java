package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import agents.BookingAgent;
import agents.BookingAgent2;
import agents.RepresentativeAgent;
import environment.Environment;
import environment.GridSpace;
import utils.Day;
import utils.Entity;
import utils.Teacher;
import utils.Time;
import utils.TimeConstraint;

public class BuildEnvironment {
	public Environment env;
	
	public BuildEnvironment() {
		// TODO Auto-generated constructor stub
	}
	
	public void createEnv(String testCaseName) {
		//parse test case
		Parser parser = new Parser();
		parser.parseTestCase(testCaseName);
		//build env
		GridSpace grid = new GridSpace(parser.days, parser.times, parser.rooms, parser.constraints_for_room);
		env = new Environment(grid);
		//create agents
		for(Entity entity : parser.entities) {
			env.ras.add(new RepresentativeAgent(entity, env));
		}
	}
	
	public void runEnv() {
		//run agents
		while(true) {
			verifyAddRemoveConstraints();
			for(BookingAgent2 ba:env.bas) {
				ba.time += 1F;
				ba.doTheMonkeyBusiness();
			}
			//break; //-->remove this
		}
	}
	
	public void verifyAddRemoveConstraints() {
		try(BufferedReader br = new BufferedReader(new FileReader("resources/addRemoveConstraints"))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] tokens = line.split(":");
		    	switch(tokens[0]) {
		    	case "time_constraints_for_teachers":
		    		for(String constraint:tokens[1].trim().split(" ")) {
		    			String[] params = constraint.split("\\W");
		    			Teacher t = new Teacher(params[0]);
		    			TimeConstraint tc = new TimeConstraint(
		    					new Day(params[2]), 
		    					new Time(Integer.parseInt(params[3]), Integer.parseInt(params[4])));
		    			tc.addRemove = params[1];
		    		}
		    		break;
		    	default:
		    		System.out.println("error adding/removing constraints: "+tokens[1]);
		    		break;
		    	}
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
