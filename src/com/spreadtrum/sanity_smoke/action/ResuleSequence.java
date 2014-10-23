package com.spreadtrum.sanity_smoke.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResuleSequence {
private List<String> modulesList = new ArrayList<String>();
private String passList;
private String failList;
private String naList;
private String blockList;
private	Map<String, ArrayList<Integer>> map = new TreeMap<String, ArrayList<Integer>>();

public ResuleSequence() {
	// TODO Auto-generated constructor stub
	map.put("phone",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("filemanager",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("mms",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("sms",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("flymode",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("blacklight",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("dialer",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("gallery",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("calender",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("alarm",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("music",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("video",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("camera",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("browers",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("map",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("calculator",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("recording",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
	map.put("radio",new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);}});
}

void addModuleToSequence(String module,String result){
	if(result.equals("pass")){
		map.get(module).set(0, map.get(module).get(0) + 1);
	}
	else if (result.equals("fail")) {
		map.get(module).set(1, map.get(module).get(1) + 1);
	}
	else if (result.equals("block")) {
		map.get(module).set(2, map.get(module).get(2) + 1);
	}
	else if (result.equals("na")) {
		map.get(module).set(3, map.get(module).get(3) + 1);
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
	return passList;
}



public String getFailList() {
	return failList;
}



public String getNaList() {
	return naList;
}



public String getBlockList() {
	return blockList;
}



}
