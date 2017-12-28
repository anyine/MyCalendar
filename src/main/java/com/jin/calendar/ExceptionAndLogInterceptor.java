package com.jin.calendar;

/*import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;*/

/*import com.foreveross.wx.exception.WeixianBussException;
import com.foreveross.wx.model.ActionLog;
import com.foreveross.wx.model.Menu;
import com.foreveross.wx.vo.ActionLogVo;
import com.foreveross.wx.web.controller.BaseController;*/
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.JFinal;
import com.jfinal.log.Logger;

/**
 * @title: 日志/异常处理拦截器
 * @className: ActionLogInterceptor
 * @description: TODO
 * @company: FOREVEROSS
 * @author: <a href="http://www.smhaochi.com">vakin jiang</a>
 * @createDate: 2014年4月2日
 * @version: 1.0
 */
public class ExceptionAndLogInterceptor implements Interceptor {

  private static final Logger log = Logger.getLogger(ExceptionAndLogInterceptor.class);
  @Override
  public void intercept(ActionInvocation ai) {
   // BaseController controller = (BaseController)ai.getController();
    //HttpServletRequest request = controller.getRequest();
    
    //boolean successed = false;
    try {
      ai.invoke();
     // successed = true;
    } catch (Exception e) {
      //
      doLog(ai,e);
     /* //判断是否ajax请求
      String header = request.getHeader("X-Requested-With");
      boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(header);
      String msg = formatException(e);
      if(isAjax){
        msg = new StringBuilder().append("{\"status\":\"0\",\"msg\":\"")
            .append(msg).append("\"}").toString();
        controller.renderJson(msg);
      }else{
        String redirctUrl = request.getHeader("referer");
        if(StringUtils.isBlank(redirctUrl)){
          redirctUrl = request.getRequestURI();
        }
        controller.setAttr("message", msg);
        controller.setAttr("redirctUrl",redirctUrl);
       //controller.render("../public/failed.ftl");
      }*/
    }finally{
      //记录日志
      try {
       /* Menu menu = matchDefineRecordLogMenu(request);
        if(menu != null){
          ActionLogVo actionLog = controller.wrapActionLog();
          new ActionLog().set(ActionLog.ACT_NAME, menu.getName())//
                   .set(ActionLog.ACT_TIME, new Date())//
                   .set(ActionLog.DATA, actionLog.getData())//
                   .set(ActionLog.IP, actionLog.getIp())//
                   .set(ActionLog.SUCESSED, successed)//
                   .set(ActionLog.URI, actionLog.getUri())//
                   .set(ActionLog.USER_ID, actionLog.getUserId())//
                   .set(ActionLog.USER_NAME, actionLog.getUserName())//
                   .save();
        }*/
      } catch (Exception e2) {}
      
    }
  }
  
  /**
   * @methodName: matchDefineRecordLogMenu
   * @description: 匹配已定义需要记录日志的菜单
   * @author: vakinge
   * @createDate: 2014年4月3日
   * @param request
   * @return 
   */
/*  private Menu matchDefineRecordLogMenu(HttpServletRequest request) {
    Map<String, Menu> menus = Menu.getAllUrlMenus();
    if(menus != null){
      String uri = request.getRequestURI();
      Set<String> urls = menus.keySet();
      for (String url : urls) {
        if(url == null)continue;
        //url匹配 && 有参数 && 已配置记录日志
        if(url.contains(uri) 
            && request.getParameterNames().hasMoreElements()
            && menus.get(url).isRecordLog()){
          return menus.get(url);
        }
      }
    }
    return null;
  }*/

  /**
   * @methodName: doLog
   * @description: 记录log4j日志
   * @author: vakinge
   * @createDate: 2014年4月3日
   * @param ai 
   * @param e 
   */
  private void doLog(ActionInvocation ai,Exception e) {
    //开发模式
    if(JFinal.me().getConstants().getDevMode()){
      e.printStackTrace();
    }
    //业务异常不记录 ？？
    if( e instanceof Exception)return;
    StringBuilder sb =new StringBuilder("\n---Exception Log Begin---\n");
    sb.append("Controller:").append(ai.getController().getClass().getName()).append("\n");
    sb.append("Method:").append(ai.getMethodName()).append("\n");
    sb.append("Exception Type:").append(e.getClass().getName()).append("\n");
    sb.append("Exception Details:");
    log.error(sb.toString(), e);
  }

  /**
   * 格式化异常信息，用于友好响应用户
   * @param e
   * @return
   */
 /* private static String formatException(Exception e){
     String message = null;
      Throwable ourCause = e;
      while ((ourCause = e.getCause()) != null) {
        e = (Exception) ourCause;
      }
      String eClassName = e.getClass().getName();
      //一些常见异常提示
      if("java.lang.NumberFormatException".equals(eClassName)){
        message = "请输入正确的数字";
      }else if (e instanceof BaseBussException || e instanceof RuntimeException) {
        message = e.getMessage();
        if(StringUtils.isBlank(message))message = e.toString();
      }
      
      //获取默认异常提示
      if (StringUtils.isBlank(message)){
        message = "系统繁忙,请稍后再试";
      }
      //替换特殊字符
      message = message.replaceAll("\"", "'");
      return message;
  }*/

}