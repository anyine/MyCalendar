package com.jin.calendar.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class RoomSchedule extends Model<RoomSchedule> {
	public static final RoomSchedule dao = new RoomSchedule();
	
	/**
	 * 查询当前会议室，用户下在这个时间段内的事件
	 * @param roomId
	 * @param start
	 * @param end
	 * @param userid
	 * @return
	 */
	public List<RoomSchedule> getDurationEventsByRoomId(int roomId, long start, long end, int userid) {
		String sqlStr = "select x.*,y.name username,y.email, z.name roomname from room_schedule x left join user y on x.userid = y.id left join room z on x.roomid=z.id "
				+ " where x.roomid= ? and x.start>=from_unixtime(?) and x.start<from_unixtime(?) and x.userid=?";
		//System.out.println(sqlStr);
		return dao.find(sqlStr,roomId,start,end,userid);
	}
	/**
	 * 查询当前会议室，下在这个时间段内的通过审批的事件
	 * @param roomId
	 * @param start
	 * @param end
	 * @param userid
	 * @return
	 */
	public List<RoomSchedule> getDurationEventsByRoomIdS(int roomId, long start, long end) {
		String sqlStr = "select x.*,y.name username,y.email, z.name roomname from room_schedule x left join user y on x.userid = y.id left join room z on x.roomid=z.id "
				+ " where x.roomid= ? and x.start>=from_unixtime(?) and x.start<from_unixtime(?) and x.room_sh_status='1'";
		//System.out.println(sqlStr);
		return dao.find(sqlStr,roomId,start,end);
	}
	
	/**
	 * 检验同会议室下是否有会议与新增或修改后的会议的时间有重叠
	 * @param start 会议开始时间
	 * @param end 会议结束时间
	 * @param roomId 会议室id 
	 * @param id 会议事件id 
	 * @return false：有重叠，true：无重叠 
	 */
	public boolean isLegalEvent(long start, long end, Integer roomId, Integer id, Integer userid){
		String sql1="select count(*) from room_schedule "
				+ " where start>from_unixtime(?) and start<from_unixtime(?) and roomid = ? and id <> ?";
		String sql2="select count(*) from room_schedule "
				+ " where end>from_unixtime(?) and end<from_unixtime(?) and roomid = ? and id <> ?";
		String sql3="select count(*) from room_schedule "
				+ " where start<=from_unixtime(?) and end>=from_unixtime(?) and roomid = ? and id <> ?";

		Long n1 = Db.queryLong(sql1,start,end,roomId,id);
		Long n2 = Db.queryLong(sql2,start,end,roomId,id);
		Long n3 = Db.queryLong(sql3,start,end,roomId,id);
		
		//System.out.println(n1+"-"+n2+"-"+n3);
		return (n1+n2+n3) == 0 ? true : false;
	}
	
	/**
	 * 检验同会议室下是否有通过审批的会议与新增或修改后的会议的时间有重叠
	 * @param start 会议开始时间
	 * @param end 会议结束时间
	 * @param roomId 会议室id 
	 * @param id 会议事件id 
	 * @return false：有重叠，true：无重叠 
	 */
	public boolean isLegalEventPast(long start, long end, Integer roomId, Integer id, Integer userid){
		String sql1="select count(*) from (SELECT * from room_schedule where room_sh_status = '1') x "
				+ " where x.start>from_unixtime(?) and x.start<from_unixtime(?) and x.roomid = ? and x.id <> ?";
		String sql2="select count(*) from (SELECT * from room_schedule where room_sh_status = '1') x "
				+ " where x.end>from_unixtime(?) and x.end<from_unixtime(?) and x.roomid = ? and x.id <> ?";
		String sql3="select count(*) from (SELECT * from room_schedule where room_sh_status = '1') x "
				+ " where x.start<=from_unixtime(?) and x.end>=from_unixtime(?) and x.roomid = ? and x.id <> ?";

		Long n1 = Db.queryLong(sql1,start,end,roomId,id);
		Long n2 = Db.queryLong(sql2,start,end,roomId,id);
		Long n3 = Db.queryLong(sql3,start,end,roomId,id);
		
		//System.out.println(n1+"-"+n2+"-"+n3);
		return (n1+n2+n3) == 0 ? true : false;
	}
	
	/**
	 * 查询当天审批通过的会议
	 * @return
	 */
	public List<RoomSchedule> getTodayEvent(){
		String sqlStr="select x.*,y.name username,y.tel,y.email, z.name roomname from room_schedule x "
				+" left join user y on x.userid = y.id "
				+" left join room z on x.roomid=z.id "
				+" where to_days(start)=to_days(now()) and x.room_sh_status='1' order by start asc";
		return dao.find(sqlStr);
	}
	/**
	 * 根据日期,审批状态查询会议室预定情况
	 * @param date
	 * @return
	 */
	public List<RoomSchedule> getEventBysDate(String date, String status){

		String sqlStr="select x.*,y.name username,y.tel,y.email, z.name roomname from room_schedule x "
				+" left join user y on x.userid = y.id "
				+" left join room z on x.roomid=z.id "
				+" where ";
		if(!"".equals(date) && null != date){
			sqlStr = sqlStr + " to_days(start) like to_days('"+date+"')";
		}else if(!"".equals(status) && null != status){
			sqlStr = sqlStr + " x.room_sh_status = '"+status+"'";
		}
		sqlStr = sqlStr +" order by start asc";
		return dao.find(sqlStr);
		
	}
	/**
	 * 查看未过期的会议室预约情况
	 * @param status
	 * @return
	 */
	public List<RoomSchedule> getEvent(){
		
		String sqlStr="select x.*,y.name username,y.tel,y.email, z.name roomname from room_schedule x "
				+" left join user y on x.userid = y.id "
				+" left join room z on x.roomid=z.id "
				+" where to_days(start)>to_days(now()) order by start asc";
		return dao.find(sqlStr);
		
	}
	

	
}
