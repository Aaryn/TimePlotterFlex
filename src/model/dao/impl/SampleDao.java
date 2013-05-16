package model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import model.dao.MySqlStatement;


public class SampleDao {

	public ArrayList getLineData(){
		String query = "select Date, HSI from timeseriesplotter_close order by Date";
		ResultSet rs = MySqlStatement.executeSqlQuery(query);
		ArrayList<Map<String,String>> list = new ArrayList<Map<String, String>>();
		try{
			while(rs.next()){
				Map<String,String> dataMap = new HashMap<String, String>();
				String date = rs.getString("Date");
				float num = rs.getFloat("HSI");
				dataMap.put("date", String.valueOf(date));
				dataMap.put("linenum", String.valueOf(num));
				list.add(dataMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		finally{
//			MySqlStatement.stop(rs);
//		}
		return list;
	}
	
	public ArrayList getLineData(String orderNum, String start, String end){
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
			e.printStackTrace();
		}
//		finally{
//			MySqlStatement.stop(rs);
//		}
		return list;
	}
	
	public ArrayList getAveData(String orderNum, String start, String end){
		String query = "";
		
		if(start==""||end==""){
			query = "select Date, sum(HSI) as HSI from (select Date, HSI from timeseriesplotter_close union all select Date, HSI from timeseriesplotter_high union all select Date, HSI from timeseriesplotter_low) as a group by Date";
			
		}else
		{
			query = "select Date, sum(`"+orderNum+"`) as `"+orderNum+"` from (select Date, `"+orderNum+"` from timeseriesplotter_close where Date between '"+start+"' and '"+end+"' union all select Date, `"+orderNum+"` from timeseriesplotter_high where Date between '"+start+"' and '"+end+"' union all select Date, `"+orderNum+"` from timeseriesplotter_low where Date between '"+start+"' and '"+end+"') as a group by Date";
			
		}
		ResultSet rs = MySqlStatement.executeSqlQuery(query);
		
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		try {
			while(rs.next()){
				Map<String,String> dataMap = new HashMap<String,String>();
				String	date = rs.getString("Date");
				float num = rs.getFloat(orderNum);				
				dataMap.put("date", String.valueOf(date));
				dataMap.put("linenum", String.valueOf(num/3));
				list.add(dataMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally{
//			MySqlStatement.stop(rs);
//			
//		}
		return list;
	}
	public ArrayList getCalData(String orderNum, String start, String end){
		 ArrayList newList = new ArrayList();
		 ArrayList data1 = getAveData(orderNum, start, end);
		 volumeDao volume = new volumeDao();
		 ArrayList data2 = volume.getVolumeData(orderNum, start, end);
		 
		 for(int i = 13; i<data1.size(); i++){
			 Map<String, String> dataMap = new HashMap<String, String>();
			 float totalAdd = 0;
			 float totalDecrease = 0;
			 for(int j = 0; j<13; j++){
				 Map<String, String> dataMap1 = (Map<String, String>) data1.get(i-j);
				 Map<String, String> dataMap2 = (Map<String, String>) data2.get(i-j);
				 Map<String, String> dataMap12 = (Map<String, String>) data1.get(i-j-1);
				 Map<String, String> dataMap22 = (Map<String, String>) data2.get(i-j-1);
				 
				 float num1 = Float.parseFloat(dataMap1.get("linenum"));
				 float num2 = Float.parseFloat(dataMap2.get("linenum"));
				 float num12 = Float.parseFloat(dataMap12.get("linenum"));
				 float num22 = Float.parseFloat(dataMap22.get("linenum"));
				 
				 float sub = num1*num2 - num12*num22;
				 if(sub>=0){
					 totalAdd = totalAdd + sub;
				 }else{
					 totalDecrease = totalDecrease - sub;
				 }
				 
			 }
			 float mf = totalAdd/totalDecrease;
			 float mfi = 100-(100/(1+mf));
			 
			 Map<String, String> rDataMap = (Map<String, String>) data1.get(i);
			 String date = rDataMap.get("date");
			 dataMap.put("date", String.valueOf(date));
			 dataMap.put("linenum", String.valueOf(mfi));
			 newList.add(dataMap);
		 }
		
		
		return newList;
		
	}
	
	
	
	public static void main(String[] args){
		SampleDao dao = new SampleDao();
		Date date = new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd");
		System.out.println(df.format(date));
//		dao.getData();
	}
}
