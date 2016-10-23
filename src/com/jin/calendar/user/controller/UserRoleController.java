package com.jin.calendar.user.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jin.calendar.bo.DwzResponseBO;
import com.jin.calendar.common.CommonConstant;
import com.jin.calendar.user.model.UserMenuModel;
import com.jin.calendar.user.model.UserRoleMenuModel;
import com.jin.calendar.user.model.UserRoleModel;
import com.jin.calendar.util.QTree;

/**
 * UserRoleController
 */
public class UserRoleController extends Controller{
	
    public void index(){
		
		Page<UserRoleModel> page = UserRoleModel.dao.paginate(CommonConstant.PAGENUMBER, CommonConstant.PAGESIZE, "select *", "from t_user_role ");
		
		setAttr("page", page);
		
		render("role/role.jsp");
	}
	public void getPageList(){
		
		Page<UserRoleModel> page = UserRoleModel.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"), "select *", "from t_user_role ");
		
		setAttr("page", page);
		
		render("role/role.jsp");
	}
	public void isUnique(){
		List<UserRoleModel> list = UserRoleModel.dao.find("select * from t_user_role where user_role_name= ?", getPara("name"));
		if(list.size()>0){
			renderText("false");
		}else{
			renderText("true");
		}
	}
	
	public void goAdd(){
		
        //查询出所有权限
		setAttr("page", new QTree(0, "/", "根菜单", null, null));
		
		render("role/role_add.jsp");
		
	}
	public void add(){
		
		DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"),"closeCurrent");

		//添加角色，并为角色添加权限菜单
		boolean flag = Db.tx(new IAtom(){

			@Override
			public boolean run() throws SQLException {
				UserRoleModel ur = new UserRoleModel();
				boolean flag1  = ur .set("user_role_name", getPara("rolename"))
						           .set("user_role_login_url", getPara("url"))
							       .set("cdate", new Date())
								   .set("cuser_id", getPara("userid"))
							       .set("cuser_name", getPara("username")).save();
				String ids = getPara("ids");
				
				boolean flag2 = false;
				
				if(StringUtils.isNotEmpty(ids)){
					for(String id : ids.split(",")){
						if("0".equals(id)) continue;//结束本次循环 进入下次， break: 结束循环
							flag2 = new UserRoleMenuModel()
						  				.set("user_role_id", ur.getInt("id"))
						  				.set("user_menu_id", id)
						  				.set("user_menu_url", UserMenuModel.dao.findById(id).getStr("user_menu_url"))
						  				.set("cdate", new Date())
										.set("cuser_id", getPara("userid"))
										.set("cuser_name", getPara("username"))
						  				.save();
							if(!flag2){
								return false;
							}
					}
				}
				return flag1 && flag2;
			}});
		
		if(!flag){
			
			responseBO.setStatusCode("300");
			
			responseBO.setMessage("操作失败！");
			
		}
		renderJson(responseBO);
	}
	public void del(){
		DwzResponseBO responseBO = new DwzResponseBO();
		
		//删除角色和角色权限
		boolean flag = Db.tx(new IAtom(){

			@Override
			public boolean run() throws SQLException {

				boolean flag1 = UserRoleModel.dao.deleteById(getParaToInt());
				
				int flag2 = Db.update("delete from  t_user_role_menu where user_role_id=?",getParaToInt());
				
				return flag1 && flag2 > 0; 
			}
			
		});
		
		if(!flag){
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);
	}
	
	
}
