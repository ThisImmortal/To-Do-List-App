package com.todolist.webbapp.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.todolist.webbapp.entity.Plan;
import com.todolist.webbapp.entity.User;
import com.todolist.webbapp.service.PlanService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private PlanService planService;

	@GetMapping("/getPlansById")
	public String getPlansById(@SessionAttribute("user") User theUser, HttpServletRequest request) {

		long id = theUser.getId();
		List<Plan> plans = planService.getPlanList(id);

		String planlistSource = "actualPlanlist";
		request.setAttribute("planlistSource", planlistSource);
		request.setAttribute("planList", plans);

		return "home-page";
	}

	@GetMapping("/showNewPlanForm")
	public String showNewPlanForm(Model theModel) {

		Plan newPlan = new Plan();
		theModel.addAttribute("newPlan", newPlan);

		return "new-plan-form";
	}

	@PostMapping("/saveNewPlan")
	public String savePlan(@ModelAttribute("newPlan") Plan thePlan, @SessionAttribute("user") User theUser) {

		long id = theUser.getId();
		planService.savePlan(thePlan, id);

		return "redirect:/user/getPlansById";
	}



	@GetMapping("showFormForUpdate")
	public String showUpdateForm(@RequestParam("planId") long theId, Model theModel) {

		Plan thePlan = planService.getPlanById(theId);

		theModel.addAttribute("newPlan", thePlan);

		return "update-plan-form";
	}

	@PostMapping("updatePlan")
	public String updatePlan(@ModelAttribute("newPlan") Plan thePlan) {

		planService.updatePlan(thePlan);

		return "redirect:/user/getPlansById";

	}

	@GetMapping("delete")
	public String deletePlan(@RequestParam("planId") long theId) {

		planService.deletePlan(theId);

		return "redirect:/user/getPlansById";
	}

	@GetMapping("showDeletedPlans")
	public String showDeletedPlans(@SessionAttribute("user") User theUser, HttpServletRequest request) {

		long id = theUser.getId();

		List<Plan> deletedPlans = planService.getDeletedPlansById(id);

		request.setAttribute("planList", deletedPlans);
		
		String deletedPlanlistSource = "deletedPlanlist";
		request.setAttribute("planListSource", deletedPlanlistSource);

		return "home-page";
	}
	
	@GetMapping("restore")
	public String restoreDeletedPlan(@RequestParam("planId") long theId) {
		
		planService.restorePlan(theId);
		
		return "home-page";
		
	}

}
