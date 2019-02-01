package com.almundo.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.almundo.test.model.Call;

public class ResourcesCall {
	
	private ResourcesCall() {}
	
	private static Map<Integer,Call> actualCalls = new ConcurrentHashMap<>();

	public static Map<Integer, Call> getActualCalls() {
		return actualCalls;
	}

}
