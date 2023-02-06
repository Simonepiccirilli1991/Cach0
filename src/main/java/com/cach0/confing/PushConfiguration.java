package com.cach0.confing;

import org.springframework.stereotype.Component;

import com.cach0.model.PushDto;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

@Component
public class PushConfiguration {

	public static final String PUSH = "push";
	private final HazelcastInstance hazelcastInstance 
	= Hazelcast.newHazelcastInstance(createConfigPush());

	public Config createConfigPush() {
		Config config = new Config();
		config.addMapConfig(mapConfig());
		return config;
	}

	private MapConfig mapConfig() {
		MapConfig mapConfig = new MapConfig(PUSH);
		mapConfig.setTimeToLiveSeconds(360);
		mapConfig.setMaxIdleSeconds(120);
		return mapConfig;
	}

	public PushDto put(String number, PushDto push){
		IMap<String, PushDto> map = hazelcastInstance.getMap(PUSH);
		return map.put(number, push);
	}

	public PushDto get(String key){
		IMap<String, PushDto> map = hazelcastInstance.getMap(PUSH);
		return map.get(key);
	}

	public String insert(String key, PushDto push){
		IMap<String, PushDto> map = hazelcastInstance.getMap(PUSH);
		map.putIfAbsent(key, push);
		return key;
	}
	
}
