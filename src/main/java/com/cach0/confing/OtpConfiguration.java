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
	= Hazelcast.newHazelcastInstance(createConfigOtp());

	public Config createConfigOtp() {
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

	public OtpDto put(String number, OtpDto dto){
		IMap<String, OtpDto> map = hazelcastInstance.getMap(OTPS);
		return map.put(number, dto);
	}

	public OtpDto get(String key){
		IMap<String, OtpDto> map = hazelcastInstance.getMap(OTPS);
		return map.get(key);
	}

	public String insert(String key, OtpDto dto){
		IMap<String, OtpDto> map = hazelcastInstance.getMap(OTPS);
		map.putIfAbsent(key, dto);
		return key;
	}
}
