package com.leopin.parkfifty.admin.unit.tests;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyAdaptedController {
	public MyAdaptedController() {
		
	}
	@RequestMapping("/myPath1.do")
	public void myHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().write("test");
	}

	@RequestMapping("/myPath2.do")
	public void myHandle(@RequestParam("param1") String p1, @RequestParam("param2") int p2,
			@RequestHeader("header1") long h1, @CookieValue("cookie1") Cookie c1,
			HttpServletResponse response) throws IOException {
		response.getWriter().write("test-" + p1 + "-" + p2 + "-" + h1 + "-" + c1.getValue());
	}
	
	@RequestMapping(value="/company", headers = "Accept=application/json")
	public @ResponseBody String myHandle() throws IOException {
		return "Glenn Pinto";
	}
}
