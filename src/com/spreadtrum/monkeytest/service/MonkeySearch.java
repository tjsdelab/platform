package com.spreadtrum.monkeytest.service;

import java.util.List;

import com.spreadtrum.monkeytest.model.TestForm;

public interface MonkeySearch {
	//模糊搜索输入的内容，得到表单号List
	List<TestForm> findMatchedFormNames(String input,String type);
	

}
