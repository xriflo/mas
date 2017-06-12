package utils;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Entity {
	public ArrayList<Constraint> constraints;
	public HashMap<Entity, Tuple<Integer, Integer>> stalkingOtherEntities;
	
	public Entity() {
		this.stalkingOtherEntities = new HashMap<Entity, Tuple<Integer, Integer>>();
		this.constraints = new ArrayList<Constraint>();
	}
	
}
