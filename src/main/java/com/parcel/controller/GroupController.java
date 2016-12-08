package com.parcel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcel.entity.Invitation;
import com.parcel.entity.User_group;
import com.parcel.service.GroupService;

@Controller
public class GroupController {
	
	public static final int WRONG_PW = 0;
	public static final int ERROR = 1;
	public static final int SUCCESS = 2;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/group/groupInfo")
	public String getGroupInfo(HttpSession session, Model model) {
		
		List<User_group> list = groupService.getGroupList((int)session.getAttribute("idx"));
		model.addAttribute("groupList", list);
		//몇몇 파라미터 받아서 페이지를 그릴 수 있어야 되는데 일단은 연결만
		return "/groupManager/groupInfo";
	}

	@RequestMapping(value="/group/makeGroupCode", method=RequestMethod.POST)
	@ResponseBody
	public String makeGroupCode() {
		
		String code = groupService.makeUniquenessOfGroupCode();
		System.out.println(code);
		if (code == null) {
			return "실패";
		} else {
			return code;
		}
		//코드를 만든다음 user_group 테이블에 있는지 확인하고 없으면 보내주고 있으면 다시 만들고만들고...하다가 보내준다
		
	}
	@RequestMapping(value="/group/addGroup", method=RequestMethod.POST)
	public String addGroupAndValidate(HttpSession session, @RequestBody @Valid User_group group, BindingResult result) {
		//그룹을 추가하고 (코드 중복으로 인해 실패할 수 있음 -->재수없게 동시에 두 코드가 중복되서 누군가 먼저 넣은경우)
		group.setManager((int)session.getAttribute("idx"));
		
		if(groupService.addGroup(group)) {
			session.setAttribute("sendUserGroup", groupService.getGroupInfoForProductInfo(group.getProduct()));
			return "forward:/group/sendGroup";
		} else {
			return "forward:/util/false";
		}
		
	}
	
	/*
	 * session에 user_group을 넣어서 보내야만 반환해줌
	 */
	@RequestMapping(value="/group/sendGroup", method=RequestMethod.POST)
	public @ResponseBody User_group sendGroup(HttpSession session) {
		User_group temp = (User_group)session.getAttribute("sendUserGroup");
		session.removeAttribute("sendUserGroup");
		return temp;
	}
	
	@RequestMapping(value="/group/deleteGroup", method=RequestMethod.POST)
	@ResponseBody
	public int deleteGroup(@RequestBody User_group group, HttpSession session) {
		return groupService.deleteGroup(group);
		
	}
	
	@RequestMapping("/group/joinGroup")
	public String getJoinGroupPage(@RequestParam(name="msg", required=false) String msg, Model model) {
		
		if (msg != null) {
			model.addAttribute("msg", msg);
		}
		
		return "/groupManager/groupJoinPage";
	}
	
	@RequestMapping(value="/group/joinGroup", method=RequestMethod.POST)
	public String joinGroupAndValidate(HttpSession session, RedirectAttributes model, @Valid User_group group, BindingResult result) {
		if (groupService.joinGroup(group.getCode(), group.getPw(), (int)session.getAttribute("idx"))) {
			return "redirect:/parcel/main";
		} else {
			model.addAttribute("msg", "check your input");
			return "redirect:";
		}
	}
	
	@RequestMapping(value="/group/drop/{gidx}")
	public String dropGroup(@PathVariable int gidx, HttpSession session) {
		boolean r = groupService.dropGroup(gidx, (int)session.getAttribute("idx"));
		
		return "redirect:/group/groupInfo";
	}
	
	@RequestMapping(value="/group/invite",  method=RequestMethod.GET)
	public String showInvitePage(HttpSession session, Model model) {
		//1. 현재 내 그룹 리스트
		model.addAttribute("groupList", groupService.getGroupListByManager((int)session.getAttribute("idx")));
		//2. 현재 내가 초대중인 리스트 --> invite 테이블도 필요
		model.addAttribute("inviteList",groupService.getInviteListByUserIdxForSender((int)session.getAttribute("idx")));
		return "/groupManager/groupInvitationPage";
	}
	
	
	@RequestMapping(value="/group/invite", method=RequestMethod.POST)
	public @ResponseBody String inviteAndValidate(@RequestBody Invitation invitation ) {
		try {
			if (invitation.getType().equals("id")) {
				if (groupService.inviteUserById(invitation)) {
					return "success";
				} else {
					return "false";
				}
			} else if (invitation.getType().equals("email")){
				if (groupService.inviteUserByEmail(invitation)) {
					return "success";
				} else {
					return "false";
				}
			} else {
				return "false";
			}
		} catch (Exception e) {
			return "false";
		}
	}
	
	
	@RequestMapping(value="/group/invitedList",  method=RequestMethod.GET)
	public String showInvitedList(HttpSession session, Model model) {
		
		//초대받은 리스트 보기 state=1인것만 봐야한다.
		model.addAttribute("inviteList",groupService.getInviteListByUserIdxForReceiver((int)session.getAttribute("idx")));
		return "/groupManager/groupInvitedListPage";
	}
	
	
	
	//취소
	@RequestMapping(value="/group/cancle", method=RequestMethod.POST)
	public String cancleInvitation(Invitation invitation, HttpSession session, Model model) {
		int ownerIdx = (int)session.getAttribute("idx");
		int deleteInvitationIdx = invitation.getIdx();
		//주인이 post요청으로 지우는건지 확인해아한다.
		try {
			if (groupService.cancleInvitation(ownerIdx, deleteInvitationIdx)) {
				model.addAttribute("result", true);
			} else {
				model.addAttribute("result", false);
			}			
		} catch (Exception e) {
			model.addAttribute("result", false);
		}
		return "redirect:/group/invite";
	}
	//수락 및 거절
	@RequestMapping(value="/group/permit", method=RequestMethod.POST)
	public String permitInvitation(Invitation invitation, HttpSession session, Model model) {
		System.out.println(invitation.isAcceptanceValue());
		System.out.println(invitation.getIdx());
		System.out.println(invitation.getGroup_idx());
		
		
		invitation.setReceiver((int)session.getAttribute("idx"));
		if (groupService.permitInvitation(invitation)) {
			model.addAttribute("result", true);
		} else {
			model.addAttribute("result", false);
		}
		
		return "redirect:/group/invitedList";
	}
	
	//모바일 그룹생성 페이지
	@RequestMapping("/group/groupMakePage")
	public String getGroupMakePage() {
		
		return "/group/group_add_dialog_m";
	}
}
