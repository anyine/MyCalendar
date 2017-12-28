package com.jin.calendar.user.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jin.calendar.bo.DwzResponseBO;
import com.jin.calendar.user.model.UserMenuModel;
import com.jin.calendar.util.QTree;
/**
 * UserMenuController
 */
public class UserMenuController extends Controller{
	
	public void index(){
		
		QTree page =    new QTree(0, "/", "根菜单", null, null);
		 
		
		//Page<UserMenuModel> page = UserMenuModel.dao.paginate(CommonConstant.PAGENUMBER, CommonConstant.PAGESIZE, "select *", "from t_user_menu ");
		 
		setAttr("page", page);
	
		render("menu/usermenu.jsp");
	}
	public void getPageList(){
		
		Page<UserMenuModel> page = UserMenuModel.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"), "select *", "from t_user_menu ");
		
		setAttr("page", page);
		
		render("menu/usermenu.jsp");
	}
	public void isUnique(){
		List<UserMenuModel> list = UserMenuModel.dao.find("select * from t_user_menu where user_menu_url= ?", getPara("name"));
		if(list.size()>0){
			renderText("false");
		}else{
			renderText("true");
		}
	}
	
	public void goAdd(){

		render("menu/usermenu_add.jsp");
		
	}
	public void add(){
		
		DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"),"closeCurrent");
		boolean flag  = new UserMenuModel()
		            .set("user_menu_url", getPara("name"))
		            .set("user_menu_title", getPara("title"))
		      /*      .set("user_menu_sn", getPara("sn"))
		            .set("user_menu_type", getPara("type"))*/
		            .set("user_menu_parent_id", getParaToInt("parentid"))
					.set("cdate", new Date())
					.set("cuser_id", getPara("userid"))
					.set("cuser_name", getPara("username")).save();
		
		if(!flag){
			
			responseBO.setStatusCode("300");
			
			responseBO.setMessage("操作失败！");
			
		}
		renderJson(responseBO);
	}
	public void del(){
		DwzResponseBO responseBO = new DwzResponseBO();
		
		//删除菜单和角色关系
		boolean flag = Db.tx(new IAtom(){
      
			@Override
			public boolean run() throws SQLException {

				String ids = getPara();
				
				for(String sid:ids.split(",")){
					
				    int id  = Integer.parseInt(sid);
				    
					boolean flag1 = UserMenuModel.dao.deleteById(id);
					
					int flag2 = Db.update("delete from  t_user_role_menu where user_menu_id=?",id);
					     
					if(!flag1 || flag2<0){return false;}
					
				}
				return true; 
			}
			
		});
		
		if(!flag){
			
			responseBO.setStatusCode("300");
			
			responseBO.setMessage("操作失败！");
		}
	   
		renderJson(responseBO);
	}

	
	
	public void toIst(){
		
		    List<UserMenuModel> list = UserMenuModel.dao.findAll();  
	        StringBuffer sb = new StringBuffer();  
	        List<UserMenuModel> folderList = new ArrayList<UserMenuModel>();  
	        List<UserMenuModel> menuList = new ArrayList<UserMenuModel>();  
	        for(UserMenuModel r : list){  
	            if(r.getInt("user_menu_parent_id")==0){  
	                folderList.add(r);  
	            }else{  
	                menuList.add(r);  
	            }  
	        }  
	        for(UserMenuModel res : folderList){  
	            sb.append("<div class=\"accordionHeader\"><h2><span>Folder</span>" + res.getStr("user_menu_title") + "</h2></div>");  
	            sb.append("<div class=\"accordionContent\"><ul class=\"tree treeFolder\">");  
	            for(UserMenuModel menu : menuList){  
	                if(menu.getInt("user_menu_parent_id") == res.getInt("id")){  
	                    sb.append("<li><a>" + menu.getStr("user_menu_title") +":"+menu.getStr("user_menu_url ")+ "</a></li>");  
	                }  
	            }  
	            sb.append("</ul></div>");  
	        }  
	        setAttr("menuHtml", sb.toString());
	        setAttr("menuList", list);
	        System.out.println(sb.toString());
	}
}
