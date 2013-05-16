package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.dao.MySqlStatement;

public class DaoUtil {
	public ArrayList getCloseData(String orderNum, String start, String end){
		String query = "";
		if(start==""||end==""){
		query = "select Date, `"+orderNum+"` from timeseriesplotter_close order by Date";
		}else{
			query = "select Date, `"+orderNum+"` from timeseriesplotter_close where Date between '"+start+"' and '"+end+"' order by Date";
		}
		
		ResultSet rs = MySqlStatement.executeSqlQuery(query);
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try {
			while(rs.next()){
				Map<String,String> dataMap = new HashMap<String,String>();
				String	date = rs.getString("Date");
				float num = rs.getFloat(orderNum);
				dataMap.put("date", String.valueOf(date));
				dataMap.put("linenum", String.valueOf(num));
				list.add(dataMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList getOpenData(String orderNum, String start, String end){
		String query = "";
		if(start==""||end==""){
		query = "select Date, `"+orderNum+"` from timeseriesplotter_open order by Date";
		}else{
			query = "select Date, `"+orderNum+"` from timeseriesplotter_open where Date between '"+start+"' and '"+end+"' order by Date";
		}
		
		ResultSet rs = MySqlStatement.executeSqlQuery(query);
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try {
			while(rs.next()){
				Map<String,String> dataMap = new HashMap<String,String>();
				String	date = rs.getString("Date");
				float num = rs.getFloat(orderNum);
				dataMap.put("date", String.valueOf(date));
				dataMap.put("linenum", String.valueOf(num));
				list.add(dataMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList getHighData(String orderNum, String start, String end){
		String query = "";
		if(start==""||end==""){
		query = "select Date, `"+orderNum+"` from timeseriesplotter_high order by Date";
		}else{
			query = "select Date, `"+orderNum+"` from timeseriesplotter_high where Date between '"+start+"' and '"+end+"' order by Date";
		}
		
		ResultSet rs = MySqlStatement.executeSqlQuery(query);
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try {
			while(rs.next()){
				Map<String,String> dataMap = new HashMap<String,String>();
				String	date = rs.getString("Date");
				float num = rs.getFloat(orderNum);
				dataMap.put("date", String.valueOf(date));
				dataMap.put("linenum", String.valueOf(num));
				list.add(dataMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList getLowData(String orderNum, String start, String end){
		String query = "";
		if(start==""||end==""){
		query = "select Date, `"+orderNum+"` from timeseriesplotter_low order by Date";
		}else{
			query = "select Date, `"+orderNum+"` from timeseriesplotter_low where Date between '"+start+"' and '"+end+"' order by Date";
		}
		
		ResultSet rs = MySqlStatement.executeSqlQuery(query);
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try {
			while(rs.next()){
				Map<String,String> dataMap = new HashMap<String,String>();
				String	date = rs.getString("Date");
				float num = rs.getFloat(orderNum);
				dataMap.put("date", String.valueOf(date));
				dataMap.put("linenum", String.valueOf(num));
				list.add(dataMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList getAveData(String orderNum, String start, String end){
		String query = "";
		if(orderNum==""){
			query = "select Date, sum(HSI) as HSI from (select Date, HSI from timeseriesplotter_close union all select Date, HSI from timeseriesplotter_open union all select Date, HSI from timeseriesplotter_high union all select Date, HSI from timeseriesplotter_low) as a group by Date";
		}
		
		if(start==""||end==""){
			query = "select Date, sum(`"+orderNum+"`) as HSI from (select Date, HSI from timeseriesplotter_close union all select Date, HSI from timeseriesplotter_open union all select Date, HSI from timeseriesplotter_high union all select Date, HSI from timeseriesplotter_low) as a group by Date";
		}
		{
			query = "select Date, sum(`"+orderNum+"`) as HSI from (select Date, `"+orderNum+"` from timeseriesplotter_close where Date between '"+start+"' and '"+end+"' union all select Date, `"+orderNum+"` from timeseriesplotter_open where Date between '"+start+"' and '"+end+"' union all select Date, `"+orderNum+"` from timeseriesplotter_high where Date between '"+start+"' and '"+end+"' union all select Date, `"+orderNum+"` from timeseriesplotter_low where Date between '"+start+"' and '"+end+"') as a group by Date";
		}
		ResultSet rs = MySqlStatement.executeSqlQuery(query);
		
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		try {
			while(rs.next()){
				Map<String,String> dataMap = new HashMap<String,String>();
				String	date = rs.getString("Date");
				float num = rs.getFloat(orderNum);
				dataMap.put("date", String.valueOf(date));
				dataMap.put("linenum", String.valueOf(num/4));
				list.add(dataMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

}
