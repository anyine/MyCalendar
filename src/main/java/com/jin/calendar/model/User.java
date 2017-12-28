package com.jin.calendar.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jin.calendar.user.model.UserRoleModel;

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
    	String sqlSel = "SELECT u.*, tuoe.user_organiz_name, tue.user_employer_name, tur.user_role_name ";
		String sqlFrom="FROM user u"
			+" left JOIN t_user_organiz_user tuou ON u.id = tuou.user_user_id "
			+" left JOIN t_user_organiz_employer tuoe ON tuou.user_organiz_id = tuoe.user_organiz_id "
			+" left JOIN t_user_employer tue ON tuoe.user_employer_id = tue.id "
			+" left JOIN t_user_role_user turu ON u.id = turu.user_user_id "
			+" left JOIN t_user_role tur ON turu.user_role_id = tur.id ";
		
		Page<User> page = User.dao.paginate(pagenum, pagesize, sqlSel, sqlFrom);
		
		return page;
    }
	
	/**
	 * 当前用户的角色信息
	 * @return
	 */
	public UserRoleModel role(){
		return UserRoleModel.dao.findFirst("select ur.* from t_user_role ur, t_user_role_user rl where ur.id=rl.user_role_id and rl.user_user_id=?", get("id"));
	}
	
}
