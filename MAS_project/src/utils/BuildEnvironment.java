package utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BuildEnvironment {
	ArrayList<Time> time;

	public void parseTestCase(String testCaseName) {
		try(BufferedReader br = new BufferedReader(new FileReader(testCaseName))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] tokens = line.split(":");
		    	switch(tokens[0]) {
		    	case "time":
		    		String[] intervals = tokens[1].trim().split("\\s*,\\s*");
		    		for(String interval:intervals) {
		    			String[] hours = interval.split("-");
		    			time.add(new Time(Integer.parseInt(hours[0]), Integer.parseInt(hours[1])));
		    		}
		    		System.out.println(time);
		    		break;
		    	case "days":
		    		break;
		    	case "teachers":
		    		break;
		    	case "constraints_for_teachers":
		    		break;
		    	case "students":
		    		break;
		    	case "rooms":
		    		break;
		    	case "time_constraints_for_rooms":
		    		break;
		    	case "physical_constraints_for_rooms":
		    		break;
	    		default:
	    			System.out.println("error");
		    	}
		    }
		    // line is not visible here.
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public BuildEnvironment() {
		super();
		this.time = new ArrayList<Time>();
	}

}
