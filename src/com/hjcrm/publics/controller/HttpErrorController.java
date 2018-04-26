package com.hjcrm.publics.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hjcrm.publics.util.BaseController;


@Controller
public class HttpErrorController extends BaseController {

	@RequestMapping(value = "/404.do",method= RequestMethod.GET)
	public String page404(){
		return "page404_2";
	}
	@RequestMapping(value = "/500.do",method= RequestMethod.GET)
	public String page500(){
		return "page500";
	}
}
