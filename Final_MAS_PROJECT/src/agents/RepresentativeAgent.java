package agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import environment.Environment;
import utils.Entity;

public class RepresentativeAgent {
	Entity entity;
	ArrayList<BookingAgent> bas;
	
	public RepresentativeAgent(Entity entity, Environment env) {
		this.entity = entity;
		this.bas = new ArrayList<BookingAgent>();
		for(Entry<Entity, Integer> entry : entity.stalkingOtherEntities.entrySet()) {
			Entity e = entry.getKey();
			Integer times = entry.getValue();
			for(Integer time=0; time<times; time++) {
				BookingAgent ba = new BookingAgent(this, env);
				ba.stalkingEntity = e;
				bas.add(new BookingAgent(this, env));
			}
		}
	}
}
