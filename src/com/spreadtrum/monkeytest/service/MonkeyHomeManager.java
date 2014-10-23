package com.spreadtrum.monkeytest.service;

import java.util.Map;

public interface MonkeyHomeManager {
	
	//停止均值
	Map<String,Float> receiveAverageStopTime();
	//停止中值
	Map<String,Float> receiveMiddleStopTime();
	//首错均值
	Map<String,Float> receiveFirstErrAverageTime();
	//首错中值
	Map<String,Float> receiveFirstErrMiddleTime();

}
