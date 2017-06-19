import java.util.Iterator;
import java.util.Map;

import agents.BookingAgent2;
import agents.RepresentativeAgent;
import tools.BuildEnvironment;
import utils.Entity;
import utils.Room;
import utils.Tuple;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuildEnvironment be = new BuildEnvironment();
		be.createEnv("resources/testCase2");
		
		printInfo(be);
		be.runEnv();
	}
	
	public static void printInfo(BuildEnvironment be) {
		System.out.println("--------RAs--------");
		for(RepresentativeAgent ra:be.env.ras) {
			System.out.print("RA: "+ra.entity);
			System.out.print(" is stalking: ");
			Iterator it = ra.entity.stalkingOtherEntities.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        Entity e = (Entity) pair.getKey();
		        Tuple<Integer, Integer> v = (Tuple<Integer, Integer>) pair.getValue();
		        System.out.print(e + " = with no_courses=" + v.x + " and no_projector=" + v.y+", ");
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    System.out.println();
		}
		System.out.println();
		System.out.println("--------BAs--------");
		for(RepresentativeAgent ra:be.env.ras) {
			System.out.println("RA: "+ra.entity);
			for(BookingAgent2 ba:ra.bas) {
				System.out.println(ba.representingEntity +"="+ba.getClass() + " stalking entity " + ba.stalkingEntity + " with constraints "+ba.constraints);
			}
		}
		
		System.out.println();
		System.out.println("-------ROOMs-------");
		for(Room room:be.env.grid.rooms)
			System.out.println("Room "+room+" has constraints: "+room.constraints);
	}
}
