package com.spreadtrum.sanity_smoke.action;

public class SmokeResultSequence {
private int total = 0;
private int pass = 0;
private int fail = 0;
private int na = 0;
private int block = 0;

public void addModuleToSequence(String result){

	if(result.equalsIgnoreCase("Pass")){
		pass++;
	}
	else if (result.equalsIgnoreCase("Fail")) {
		fail++;
	}
	else if (result.equalsIgnoreCase("Block")) {
		block++;
	}
	else if (result.equalsIgnoreCase("NA")) {
		na++;
	}
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