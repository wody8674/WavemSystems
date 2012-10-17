package com.wavem.convergence.common.vo;

import java.io.Serializable;

public class CommonVo implements Serializable {

	private static final long serialVersionUID = -6392288294270607226L;

	private String mn_menu_id = "";
	private String mn_menu_nm = "";
	private String sb_menu_id = "";
	private String sb_menu_nm = "";
	private String url = "";
	private String user_check = "";

	public String getMn_menu_id() {
		return mn_menu_id;
	}

	public void setMn_menu_id(String mn_menu_id) {
		this.mn_menu_id = mn_menu_id;
	}

	public String getMn_menu_nm() {
		return mn_menu_nm;
	}

	public void setMn_menu_nm(String mn_menu_nm) {
		this.mn_menu_nm = mn_menu_nm;
	}

	public String getSb_menu_id() {
		return sb_menu_id;
	}

	public void setSb_menu_id(String sb_menu_id) {
		this.sb_menu_id = sb_menu_id;
	}

	public String getSb_menu_nm() {
		return sb_menu_nm;
	}

	public void setSb_menu_nm(String sb_menu_nm) {
		this.sb_menu_nm = sb_menu_nm;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUser_check() {
		return user_check;
	}

	public void setUser_check(String user_check) {
		this.user_check = user_check;
	}
}
