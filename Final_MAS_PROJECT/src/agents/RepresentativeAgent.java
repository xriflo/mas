package agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;

import environment.Environment;
import utils.Constraint;
import utils.Entity;
import utils.HasProjecterConstraint;
import utils.Tuple;

public class RepresentativeAgent extends Agent{
	public Entity entity;
	public ArrayList<BookingAgent2> bas;
	
	public RepresentativeAgent(Entity entity, Environment env) {
		this.entity = entity;
		this.bas = new ArrayList<BookingAgent2>();
		for(Entry<Entity, Tuple<Integer, Integer>> entry : entity.stalkingOtherEntities.entrySet()) {
			Entity e = entry.getKey();
			Integer times_to_take = entry.getValue().x;
			Integer times_to_use_projector = entry.getValue().y;
			for(Integer time=0; time<times_to_take; time++) {
				BookingAgent2 ba = new BookingAgent2(this, env);
				ba.stalkingEntity = e;
				if(times_to_use_projector!=0) {
					ba.constraints.add(new HasProjecterConstraint(true));
					times_to_use_projector--;
				}
				else {
					ba.constraints.add(new HasProjecterConstraint(false));
				}
				bas.add(ba);
				env.bas.add(ba);
			}
		}
		
		for(BookingAgent2 ba:bas) {
			ba.brothers = new ArrayList<BookingAgent2>(bas);
			ba.brothers.remove(ba);
		}
	}
	
	public void addRemoveConstraint(Constraint constraint) {
		//TODO infor BAs
	}

	@Override
	public String toString() {
		return "RepresentativeAgent [entity=" + entity + "]";
	}
	
	
}
