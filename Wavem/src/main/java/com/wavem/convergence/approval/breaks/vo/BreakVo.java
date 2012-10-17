package com.wavem.convergence.approval.breaks.vo;

import java.io.Serializable;

public class BreakVo implements Serializable {
	private static final long serialVersionUID = -8560863020322348416L;
	
	private String bre_code="";
	private String app_code="";
	private String bre_date="";
	private String bre_type="";
	private String bre_takeover="";
	private String bre_start="";
	private String bre_over="";
	private String fir_reg_day = "";
	private String fir_reg = "";
	private String lat_reg_day= "";
	private String lat_reg = "";
	private String bre_name="";
	private String bre_team="";
	private String bre_level="";
	
	
	public String getBre_code() {
		return bre_code;
	}
	public void setBre_code(String bre_code) {
		this.bre_code = bre_code;
	}
	public String getApp_code() {
		return app_code;
	}
	public void setApp_code(String app_code) {
		this.app_code = app_code;
	}
	public String getBre_date() {
		return bre_date;
	}
	public void setBre_date(String bre_date) {
		this.bre_date = bre_date;
	}
	public String getBre_type() {
		return bre_type;
	}
	public void setBre_type(String bre_type) {
		this.bre_type = bre_type;
	}
	public String getBre_takeover() {
		return bre_takeover;
	}
	public void setBre_takeover(String bre_takeover) {
		this.bre_takeover = bre_takeover;
	}
	public String getBre_start() {
		return bre_start;
	}
	public void setBre_start(String bre_start) {
		this.bre_start = bre_start;
	}
	public String getBre_over() {
		return bre_over;
	}
	public void setBre_over(String bre_over) {
		this.bre_over = bre_over;
	}
	public String getFir_reg_day() {
		return fir_reg_day;
	}
	public void setFir_reg_day(String fir_reg_day) {
		this.fir_reg_day = fir_reg_day;
	}
	public String getFir_reg() {
		return fir_reg;
	}
	public void setFir_reg(String fir_reg) {
		this.fir_reg = fir_reg;
	}
	public String getLat_reg_day() {
		return lat_reg_day;
	}
	public void setLat_reg_day(String lat_reg_day) {
		this.lat_reg_day = lat_reg_day;
	}
	public String getLat_reg() {
		return lat_reg;
	}
	public void setLat_reg(String lat_reg) {
		this.lat_reg = lat_reg;
	}
	public String getBre_name() {
		return bre_name;
	}
	public void setBre_name(String bre_name) {
		this.bre_name = bre_name;
	}
	public String getBre_team() {
		return bre_team;
	}
	public void setBre_team(String bre_team) {
		this.bre_team = bre_team;
	}
	public String getBre_level() {
		return bre_level;
	}
	public void setBre_level(String bre_level) {
		this.bre_level = bre_level;
	}
	
}
