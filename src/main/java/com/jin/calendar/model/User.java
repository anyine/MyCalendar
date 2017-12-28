package com.jin.calendar.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
//import com.jin.calendar.user.model.UserRoleModel;

@SuppressWarnings("serial")
public class User extends Model<User> {
	public static final User dao = new User();
	
	public User getUserByLoginName(String loginName){
		return User.dao.findFirst("select * from user where loginName='"+loginName+"'");
	}
	/**
	 * 查询用户信息
	 * 用户 组织机构信息
	 * 用户角色信息
	 * @param pagenum
	 * @param pagesize
	 * @return
	 */
    public Page<User> getAllUserInfo(Integer pagenum, Integer pagesize){
    	String sqlSel = "SELECT u.* ";
		String sqlFrom="FROM user u";
		
		Page<User> page = User.dao.paginate(pagenum, pagesize, sqlSel, sqlFrom);
		
		return page;
    }

}
