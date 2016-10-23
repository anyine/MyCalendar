package com.jin.calendar.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jin.calendar.bo.DwzResponseBO;
import com.jin.calendar.common.CommonConstant;
import com.jin.calendar.model.LeaderInfo;
import com.jin.calendar.model.LeaderSchedule;
/**
 * 领导信息控制器
 * 
 * @author zhai
 *
 * 2016-10-17 上午11:55:04
 */
public class LeaderInfoController extends Controller {
	
	public void index(){
		Page<LeaderInfo> page = LeaderInfo.dao.paginate(CommonConstant.PAGENUMBER, CommonConstant.PAGESIZE, "select *", "from leader_info where isdelete = '0'");
		setAttr("page", page);
		render("leader.jsp");
	}

	public void getPageList(){
		//String sqlStr = "";
		Page<LeaderInfo> page = LeaderInfo.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"), "select *", "from leader_info where isdelete = '0'");
		setAttr("page", page);
		render("leader.jsp");
	}
	
	public void goAdd(){
		render("leader_add.jsp");
	}
	public void goUpd(){
		LeaderInfo leader = LeaderInfo.dao.findById(getParaToInt());
		setAttr("leader", leader);
		render("leader_upd.jsp");
	}
	
	public void add(){

		DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"),"closeCurrent");
		Record leader = new Record().set("leader_name", getPara("name")).set("leader_position", getPara("position")).set("leader_age", getPara("age")).set("leader_sex", getPara("sex"));
		boolean flag = Db.save("leader_info", "leaderid", leader);
		if(!flag){
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);
						
	}
	
	public void upd(){
		DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"),"closeCurrent");
		boolean flag = LeaderInfo.dao.findById(getParaToInt("id")).set("leader_name", getPara("name")).set("leader_position", getPara("position")).set("leader_age", getPara("age")).set("leader_sex", getPara("sex")).update();
		if(!flag){
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);
	}
	
	public void del(){
		DwzResponseBO responseBO = new DwzResponseBO();
		boolean flag = LeaderInfo.dao.findById(getParaToInt()).set( "isdelete", "1").update();
		if(!flag){
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);
	}
	
	public void isUnique(){
		List<Record> list = Db.find("select * from leader_info where isdelete = '0' and leader_name= ?", getPara("name"));
		if(list.size()>0){
			renderText("false");
		}else{
			renderText("true");
		}
	}
	
	/****************************************************************************************************************************
	 * 
	 * 处理领导事记的业务
	 * 
	 * ***************************************************************************************************************************/
	public void indexSch(){
		
		Page<LeaderSchedule> page = LeaderSchedule.dao.getEventBysDate(null, null, null, null, CommonConstant.PAGESIZE, CommonConstant.PAGENUMBER);
		
		setAttr("page", page);
		
		render("leader_need.jsp");
	}
	
	public void getPageListSch(){

		Page<LeaderSchedule> page = LeaderSchedule.dao.getEventBysDate(getPara("sdate"), getPara("edate"), getPara("type"), getPara("name"), getParaToInt("numPerPage"), getParaToInt("pageNum"));
		
		setAttr("page", page);
		
		render("leader_need.jsp");
	}
	public void goLeaderScheduleAdd(){
		setAttr("leaderInfo_list", LeaderInfo.dao.getAllLeaderInfo());
		setAttr("ServerTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		render("leaderSchedule_add.jsp");
	}
	public void goLeaderScheduleUpd(){
		setAttr("leaderInfo_list", LeaderInfo.dao.getAllLeaderInfo());
		//LeaderSchedule Leader = LeaderSchedule.dao.findById(getParaToInt());
		setAttr("LeaderSchedule", LeaderSchedule.dao.findById(getParaToInt()));
		setAttr("ServerTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		render("leaderSchedule_upd.jsp");
	}
	public void addLeaderSchedule(){
		DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"),"closeCurrent");
		Model<LeaderSchedule> mo= getModel(LeaderSchedule.class, "oop");
		boolean f =  LeaderSchedule.dao.isLegalEvent2(mo.getTimestamp("start"), mo.getTimestamp("end"), mo.getInt("leaderid"), mo.getInt("id"));
		if(f){
			boolean flag = mo.save();
			if(!flag){
				responseBO.setStatusCode("300");
				responseBO.setMessage("操作失败！");
			}
		}else{
			responseBO.setStatusCode("301");
			responseBO.setMessage("领导时间冲突");
		}
		renderJson(responseBO);
	}
	public void delLeaderSchedule(){
		DwzResponseBO responseBO = new DwzResponseBO();
		boolean flag = LeaderSchedule.dao.findById(getParaToInt()).set( "isdelete", "1").update();
		if(!flag){
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);
		
	}
	public void updLeaderSchedule(){
		DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"),"closeCurrent");
		Model<LeaderSchedule> mo= getModel(LeaderSchedule.class, "oop");
		boolean f =  LeaderSchedule.dao.isLegalEvent2(mo.getTimestamp("start"), mo.getTimestamp("end"), mo.getInt("leaderid"), mo.getInt("id"));
		if(f){
			boolean flag = mo.update();
			if(!flag){
				responseBO.setStatusCode("300");
				responseBO.setMessage("操作失败！");
			}
		}else{
			responseBO.setStatusCode("301");
			responseBO.setMessage("领导时间冲突");
		}
		renderJson(responseBO);
	}

}
