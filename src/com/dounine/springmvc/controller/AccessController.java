package com.dounine.springmvc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dounine.springmvc.entity.Msg;
import com.dounine.springmvc.service.MsgService;
import com.dounine.springmvc.util.Email;

@Controller
public class AccessController{
	private static final Logger logger = LoggerFactory.getLogger(AccessController.class);
	
	@Resource
	MsgService studentService;
	@Resource
	Email sendMail;
	
	@RequestMapping("/index.html")
	public String loginasdf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");
		logger.info("进入首页");
		
		return "welcome";
	}
	
	@RequestMapping("/send")
	@Transactional
	public ModelAndView sendMail(Msg msg,String code,HttpSession session){
		ModelAndView modelAndView = new ModelAndView("welcome");
		modelAndView.addObject("sendto", msg.getSendto());
		modelAndView.addObject("content", msg.getContent());
		
		if(code.equals(session.getAttribute("code").toString().toLowerCase())){
			if(msg.getContent().trim().length()==0||msg.getSendto().trim().length()==0){
				modelAndView.addObject("info", "邮箱或者内容不能为空");
			}else{
				studentService.add(msg);
				sendMail.sendMail(msg.getSendto(), msg.getContent());
			}
		}else{
			modelAndView.addObject("info", "您输入的验证码错误");
		}
		return modelAndView;
	}

}
