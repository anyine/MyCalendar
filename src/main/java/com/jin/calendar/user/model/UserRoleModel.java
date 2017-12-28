package com.jin.calendar.user.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.jfinal.plugin.activerecord.Model;
/**
 * UserRoleModel<br>
 * id	id<br>
 * user_role_name	角色名称<br>
 * user_role_login_url	角色跳转地址<br>
 * cdate	创建时间<br>
 * cuser_id	创建人id<br>
 * cuser_name	创建人姓名<br>
 * @author qiaowenbin
 */
@SuppressWarnings("serial")
public class UserRoleModel extends Model<UserRoleModel>{
	
	public static final UserRoleModel dao = new UserRoleModel();
	
	/**
	 * find all
	 * @return
	 */
	public List<UserRoleModel> findAll(){
		return findAll(null);
	}
	
	/**
	 * find all by order
	 * @param order
	 * @return
	 */
	public List<UserRoleModel> findAll(String order){
		StringBuilder sb = new StringBuilder("select * from t_user_role ");
		if(StringUtils.isEmpty(order)){
			return dao.find(sb.append("order by id desc").toString());
		}else{
			return dao.find(sb.append(order).toString());
		}
	}
	
	/**
	 * 当前用户对应角色的所有可以访问的url (用，隔开)
	 * @return
	 */
	public String urls(){
		String sql = "select * from t_user_role_menu where user_role_id=? order by user_menu_url";
		List<UserRoleMenuModel> menus = UserRoleMenuModel.dao.find(sql, get("id"));
		
		StringBuilder urls = new StringBuilder();
		for(UserRoleMenuModel rl : menus){
			urls.append("," + rl.getStr("user_menu_url"));
		}

		return urls.toString().replaceFirst(",", "");
	}
	
}
