package com.todolist.webbapp.service;

import java.util.List;

import com.todolist.webbapp.entity.Plan;
import com.todolist.webbapp.entity.User;

public interface PlanService {

	public List<Plan> getPlanList(long userId);

	public void savePlan(Plan thePlan, long id);

	public Plan getPlanById(long theId);

	public void updatePlan(Plan thePlan);

	public void deletePlan(long theId);

	public List<Plan> getDeletedPlansById(long id);

	public void restorePlan(long theId);

}
