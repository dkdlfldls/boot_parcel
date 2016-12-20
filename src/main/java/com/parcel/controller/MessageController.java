package com.parcel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.parcel.entity.Message;
import com.parcel.service.MessageService;
import com.parcel.util.Page;

/**
 * Message기능 Controller
 * @author user
 */
@Controller
public class MessageController{

	@Autowired
	private MessageService messageService;
	
	/**
	 * 메시지 관리 페이지 요청 처리
	 * @param model
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/message/info")
	public String getMessageInfo(Model model, HttpSession session, Page page) {
		
		//page.setPageInfo();
		int idx = (int)session.getAttribute("idx");
		
		List<Message> list = messageService.getMessageListForPaging(idx, page);
		if (list == null || list.isEmpty()) {
			model.addAttribute("isEmpty", true);
		} else {
			model.addAttribute("isEmpty", false);
		}
		model.addAttribute("messageList", list);
		model.addAttribute("pageInfo", page);
		
		return "/messageManager/MessageInfo";
	}
	
	/**
	 * 메시지 확인 요청 처리
	 * @param message
	 * @return
	 */
	@RequestMapping(value="/message/check", method=RequestMethod.POST)
	@ResponseBody
	public String checkMessage(@RequestBody Message message) {
		System.out.println(message.getIdx() + "@@@@@");
		boolean result = messageService.checkMessage(message.getIdx());
		if (result) {
			return "success";
		} else {
			return "fail";
		}
		
	}
}
