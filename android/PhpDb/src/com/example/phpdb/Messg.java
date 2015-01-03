package com.example.phpdb;

public class Messg {
	private String name;
	private String desc;
	private String tm;

	public Messg(String name, String desc ,String tm) {
		this.name = name;
		this.desc = desc;
		this.tm=tm;

	}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
