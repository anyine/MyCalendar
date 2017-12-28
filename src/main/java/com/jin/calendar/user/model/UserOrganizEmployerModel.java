package com.jin.calendar.user.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Model;

/**
 * UserOrganizEmployerModel<br>
 * id	id<br>
 * user_organiz_id	机构id<br>
 * user_employer_id	单位id<br>
 * user_employer_name	单位姓名<br>
 * cdate	创建时间<br>
 * cuser_id	创建人id<br>
 * cuser_name	创建人姓名<br>
 * @author qiaowenbin
 */
@SuppressWarnings("serial")
public class UserOrganizEmployerModel extends Model<UserOrganizEmployerModel>{
	
	public static final UserOrganizEmployerModel dao = new UserOrganizEmployerModel();
	
	/**
	 * find all
	 * @return
	 */
	public List<UserOrganizEmployerModel> findAll(){
		return findAll(null);
	}
	
	/**
	 * find all by order
	 * @param order
	 * @return
	 */
	public List<UserOrganizEmployerModel> findAll(String order){
		StringBuilder sb = new StringBuilder("select * from t_user_organiz_employer ");
		if(StringUtils.isEmpty(order)){
			return dao.find(sb.append("order by id desc").toString());
		}else{
			return dao.find(sb.append(order).toString());
		}
	}
	/**
	 * find all by emid
	 * @param 
	 * @return
	 */
	public List<UserOrganizEmployerModel> findAllByEmployId(int emid){
		StringBuilder sb = new StringBuilder("select * from t_user_organiz_employer ");
		if(emid ==0){
			return dao.find(sb.append(" order by id desc").toString());
		}else{
			return dao.find(sb.append(" user_employer_id="+emid).toString());
		}
	}
}
