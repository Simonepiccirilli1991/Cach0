package com.cach0.confing;

import org.springframework.stereotype.Component;

import com.cach0.model.OtpDto;
import com.cach0.model.SessionDto;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

@Component
public class SessionConfiguration {

	public static final String SESSIONS = "sessions";
	private final HazelcastInstance hazelcastInstance 
	= Hazelcast.newHazelcastInstance(createConfig1());

	public Config createConfig1() {
		Config config = new Config();
		config.addMapConfig(mapConfig1());
		return config;
	}

	private MapConfig mapConfig1() {
		MapConfig mapConfig = new MapConfig(SESSIONS);
		mapConfig.setTimeToLiveSeconds(800);
		mapConfig.setMaxIdleSeconds(620);
		return mapConfig;
	}

	public SessionDto put(String number, SessionDto session){
		IMap<String, SessionDto> map = hazelcastInstance.getMap(SESSIONS);
		return map.put(number, session);
	}

	public SessionDto get(String key){
		IMap<String, SessionDto> map = hazelcastInstance.getMap(SESSIONS);
		return map.get(key);
	}

	public String insert(String key, SessionDto request){
		IMap<String, SessionDto> map = hazelcastInstance.getMap(SESSIONS);
		map.putIfAbsent(key, request);
		return key;
	}
}
