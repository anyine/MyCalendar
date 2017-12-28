package com.jin.calendar.controller;


import com.jfinal.core.Controller;
import com.jin.calendar.model.RoomSchedule;

public class AdminController extends Controller {

	public void index(){
		setAttr("meetingList", RoomSchedule.dao.getTodayEvent());
		renderJsp("index.jsp");
	}
	
}
