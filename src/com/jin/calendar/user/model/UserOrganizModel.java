package com.jin.calendar.user.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * UserOrganizModel<br>
 * id	id<br>
 * user_organiz_name	机构名称<br>
 * user_organiz_type	预留字段<br>
 * cdate	创建时间<br>
 * cuser_id	创建人id<br>
 * cuser_name	创建人姓名<br>
 * @author qiaowenbin
 */
@SuppressWarnings("serial")
public class UserOrganizModel extends Model<UserOrganizModel>{
	
	public static final UserOrganizModel dao = new UserOrganizModel();
	
	/**
	 * find all
	 * @return
	 */
	public List<UserOrganizModel> findAll(){
		return findAll(null);
	}
	
	/**
	 * find all by order
	 * @param order
	 * @return
	 */
	public List<UserOrganizModel> findAll(String order){
		StringBuilder sb = new StringBuilder("select * from t_user_organiz ");
		if(StringUtils.isEmpty(order)){
			return dao.find(sb.append("order by id desc").toString());
		}else{
			return dao.find(sb.append(order).toString());
		}
	}
}
