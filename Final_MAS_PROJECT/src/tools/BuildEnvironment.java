package tools;

import environment.GridSpace;

public class BuildEnvironment {
	public BuildEnvironment() {
		// TODO Auto-generated constructor stub
	}
	
	public void createEnv(String testCaseName) {
		Parser parser = new Parser();
		parser.parseTestCase(testCaseName);
		
		GridSpace grid = new GridSpace(parser.days, parser.times, parser.rooms);
	}
}
