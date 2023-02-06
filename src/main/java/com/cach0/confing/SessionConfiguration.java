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
	= Hazelcast.newHazelcastInstance(createConfigSession());

	public Config createConfigSession() {
		Config config = new Config();
		config.addMapConfig(mapConfig());
		return config;
	}

	private MapConfig mapConfig() {
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

	public String insert(String key, SessionDto session){
		IMap<String, SessionDto> map = hazelcastInstance.getMap(SESSIONS);
		map.putIfAbsent(key, session);
		return key;
	}
}
