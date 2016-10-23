package com.jin.calendar.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class LeaderInfo  extends Model<LeaderInfo> {
	public static final LeaderInfo dao=new LeaderInfo();
	
	public List<LeaderInfo> getAllLeaderInfo(){
		return dao.find("select * from leader_info where isdelete = '0'");
	}
}
