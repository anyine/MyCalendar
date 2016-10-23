/**
 * 
 */
package com.jin.calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

/**
 * 
 * @author zhai
 *
 * 2016-10-17 下午5:17:29
 */
public class URLHandler extends Handler {

	/* (non-Javadoc)
	 * @see com.jfinal.handler.Handler#handle(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, boolean[])
	 */
	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		// TODO Auto-generated method stub
	    int index = target.indexOf(";jsessionid");
	    if (index != -1) 
	       target = target.substring(0, index);
	    nextHandler.handle(target, request, response, isHandled);
	  }
	

}
