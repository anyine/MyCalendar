package com.jin.calendar.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jin.calendar.bo.DwzResponseBO;
import com.jin.calendar.common.CommonConstant;
import com.jin.calendar.model.User;
import com.jin.calendar.util.MD5Util;

public class UserController extends Controller {

    public void index() {

        Page<User> page = User.dao.getAllUserInfo(CommonConstant.PAGENUMBER, CommonConstant.PAGESIZE);

        setAttr("page", page);

        render("user.jsp");
    }

    public void getPageList() {

        Page<User> page = User.dao.getAllUserInfo(getParaToInt("pageNum"), getParaToInt("numPerPage"));

        setAttr("page", page);

        render("user.jsp");
    }

    public void goAdd() {

        render("user_add.jsp");
    }

    public void add() {
        DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"), "closeCurrent");
        //事务 处理
        boolean flag = Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {

                User or = new User();

                boolean flag1 = or.set("name", getPara("name"))
                        .set("loginName", getPara("loginName"))
                        .set("password", MD5Util.MD5(getPara("password")))
                        .set("tel", getPara("tel"))
                        .set("email", getPara("email"))
                        .set("create_date", new Date()).save();
                return flag1;

            }
        });

        if (!flag) {

            responseBO.setStatusCode("300");

            responseBO.setMessage("操作失败！");
        }

        renderJson(responseBO);

    }

    public void del() {
        DwzResponseBO responseBO = new DwzResponseBO();

        boolean flag = Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                //-user
                boolean flag1 = Db.deleteById("user", getParaToInt());

                return flag1;
            }
        });

        if (!flag) {

            responseBO.setStatusCode("300");

            responseBO.setMessage("操作失败！");
        }
        renderJson(responseBO);
    }

    /**
     * 校验用户名是否可用
     */
    public void isUnique() {
        List<Record> list = Db.find("select * from user where loginName= ?", getPara("loginName"));
        if (list.size() > 0) {
            renderText("false");
        } else {
            renderText("true");
        }
    }

    /**
     * 校验密码
     */
    public void isPasswd() {

        boolean flag = false;

        User user = User.dao.getUserByLoginName((String) getSession().getAttribute("loginname"));

        if (user != null && user.getStr("password").equals(MD5Util.MD5(getPara("opwd")))) {

            flag = true;
        }

        if (!flag) {

            renderText("false");

        } else {

            renderText("true");

        }
    }

    public void goPwd() {

        render("user_edit_pwd.jsp");
    }

    /**
     * 修改用户密码
     */
    public void editPasswd() {

        DwzResponseBO responseBO = new DwzResponseBO(getPara("navTabId"), "closeCurrent");

        boolean flag = new User().findById(getParaToInt("id")).set("password", MD5Util.MD5(getPara("npwd"))).update();

        if (!flag) {

            responseBO.setStatusCode("300");

            responseBO.setMessage("操作失败！");
        }
        renderJson(responseBO);

    }
}
