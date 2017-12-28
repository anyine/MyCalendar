package com.jin.calendar.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jin.calendar.bo.DwzResponseBO;
import com.jin.calendar.common.CommonConstant;
import com.jin.calendar.model.Room;
import com.jin.calendar.model.RoomSchedule;

/**
 * 会议室Controller控制类
 */
public class RoomController extends Controller {

	public void index(){
		Page<Room> page = Room.dao.paginate(CommonConstant.PAGENUMBER, CommonConstant.PAGESIZE, "select *", "from room where isdelete = '0'");
		setAttr("page", page);
		render("room.jsp");
	}

	public void getPageList(){
		//String sqlStr = "";
		Page<Room> page = Room.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"), "select *", "from room where isdelete = '0'");
		setAttr("page", page);
		render("room.jsp");
	}
	
	public void goAdd(){
		render("room_add.jsp");
	}
	
	public void add(){
		DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"),"closeCurrent");
		boolean flag = new Room().set("name", getPara("name")).set("room_size", getPara("size")).set("room_position", getPara("position")).save();
		if(!flag){
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);
						
	}
	
	public void del(){
		DwzResponseBO responseBO = new DwzResponseBO();
		//boolean flag=Db.deleteById("room", getParaToInt());
		//int flag = Db.update("update room set isdelete = '1' where id = ?", getParaToInt());
		boolean flag = Room.dao.findById(getParaToInt()).set( "isdelete", "1").update();
		if(!flag){
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);
	}
	
	public void isUnique(){
		List<Record> list = Db.find("select * from room where isdelete = '0' and name= ?", getPara("name"));
		if(list.size()>0){
			renderText("false");
		}else{
			renderText("true");
		}
	}
	
	/**
	 * 查询预约会议室事件
	 */
	public void indexSch(){
		
		String sqlStr=" from room_schedule x "
				+" left join user y on x.userid = y.id "
				+" left join user u on x.check_userid = u.id "
				+" left join room z on x.roomid=z.id "
				+" where unix_timestamp(start)>unix_timestamp(now()) ";
		
		String status = getPara("status");
		String start = getPara("statrt");
		
		if(!"".equals(status) && status != null){
			sqlStr += " and x.room_sh_status ="+status;
		}if(!"".equals(start) && start != null){
			sqlStr += " and x.start like "+start;
		}
		sqlStr += " order by start asc";
		
		Page<RoomSchedule> page = RoomSchedule.dao.paginate(CommonConstant.PAGENUMBER, CommonConstant.PAGESIZE, "select x.*,y.name username,u.name checkname,y.tel,y.email, z.name roomname", sqlStr);
		
		setAttr("page", page);
		render("room_need.jsp");
	}
	
	public void getPageListSch(){
		String sqlStr=" from room_schedule x "
				+" left join user y on x.userid = y.id "
				+" left join user u on x.check_userid = u.id "
				+" left join room z on x.roomid=z.id "
				+" where unix_timestamp(start)>unix_timestamp(now())";
		
		String status = getPara("status");
		String start = getPara("sdate");
		String end = getPara("edate");
		String roomname = getPara("roomname");
		
		if(!"".equals(status) && status != null){
			sqlStr += " and x.room_sh_status = "+status;
		}
		if(!"".equals(start) && start != null){
			sqlStr += " and x.start > "+start;
		}
		if(!"".equals(end) && end != null){
			sqlStr += " and x.start < "+end;
		}
		if(!"".equals(roomname) && roomname != null){
			sqlStr += " and z.name like '%"+roomname+"%' ";
		}
		sqlStr += " order by start asc";
		
		Page<RoomSchedule> page = RoomSchedule.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"), "select x.*,y.name username,u.name checkname,y.tel,y.email, z.name roomname", sqlStr);
		setAttr("page", page);
		render("room_need.jsp");
	}
	
	
	/**
	 * 更新预约会议室事件
	 * 审批通过
	 */
	public void updateSta(){
		DwzResponseBO responseBO = new DwzResponseBO();
		int id = getParaToInt();
		//System.out.println();
		int flag = Db.update("update room_schedule rs set rs.room_sh_status = '1', rs.check_userid = ? where id = ?", getSessionAttr("userId"), id);
		if(flag == 0 || flag < 0){ 
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);

	}
	/**
	 * 更新预约会议室事件
	 * 审批未通过(拒绝)
	 */
	public void updateStaF(){
		DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"),"closeCurrent") ;
		int id = getParaToInt("id");
		String idea = getPara("idea");
		int checkid = getSessionAttr("userId");
		//System.out.println();
		int flag = Db.update("update room_schedule rs set rs.room_sh_status = '2', rs.room_idea = ? , rs.check_userid = ? where id = ?", idea, checkid, id);
		if(flag == 0 || flag < 0){ 
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);

	}

	
	public void goRefuse(){
		int id = getParaToInt();
		setAttr("roonSchedule", RoomSchedule.dao.findById(id));
		render("roomSchedule_refuse.jsp");
	}
	
	
}
