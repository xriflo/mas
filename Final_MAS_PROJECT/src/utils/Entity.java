package utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Entity {
	public HashMap<Entity, Integer> stalkingOtherEntities;
	public Entity() {
		this.stalkingOtherEntities = new HashMap<Entity, Integer>();
	}
	
}
