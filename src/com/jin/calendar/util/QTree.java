package com.jin.calendar.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 
 * tree
 * @author zhai
 *
 * 2016-10-20 下午4:12:27
 */
public class QTree {
	private int id;
	private String url;
	private String text;
	private boolean checked = false;
	private List<QTree> children;

	public QTree() {
		super();
	}
	public QTree(int id, String url, String text, String tableName,TreeCheck check) {
		super();
		this.id = id;
		this.url = url;
		this.text = text;
		//if(check != null) this.checked = check.isCheck(id);	
		this.children = findChildrenByTableAndCheck(id, tableName, check);
	}
	private List<QTree> findChildrenByTableAndCheck(int id, String tableName,TreeCheck check){
		List<QTree> list = new ArrayList<QTree>();
		
		if(StringUtils.isEmpty(tableName)) tableName = "t_user_menu";
		List<Record> menus = Db.find("select * from " + tableName + " where user_menu_parent_id=? order by user_menu_sn", id);
		for(Record menu : menus){
			list.add(new QTree(menu.getInt("id"), menu.getStr("user_menu_url"), menu.getStr("user_menu_title"), tableName,check));
		}
		
		return list;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<QTree> getChildren() {
		return children;
	}
	public void setChildren(List<QTree> children) {
		this.children = children;
	}
}
