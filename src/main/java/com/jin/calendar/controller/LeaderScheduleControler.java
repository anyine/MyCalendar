package com.jin.calendar.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;


import com.jfinal.core.Controller;
import com.jin.calendar.bo.LeaderEvent;
import com.jin.calendar.common.CommonConstant;
import com.jin.calendar.model.LeaderInfo;
import com.jin.calendar.model.LeaderSchedule;

/**
 * 领导事记控制器
 * 
 * @author zhai
 *
 * 2016-10-17 上午11:55:43
 */
public class LeaderScheduleControler extends Controller{
	
	
		public void index(){
			
			forwardAction("/leaderSchedule/addleaderEvent");
			
		}
	    public void getJsp(){
			setAttr("leaderList", LeaderInfo.dao.getAllLeaderInfo());
			setAttr("ServerTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			render("leadership.jsp");
	    }
		
		public void getDurationEvent() {
			List<LeaderSchedule> list = LeaderSchedule.dao.getDurationEventsByRoomId(
					getParaToInt("roomId"), getParaToLong("start"),
					getParaToLong("end"));
			List<LeaderEvent> events = new ArrayList<LeaderEvent>();
			for (LeaderSchedule leaderSchedule : list) {
				LeaderEvent event = new LeaderEvent(leaderSchedule.getInt("id"),
						leaderSchedule.getStr("subject"),
						leaderSchedule.getTimestamp("start"),
						leaderSchedule.getTimestamp("end"),
						leaderSchedule.getStr("username"),
						leaderSchedule.getStr("leadername"),
						leaderSchedule.getStr("email"),
						leaderSchedule.getStr("sj_type"),
						false);
				if (leaderSchedule.getTimestamp("start").before(new Date())) {
					event.setColor(CommonConstant.COLOR_FOR_PAST_EVENT);
				} else if(leaderSchedule.getInt("userid").equals(getSessionAttr("userId"))){
					event.setColor(CommonConstant.COLOR_FOR_OWN_FUTURE_EVENT);
	       
					event.setEditable(true);
				} else {
					event.setColor(CommonConstant.COLOR_FOR_FUTURE_EVENT);
					
				}
				events.add(event);
			}

			renderJson(events);
		}
	/**
	 * 更新事件的开始结束时间并更改状态
	 */
		public void updateleaderEvent(){
			Map<String, Object> returnMap=new HashMap<String, Object>();
			returnMap.put("isSuccess", true);
			Date start=new Date(getParaToLong("start"));
			Date end=new Date(getParaToLong("end"));
			if(start.before(new Date())){
				returnMap.put("isSuccess", false);
				returnMap.put("msg", "调整后的开始时间已过期!");
			}else if(! LeaderSchedule.dao.isLegalEvent(getParaToLong("start")/1000, getParaToLong("end")/1000,getParaToInt("roomId"),getParaToInt("id"))){
				returnMap.put("isSuccess", false);
				returnMap.put("msg", "与其他会议时间有冲突！");
			} else{
				LeaderSchedule leaderSchedule=LeaderSchedule.dao.findById(getParaToInt("id"));
				leaderSchedule.set("start", start);
				leaderSchedule.set("end", end);
				//leaderSchedule.set("sj_type", 0);
				leaderSchedule.update();
			}
			renderJson(returnMap);
		}

		public void addleaderEvent() {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("isSuccess", true);
			Date start = new Date(getParaToLong("start"));
			Date end = new Date(getParaToLong("end"));
			String title = getPara("title");
			int roomId = getParaToInt("roomId");
			//String type = getPara("sj_type");
			if (start.before(new Date())) {
				returnMap.put("isSuccess", false);
				returnMap.put("msg", "开始时间已过期!");
			} else if(! LeaderSchedule.dao.isLegalEvent(getParaToLong("start")/1000, getParaToLong("end")/1000,roomId,-100)){
				returnMap.put("isSuccess", false);
				returnMap.put("msg", "与其他会议时间有冲突！");
			} else {
				new LeaderSchedule().set("start", start)
						.set("end", end)
						.set("subject", title)
						.set("userid", getSessionAttr("userId"))
						.set("leaderid", roomId)
						.set("create_date", new Date())
						//.set("sj_type", type)
						.save();
			}
			renderJson(returnMap);
		}
	

}
