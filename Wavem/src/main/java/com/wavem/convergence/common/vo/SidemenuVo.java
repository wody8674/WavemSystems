package com.wavem.convergence.common.vo;

import java.io.Serializable;

public class SidemenuVo implements Serializable {

	private static final long serialVersionUID = 6729805976025075193L;
	
	private String dep_name = "";
	private String dep_code = "";

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getDep_code() {
		return dep_code;
	}

	public void setDep_code(String dep_code) {
		this.dep_code = dep_code;
	}

}
