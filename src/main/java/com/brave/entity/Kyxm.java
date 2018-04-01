package com.brave.entity;

import java.io.Serializable;

public class Kyxm implements Serializable{
	private int k_id;
	private String k_name;
	private String k_category;
	private int k_score;
	private static final long serialVersionUID = 1L;
	
	
	public int getK_id() {
		return k_id;
	}

	public void setK_id(int k_id) {
		this.k_id = k_id;
	}

	public String getK_name() {
		return k_name;
	}

	public void setK_name(String k_name) {
		this.k_name = k_name;
	}

	public String getK_category() {
		return k_category;
	}

	public void setK_category(String k_category) {
		this.k_category = k_category;
	}

	public int getK_score() {
		return k_score;
	}

	public void setK_score(int k_score) {
		this.k_score = k_score;
	}
}
