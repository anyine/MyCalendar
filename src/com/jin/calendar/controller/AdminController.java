package com.jin.calendar.controller;


import com.jfinal.core.Controller;
import com.jin.calendar.model.LeaderSchedule;
import com.jin.calendar.model.RoomSchedule;
import com.jin.calendar.model.UserMenu;

public class AdminController extends Controller {

	public void index(){
		setAttr("orderList", UserMenu.dao.getTodayOrder());
		setAttr("meetingList", RoomSchedule.dao.getTodayEvent());
		setAttr("leaderList", LeaderSchedule.dao.getTodayEvent());
		renderJsp("index.jsp");
	}
	
}
