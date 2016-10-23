/**
 * 
 */
package com.jin.calendar.bo;

/**
 * 
 * @author zhai
 *
 * 2016-10-20 下午12:12:21
 */
public class employBo {

	public int id;
	public String name;
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 */
	public employBo() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 */
	public employBo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
