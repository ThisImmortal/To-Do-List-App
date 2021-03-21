package com.todolist.webbapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.webbapp.dao.PlanDAO;
import com.todolist.webbapp.entity.Plan;
import com.todolist.webbapp.entity.User;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanDAO planDAO;

	@Override
	@Transactional
	public List<Plan> getPlanList(long userId) {
		
		return planDAO.getPlanList(userId);
	}

	@Override
	@Transactional
	public void savePlan(Plan thePlan, long id) {
		
		planDAO.savePlan(thePlan, id);
		
	}

	@Override
	@Transactional
	public Plan getPlanById(long theId) {
		
		return planDAO.getPlanById(theId);
	}

	@Override
	@Transactional
	public void updatePlan(Plan thePlan) {
		
		planDAO.updatePlan(thePlan);
		
	}

	@Override
	@Transactional
	public void deletePlan(long theId) {
		
		planDAO.deletePlan(theId);
		
	}

	@Override
	@Transactional
	public List<Plan> getDeletedPlansById(long id) {
		
		return planDAO.getDeletedPlansById(id);
	}

	@Override
	@Transactional
	public void restorePlan(long theId) {
		
		planDAO.restorePlan(theId);
		
	}
}
