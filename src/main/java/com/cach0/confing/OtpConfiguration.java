package com.cach0.confing;

import org.springframework.stereotype.Component;

import com.cach0.model.OtpDto;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

@Component
public class OtpConfiguration {

	public static final String OTPS = "otps";
	private final HazelcastInstance hazelcastInstance 
	= Hazelcast.newHazelcastInstance(createConfig2());

	public Config createConfig2() {
		Config config = new Config();
		config.addMapConfig(mapConfig());
		return config;
	}

	private MapConfig mapConfig() {
		MapConfig mapConfig = new MapConfig(OTPS);
		mapConfig.setTimeToLiveSeconds(360);
		mapConfig.setMaxIdleSeconds(120);
		return mapConfig;
	}

	public OtpDto put(String number, OtpDto car){
		IMap<String, OtpDto> map = hazelcastInstance.getMap(OTPS);
		return map.putIfAbsent(number, car);
	}

	public OtpDto get(String key){
		IMap<String, OtpDto> map = hazelcastInstance.getMap(OTPS);
		return map.get(key);
	}

	public String insert(String key, OtpDto request){
		IMap<String, OtpDto> map = hazelcastInstance.getMap(OTPS);
		map.put(key, request);
		return key;
	}
}
