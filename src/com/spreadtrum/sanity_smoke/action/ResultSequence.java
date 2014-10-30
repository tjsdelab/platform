package com.spreadtrum.sanity_smoke.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResultSequence {
private List<String> modulesList = new ArrayList<String>();
private String passList="";
private String failList="";
private String naList="";
private String blockList="";
private int total = 0;
private int pass = 0;
private int fail = 0;
private int na = 0;
private int block = 0;
private	Map<String, ArrayList<Integer>> map = new TreeMap<String, ArrayList<Integer>>();


void addModuleToSequence(String module,String result){
	if(map.get(module) == null){
	map.put(module, new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	}

	if(result.equalsIgnoreCase("Pass")){
		map.get(module).set(0, map.get(module).get(0) + 1);
		pass++;
	}
	else if (result.equalsIgnoreCase("Fail")) {
		map.get(module).set(1, map.get(module).get(1) + 1);
		fail++;
	}
	else if (result.equalsIgnoreCase("NA")) {
		map.get(module).set(2, map.get(module).get(2) + 1);
		na++;
	}
	else if (result.equalsIgnoreCase("Block")) {
		map.get(module).set(3, map.get(module).get(3) + 1);
		block++;
	}
}

public List<String> getModulesList() {
	//modulesList = map.keySet();
	for(String key : map.keySet()){
		modulesList.add(key);
	}
	return modulesList;
}



public String getPassList() {
	for(String key : modulesList){
		passList += map.get(key).get(0) + ",";
	}
	if(passList.length() > 0){
	passList = passList.substring(0, passList.length() - 1);
	}
	return passList;
}

public String getFailList() {
	for(String key : modulesList){
		failList += map.get(key).get(1) + ",";
	}
	if(failList.length() > 0){
	failList = failList.substring(0, failList.length() - 1);
	}
	return failList;
}

public String getNaList() {
	for(String key : modulesList){
		naList += map.get(key).get(2) + ",";
	}
	if(naList.length() > 0){
	naList = naList.substring(0, naList.length() - 1);
	}
	return naList;
}



public String getBlockList() {
	for(String key : modulesList){
		blockList += map.get(key).get(3) + ",";
	}
	if(blockList.length() > 0){
	blockList = blockList.substring(0, blockList.length() - 1);
	}
	return blockList;
}

public int getTotal() {
	total = pass + fail + na + block;
	return total;
}



public int getPass() {
	return pass;
}

public int getFail() {
	return fail;
}



public int getNa() {
	return na;
}



public int getBlock() {
	return block;
}
}