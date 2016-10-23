package com.jin.calendar.user.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * UserOrganizUserModel<br>
 * id	id<br>
 * user_organiz_id	机构id<br>
 * user_user_id	用户id<br>
 * user_user_name	用户姓名<br>
 * cdate	创建时间<br>
 * cuser_id	创建人id<br>
 * cuser_name	创建人姓名<br>
 * @author qiaowenbin
 */
@SuppressWarnings("serial")
public class UserOrganizUserModel extends Model<UserOrganizUserModel>{
	
	public static final UserOrganizUserModel dao = new UserOrganizUserModel();
	
	/**
	 * find all
	 * @return
	 */
	public List<UserOrganizUserModel> findAll(){
		return findAll(null);
	}
	
	/**
	 * find all by order
	 * @param order
	 * @return
	 */
	public List<UserOrganizUserModel> findAll(String order){
		StringBuilder sb = new StringBuilder("select * from t_user_organiz_user ");
		if(StringUtils.isEmpty(order)){
			return dao.find(sb.append("order by id desc").toString());
		}else{
			return dao.find(sb.append(order).toString());
		}
	}
	
}
