package com.jin.calendar;

//import java.util.Map;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jin.calendar.controller.AdminController;
import com.jin.calendar.controller.CommonController;
import com.jin.calendar.controller.LeaderInfoController;
import com.jin.calendar.controller.LeaderScheduleControler;
import com.jin.calendar.controller.MenuController;
import com.jin.calendar.controller.RoomController;
import com.jin.calendar.controller.RoomScheduleController;
import com.jin.calendar.controller.UserController;
import com.jin.calendar.user.controller.UserMenuController;
import com.jin.calendar.model.LeaderInfo;
import com.jin.calendar.model.LeaderSchedule;
import com.jin.calendar.model.Menu;
import com.jin.calendar.model.Room;
import com.jin.calendar.model.RoomSchedule;
import com.jin.calendar.model.User;
import com.jin.calendar.model.UserMenu;
import com.jin.calendar.user.controller.UserEmployerController;
import com.jin.calendar.user.controller.UserOrganizController;
import com.jin.calendar.user.controller.UserRoleController;
import com.jin.calendar.user.model.UserEmployerModel;
import com.jin.calendar.user.model.UserMenuModel;
import com.jin.calendar.user.model.UserOrganizEmployerModel;
import com.jin.calendar.user.model.UserOrganizModel;
import com.jin.calendar.user.model.UserOrganizUserModel;
import com.jin.calendar.user.model.UserRoleMenuModel;
import com.jin.calendar.user.model.UserRoleModel;
import com.jin.calendar.user.model.UserRoleUserModel;

/**
 * 系统配置类，继承JFinalConfig，用于对整个项目进行配置
 */
public class SysConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {

		me.setDevMode(true);// 配置当前为开发模式
		me.setViewType(ViewType.JSP);// 配置默认视图为JSP
		me.setBaseViewPath("/WEB-INF/page");
		loadPropertyFile("jdbcConfig.properties");// 加载数据库连接配置

	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", CommonController.class);
		me.add("/user", UserController.class, "/admin");
		
		me.add("/menu", MenuController.class, "/admin");
		me.add("/userMenu", UserMenuController.class);
		
		me.add("/room", RoomController.class, "/admin");
		me.add("/roomSchedule", RoomScheduleController.class);
		
		me.add("/admin", AdminController.class);
		
		me.add("/leaderInfo", LeaderInfoController.class, "/admin");
		me.add("/leaderSchedule", LeaderScheduleControler.class);
		
		me.add("/employer", UserEmployerController.class, "/admin");
		me.add("/organiz", UserOrganizController.class, "/admin");
		
		me.add("/role", UserRoleController.class, "/admin");
		me.add("/usermenu", UserMenuController.class, "/admin");
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置Druid数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcURL"),
				getProperty("jdbcUser"), getProperty("jdbcPassword"),
				getProperty("jdbcDriver"));
		me.add(druidPlugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		me.add(arp);
		// 配置方言
		arp.setDialect(new MysqlDialect());
		// arp.setDialect(new OracleDialect());
		// 配置属性名大小写不敏感工厂
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());
		arp.addMapping("menu", Menu.class);
		arp.addMapping("user", User.class);
		arp.addMapping("user_menu", UserMenu.class);
		//会议室
		arp.addMapping("room", Room.class);
		arp.addMapping("room_schedule", RoomSchedule.class);
		//领导事迹
		arp.addMapping("leader_info", "leaderid", LeaderInfo.class);
		arp.addMapping("leader_schedule", LeaderSchedule.class);
		//角色权限
		arp.addMapping("t_user_role", UserRoleModel.class);
		arp.addMapping("t_user_role_menu", UserRoleMenuModel.class);
		arp.addMapping("t_user_role_user", UserRoleUserModel.class);
		arp.addMapping("t_user_menu", UserMenuModel.class);
		//组织机构
		arp.addMapping("t_user_employer", UserEmployerModel.class);
		arp.addMapping("t_user_organiz", UserOrganizModel.class);
		arp.addMapping("t_user_organiz_employer", UserOrganizEmployerModel.class);
		arp.addMapping("t_user_organiz_user", UserOrganizUserModel.class);
	}

	// 配置全局拦截器
	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new LoginInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

	// public static void main(String[] args) {
	// JFinal.start("WebRoot", 80, "/", 5);
	// }
}
