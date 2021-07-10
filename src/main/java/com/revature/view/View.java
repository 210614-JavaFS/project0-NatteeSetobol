package com.revature.view;

public class View {
	int viewID;
	private String name;
	private String DisplayName;
	
	public int getViewID() {
		return viewID;
	}
	public void setViewID(int viewID) {
		this.viewID = viewID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return DisplayName;
	}
	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}
	public View(int viewID, String name, String displayName) {
		super();
		this.viewID = viewID;
		this.name = name;
		DisplayName = displayName;
	}
	
	
	
}
