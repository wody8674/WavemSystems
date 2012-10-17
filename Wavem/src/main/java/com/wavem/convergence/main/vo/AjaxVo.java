package com.wavem.convergence.main.vo;

import java.io.Serializable;

public class AjaxVo implements Serializable {

	private static final long serialVersionUID = 4463792484239905317L;

	/* SELECT */
	private String user_id = "";
	private String assign_cd = "";
	private String user_nm = "";
	private String level_nm = "";
	private String hp = "";
	private String email = "";
	
	/* PARAMETER */
	private String param_user_id = "";
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAssign_cd() {
		return assign_cd;
	}
	public void setAssign_cd(String assign_cd) {
		this.assign_cd = assign_cd;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getLevel_nm() {
		return level_nm;
	}
	public void setLevel_nm(String level_nm) {
		this.level_nm = level_nm;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getParam_user_id() {
		return param_user_id;
	}
	public void setParam_user_id(String param_user_id) {
		this.param_user_id = param_user_id;
	}
}
