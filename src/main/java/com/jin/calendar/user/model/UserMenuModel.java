package com.jin.calendar.user.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * UserMenuModel<br>
 * id	id<br>
 * user_menu_title	菜单名称<br>
 * user_menu_url	菜单地址<br>
 * user_menu_sn	菜单序号<br>
 * user_menu_type	菜单类型<br>
 * user_menu_parent_id	菜单父id<br>
 * cdate	创建时间<br>
 * cuser_name	创建人姓名<br>
 * cuser_id	创建人id<br>
 * @author qiaowenbin
 */
@SuppressWarnings("serial")
public class UserMenuModel extends Model<UserMenuModel>{
	
	public static final UserMenuModel dao = new UserMenuModel();
	
	/**
	 * find all
	 * @return
	 */
	public List<UserMenuModel> findAll(){
		return findAll(null);
	}
	
	/**
	 * find all by order
	 * @param order
	 * @return
	 */
	public List<UserMenuModel> findAll(String order){
		StringBuilder sb = new StringBuilder("select * from t_user_menu ");
		if(StringUtils.isEmpty(order)){
			return dao.find(sb.append("order by id desc").toString());
		}else{
			return dao.find(sb.append(order).toString());
		}
	}
	
}
