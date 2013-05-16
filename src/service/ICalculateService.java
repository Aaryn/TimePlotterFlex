package service;

import java.util.ArrayList;
import java.util.Map;

public interface ICalculateService {
	public ArrayList<Map<String,String>> getMFI(String orderNum, String start, String end);
//	RSI calculate
}
