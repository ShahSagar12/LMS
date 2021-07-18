package com.lms.model;

import java.util.HashMap;
import java.util.Map;

public class RoleTypes {
	public static final Map<Integer,String> roles;
	
	static {
		roles=new HashMap<>();
		roles.put(1,"Admin");
		roles.put(2, "User");
	}

}
