package com.hstech.master.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hstech.monkey.JsonResponseMsg;

@Controller
@RequestMapping("user")
public class UserController {

	@RequestMapping("login")
	@ResponseBody
	public JsonResponseMsg login() {
		JsonResponseMsg result = new JsonResponseMsg();
		System.out.println("asdfasfdasf");
		return result;
	}
}
