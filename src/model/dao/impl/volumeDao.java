package model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.dao.MySqlStatement;

public class volumeDao {


	public ArrayList getVolumeData(){
		String query = "select Date, HSI from timeseriesplotter_volume order by Date";
		ResultSet rs = MySqlStatement.executeSqlQuery(query);
		ArrayList<Map<String,String>> list = new ArrayList<Map<String, String>>();
		try{
			while(rs.next()){
				Map<String,String> dataMap = new HashMap<String, String>();
				String date = rs.getString("Date");
				long volume = rs.getLong("HSI");
				dataMap.put("date", String.valueOf(date));
				dataMap.put("linenum", String.valueOf(volume/1000000));
				list.add(dataMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList getVolumeData(String orderNum, String start, String end){
		String query = "";
		if(start==""||end==""){
			query = "select Date, `"+orderNum+"` from timeseriesplotter_volume order by Date";
		}else{
			query = "select Date, `"+orderNum+"` from timeseriesplotter_volume where Date between '"+start+"' and '"+end+"'order by Date";
		}
		
		ResultSet rs = MySqlStatement.executeSqlQuery(query);
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try {
			while(rs.next()){
				Map<String,String> dataMap = new HashMap<String,String>();
				String	date = rs.getString("Date");
				long volume = rs.getLong(orderNum);
				dataMap.put("date", String.valueOf(date));
				dataMap.put("linenum", String.valueOf(volume/1000000));
				list.add(dataMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args){
		volumeDao dao = new volumeDao();
//		dao.getData();
	}

}
