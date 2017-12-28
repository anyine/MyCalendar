package com.jin.calendar;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jin.calendar.model.User;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements Interceptor {

	//@Override
	public void intercept(ActionInvocation ai) {
		String user = ai.getController().getSessionAttr("username");
		if(user == null){
			ai.getController().redirect("/");
		}else {
			ai.invoke();
		  /**
		   * 权限控制
		   * 根据用户所属角色的权限
		   * 判断请求url是否可以跳转
		   */
			/*String res = authVisit(ai);
			if(res == null){
				ai.invoke();
			}else{
				ai.getController().redirect(res);
			}*/
			
			
		}
	}

	/**
	 * 是否有权限访问
	 * @param ai
	 * @return
	 */
	public String authVisit(ActionInvocation ai) {
		try {
			User user = ai.getController().getSessionAttr("user");
			if(user != null){
				String urls = user.role().urls();
				String actionKey = ai.getActionKey();
				for(String url : urls.split(",")){
					if(actionKey.equals(url)){
						return null;
					}
				}
				
				return user.role().getStr("user_role_login_url");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(QStringUtil.fromException(e));
		}
		
		return "/exit";
	}
}
