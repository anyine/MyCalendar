package com.jin.calendar.user.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.jfinal.plugin.activerecord.Model;

/**
 * UserRoleMenuModel<br>
 * id	id<br>
 * user_role_id	角色id<br>
 * user_menu_id	菜单id<br>
 * user_menu_url	菜单url<br>
 * cdate	创建时间<br>
 * cuser_id	创建人id<br>
 * cuser_name	创建人姓名<br>
 * @author qiaowenbin
 */
@SuppressWarnings("serial")
public class UserRoleMenuModel extends Model<UserRoleMenuModel>{
	
	public static final UserRoleMenuModel dao = new UserRoleMenuModel();
	
	/**
	 * find all
	 * @return
	 */
	public List<UserRoleMenuModel> findAll(){
		return findAll(null);
	}
	
	/**
	 * find all by order
	 * @param order
	 * @return
	 */
	public List<UserRoleMenuModel> findAll(String order){
		StringBuilder sb = new StringBuilder("select * from t_user_role_menu ");
		if(StringUtils.isEmpty(order)){
			return dao.find(sb.append("order by id desc").toString());
		}else{
			return dao.find(sb.append(order).toString());
		}
	}
	
	
}
