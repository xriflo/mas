package tools;

import environment.Environment;
import environment.GridSpace;

public class BuildEnvironment {
	public BuildEnvironment() {
		// TODO Auto-generated constructor stub
	}
	
	public void createEnv(String testCaseName) {
		Parser parser = new Parser();
		parser.parseTestCase(testCaseName);
		GridSpace grid = new GridSpace(parser.days, parser.times, parser.rooms, parser.constraints_for_room);
		System.out.println(grid);
		System.out.println("gridsize: "+grid.cells.size());
		Environment env = new Environment(grid);
	}
}
