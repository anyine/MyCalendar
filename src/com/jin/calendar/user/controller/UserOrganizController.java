package com.jin.calendar.user.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jin.calendar.bo.DwzResponseBO;
import com.jin.calendar.common.CommonConstant;
import com.jin.calendar.user.model.UserEmployerModel;
import com.jin.calendar.user.model.UserOrganizEmployerModel;
import com.jin.calendar.user.model.UserOrganizModel;



/**
 * UserOrganizController
 */
public class UserOrganizController extends Controller{
	
	/**
	 * 跳转到首页 
	 */
	public void index(){
		
		Page<UserOrganizModel> page = UserOrganizModel.dao.paginate(CommonConstant.PAGENUMBER, CommonConstant.PAGESIZE, "select o.*,em.user_employer_name", "from t_user_organiz o join t_user_organiz_employer e on o.id=e.user_organiz_id join t_user_employer em on e.user_employer_id=em.id ");
		
		setAttr("page", page);
		
		render("organiz/organiz.jsp");
	}
	public void getPageList(){
		
		Page<UserOrganizModel> page = UserOrganizModel.dao.paginate(getParaToInt("pageNum"), getParaToInt("numPerPage"), "select o.*,em.user_employer_name", "from t_user_organiz o join t_user_organiz_employer e on o.id=e.user_organiz_id join t_user_employer em on e.user_employer_id=em.id ");
		
		setAttr("page", page);
		
		render("organiz/organiz.jsp");
	}
	public void isUnique(){
		
		List<UserOrganizModel> list = UserOrganizModel.dao.find("select * from t_user_organiz_employer where user_organiz_name= ? and user_employer_id=?", getPara("name"), getPara("employerid"));
		
		if(list.size()>0){
			
			renderText("false");
		
		}else{
			
			renderText("true");
		}
	}
	public void goAdd(){
		setAttr("list", UserEmployerModel.dao.findAll());
		
		setAttr("ServerTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		render("organiz/organiz_add.jsp");
	}
	public void add(){
		DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"),"closeCurrent");
		//事务 处理
		boolean flag = Db.tx(new IAtom(){
			@Override
			public boolean run() throws SQLException {
				UserOrganizModel or = new UserOrganizModel();
				boolean flag1  = or
						.set("user_organiz_name", getPara("name"))
						.set("cdate", getPara("severtime"))
						.set("cuser_id", getParaToInt("userid"))
						.set("cuser_name", getPara("username")).save();
				boolean flag2 = new UserOrganizEmployerModel()
						.set("user_organiz_id", or.getInt("id"))
						.set("user_organiz_name", or.getStr("user_organiz_name"))
						.set("user_employer_id", getParaToInt("employerid"))
						.set("cdate", getPara("severtime"))
						.set("cuser_id", getParaToInt("userid"))
						.set("cuser_name", getPara("username")).save();
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
		//删除机构和组织关系
		boolean flag = Db.tx(new IAtom(){
			@Override
			public boolean run() throws SQLException {
				boolean flag1 = UserOrganizModel.dao.deleteById(getParaToInt());
				int flag2 = Db.update("delete from  t_user_organiz_employer where user_organiz_id=?",getParaToInt());
				return flag1 && flag2 >0; 
			}});
		
		if(!flag){
			responseBO.setStatusCode("300");
			responseBO.setMessage("操作失败！");
		}
		renderJson(responseBO);
	}
	
}
