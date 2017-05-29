package com.hstech.utils.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("User")
public class User {
	@XStreamAlias("Id")
	public String id;

	@XStreamAlias("DisplayName")
	public String displayName;

	public User() {
		super();
	}

	public User(String id, String displayName) {
		super();
		this.id = id;
		this.displayName = displayName;
	}
}