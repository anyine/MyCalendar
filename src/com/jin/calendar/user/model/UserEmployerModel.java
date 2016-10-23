package com.jin.calendar.user.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * UserEmployerModel<br>
 * id	id<br>
 * user_employer_name	单位名称<br>
 * user_employer_type	预留字段<br>
 * cdate	创建时间<br>
 * cuser_id	创建人id<br>
 * cuser_name	创建人姓名<br>
 * @author qiaowenbin
 */
@SuppressWarnings("serial")
public class UserEmployerModel extends Model<UserEmployerModel>{
	
	public static final UserEmployerModel dao = new UserEmployerModel();
	
	/**
	 * find all
	 * @return
	 */
	public List<UserEmployerModel> findAll(){
		return findAll(null);
	}
	
	/**
	 * find all by order
	 * @param order
	 * @return
	 */
	public List<UserEmployerModel> findAll(String order){
		StringBuilder sb = new StringBuilder("select * from t_user_employer ");
		if(StringUtils.isEmpty(order)){
			return dao.find(sb.append("order by id desc").toString());
		}else{
			return dao.find(sb.append(order).toString());
		}
	}
	
	
}
