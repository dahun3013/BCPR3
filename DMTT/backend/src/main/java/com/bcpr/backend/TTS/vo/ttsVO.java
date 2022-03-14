package com.bcpr.backend.TTS.vo;

public class ttsVO {
	private String id;
	private String password;
	private String profile;
	

	public ttsVO() {
	}


	public ttsVO(String id, String password, String profile) {
		super();
		this.id = id;
		this.password = password;
		this.profile = profile;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getProfile() {
		return profile;
	}


	public void setProfile(String profile) {
		this.profile = profile;
	}


}
