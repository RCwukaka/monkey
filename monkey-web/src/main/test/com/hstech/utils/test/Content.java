package com.hstech.utils.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Content")
public class Content {
	@XStreamAlias("Id")
	public String id;

	@XStreamAlias("Name")
	public String name;

	@XStreamAlias("Value")
	public String value;

	public Content(){
		super();
	}
	
	public Content(String id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}
}
