package com.leopin.parkfifty.admin.unit.tests;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AcceptHeadersController {
	@RequestMapping(value = "/something", headers = "accept=text/html")
	public void handleHtml(Writer writer) throws IOException {
		writer.write("html");
	}

	@RequestMapping(value = "/something", headers = "accept=application/xml")
	public void handleXml(Writer writer) throws IOException {
		writer.write("xml");
	}
}
