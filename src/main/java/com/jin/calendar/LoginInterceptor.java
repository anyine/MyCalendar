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
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(QStringUtil.fromException(e));
		}
		
		return "/exit";
	}
}
