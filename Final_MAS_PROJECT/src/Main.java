import environment.BuildEnvironment;
import environment.Environment;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Environment env = new Environment();
		BuildEnvironment be = new BuildEnvironment();
		be.parseTestCase("resources/testCase2");
	}

}
