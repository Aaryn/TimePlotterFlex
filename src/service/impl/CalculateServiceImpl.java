package service.impl;

import java.util.ArrayList;
import java.util.Map;

import service.ICalculateService;
import util.DaoUtil;

public class CalculateServiceImpl implements ICalculateService{

	public ArrayList<Map<String, String>> getMFI(String orderNum, String start, String end) {
		DaoUtil daoUtil = new DaoUtil();
		
		daoUtil.getAveData(orderNum, start, end);
		//daoUtil.getCloseData(orderNum, start, end);
		// TODO Auto-generated method stub
		return null;
	}

}
