package com.jin.calendar.model;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class LeaderSchedule extends Model<LeaderSchedule>{

	private static final long serialVersionUID = 1L;
	
	public static final LeaderSchedule dao = new LeaderSchedule();
	
    /**
     * @param leaderid
     * @param start
     * @param end
     * @return
     */
	public List<LeaderSchedule> getDurationEventsByRoomId(int leaderid, long start, long end) {
		String sqlStr = "select x.*,y.name username,y.email, z.leader_name leadername from leader_schedule x left join user y on x.userid = y.id left join leader_info z on x.leaderid=z.leaderid "
				+ " where x.leaderid= ? and x.start>=from_unixtime(?) and x.start<from_unixtime(?) and x.isdelete='0'";
		return dao.find(sqlStr,leaderid,start,end);
	}
	
	/**
	 * 检验同领导下是否有事记与新增或修改后的事记的时间有重叠
	 * @param start 会议开始时间
	 * @param end 会议结束时间
	 * @param leaderid 会议室id 
	 * @param id 领导事件id 
	 * @return false：有重叠，true：无重叠 
	 */
	public boolean isLegalEvent(long start, long end, Integer leaderid, Integer id){
		String sql1="select count(*) from leader_schedule "
				+ " where start>from_unixtime(?) and start<from_unixtime(?) and leaderid = ? and id <> ?";
		String sql2="select count(*) from leader_schedule "
				+ " where end>from_unixtime(?) and end<from_unixtime(?) and leaderid = ? and id <> ?";
		String sql3="select count(*) from leader_schedule "
				+ " where start<=from_unixtime(?) and end>=from_unixtime(?) and leaderid = ? and id <> ?";

		Long n1 = Db.queryLong(sql1,start,end,leaderid,id);
		Long n2 = Db.queryLong(sql2,start,end,leaderid,id);
		Long n3 = Db.queryLong(sql3,start,end,leaderid,id);
		
		//System.out.println(n1+"-"+n2+"-"+n3);
		return (n1+n2+n3) == 0 ? true : false;
	}
	
	/**
	 * 检验同领导下是否有事记与新增或修改后的事记的时间有重叠
	 * @param start 会议开始时间
	 * @param end 会议结束时间
	 * @param leaderid 会议室id 
	 * @param id 领导事件id 
	 * @return false：有重叠，true：无重叠 
	 */
	public boolean isLegalEvent2(Timestamp start, Timestamp end, Integer leaderid, Integer id){
		String sql1="select count(*) from leader_schedule "
				+ " where start>? and start<? and leaderid = ? and id <> ?";
		String sql2="select count(*) from leader_schedule "
				+ " where end>? and end<? and leaderid = ? and id <> ?";
		String sql3="select count(*) from leader_schedule "
				+ " where start<=? and end>=? and leaderid = ? and id <> ?";

		Long n1 = Db.queryLong(sql1,start,end,leaderid,id);
		Long n2 = Db.queryLong(sql2,start,end,leaderid,id);
		Long n3 = Db.queryLong(sql3,start,end,leaderid,id);
		
		//System.out.println(n1+"-"+n2+"-"+n3);
		return (n1+n2+n3) == 0 ? true : false;
	}
	/**
	 * 查看领导当天的事记录
	 * @return
	 */
	public List<LeaderSchedule> getTodayEvent(){
		String sqlStr="select x.*,y.name username,y.tel,y.email, z.leader_name leadername from leader_schedule x "
				+" left join user y on x.userid = y.id "
				+" left join leader_info z on x.leaderid=z.leaderid "
				+" where to_days(start)=to_days(now()) and x.isdelete='0' order by start asc";
		return dao.find(sqlStr);
	}
	/**
	 * 分页查询
	 * 根据日期,事迹类型,领导名查询
	 * @param start
	 * @param end
	 * @param type
	 * @param name
	 * @param pagesize
	 * @param pagenum
	 * @return
	 */
	public Page<LeaderSchedule> getEventBysDate(String start, String end, String type, String name, Integer pagesize, Integer pagenum){
		
		String sqlSelect = "select x.*,y.name username,y.tel,y.email, z.*";

		String sqlFrom=" from leader_schedule x "
				+" left join user y on x.userid = y.id "
				+" left join leader_info z on x.leaderid=z.leaderid "
				+" where x.isdelete='0' ";
	    if(!"".equals(type) && null != type){
			sqlFrom = sqlFrom + " and x.sj_type = '"+type+"' ";
		}
	    if(!"".equals(start) && start != null){
	    	sqlFrom += " and x.start > "+start;
		}
		if(!"".equals(end) && end != null){
			sqlFrom += " and x.start < "+end;
		}
		if(!"".equals(name) && name != null){
			sqlFrom += " and z.leader_name like '%"+name+"%' ";
		}
	    
		sqlFrom = sqlFrom +" order by start asc";
		
		return dao.paginate(pagenum, pagesize, sqlSelect, sqlFrom);
		
	}
	/**
	 * 查看未过期的
	 * @param status
	 * @return
	 */
	public List<LeaderSchedule> getEvent(){
		
		String sqlStr="select x.*,y.name username,y.tel,y.email, z.leader_name leadername from leader_schedule x "
				+" left join user y on x.userid = y.id "
				+" left join leader_info z on x.leaderid=z.leaderid "
				+" where to_days(start)>to_days(now()) order by start asc";
		return dao.find(sqlStr);
		
	}
	
}
