package ar.com.educationit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value={"/","/home"})
	public String home() {
		return "index";//templates/index.html
	}
}
