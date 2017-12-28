package com.jin.calendar.controller;

import java.util.Date;
import java.util.Objects;

import com.jin.calendar.util.MD5Util;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;
import com.jin.calendar.model.Room;
import com.jin.calendar.model.User;

@ClearInterceptor(ClearLayer.ALL)
public class CommonController extends Controller {

	public void index() {
        setAttr("ServerTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		if(getSession().getAttribute("username")==null || getSession().getAttribute("pageType")==null){
			render("login.jsp");
		}else if(getSession().getAttribute("pageType").equals("bookRoom")){
			setAttr("roomList", Room.dao.getAllRoom());
			//forwardAction("/admin/index");
			render("bookRoom.jsp");
		}else if(getSession().getAttribute("pageType").equals("manager")){
			//setAttr("menuList", Menu.dao.getForenoonFoods());
			setAttr("roomList", Room.dao.getAllRoom());
			forwardAction("/admin/index");
		}else{
			renderNull();
		}
	}
	public void bookRoom(){
		setAttr("roomList", Room.dao.getAllRoom());
		setAttr("ServerTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		render("bookRoom.jsp");
	}
	
	public void login(){
		User user=User.dao.getUserByLoginName(getPara("username"));
		if(user==null || ! user.getStr("password").equals(Objects.requireNonNull(MD5Util.MD5(getPara("password"))).toUpperCase())){
			setAttr("msg", "用户名或密码错误");
			render("login.jsp");
			return;
		}else{
			getSession().setAttribute("userId", user.getInt("id"));
			getSession().setAttribute("username", user.getStr("name"));
			getSession().setAttribute("loginname", user.getStr("loginName"));
			getSession().setAttribute("pageType", getPara("pageType"));
			getSession().setAttribute("user", user);
			redirect("/");
		}
	}
	
	public void exit(){
		getSession().invalidate();
		render("login.jsp");
	}
	
}
