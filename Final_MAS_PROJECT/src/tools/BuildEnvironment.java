package tools;

import java.util.ArrayList;

import agents.RepresentativeAgent;
import environment.Environment;
import environment.GridSpace;
import utils.Entity;
import utils.Teacher;

public class BuildEnvironment {
	Environment env;
	ArrayList<agents.RepresentativeAgent> ras;
	
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
}
