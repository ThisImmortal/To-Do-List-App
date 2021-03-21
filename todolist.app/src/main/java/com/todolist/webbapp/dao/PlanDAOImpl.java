package com.todolist.webbapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todolist.webbapp.entity.Plan;
import com.todolist.webbapp.entity.User;

@Repository
public class PlanDAOImpl implements PlanDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Plan> getPlanList(long userId) {

		Session currentSession = sessionFactory.getCurrentSession();

	
		User theUser = currentSession.get(User.class, userId);

		List<Plan> allPlans = theUser.getPlans();
		List<Plan> activePlans = new ArrayList<Plan>();
		
		for(Plan thePlan : allPlans) {
			
			
			if(thePlan.getAvailable() == 1) {
				
				activePlans.add(thePlan);
			}
			
		
		}
		

		return activePlans;
	}

	@Override
	public void savePlan(Plan thePlan, long id) {

		Session session = sessionFactory.getCurrentSession();

		User theUser = session.get(User.class, id);
		

		theUser.addPlans(thePlan);
		session.saveOrUpdate(thePlan);

	}



	

	@Override
	public Plan getPlanById(long theId) {

		Session session = sessionFactory.getCurrentSession();

		Plan thePlan = session.get(Plan.class, theId);

		return thePlan;
	}

	@Override
	public void updatePlan(Plan thePlan) {
		
		Session session = sessionFactory.getCurrentSession();
		
		
		session.saveOrUpdate(thePlan);
		
	}

	@Override
	public void deletePlan(long theId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query theQuery = session.createQuery("update Plan set available=:passive where id=:planId");
		
		theQuery.setParameter("passive", 0);
		
		theQuery.setParameter("planId", theId);
		
		theQuery.executeUpdate();
	}

	@Override
	public List<Plan> getDeletedPlansById(long id) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		
		User theUser = currentSession.get(User.class, id);

		List<Plan> allPlans = theUser.getPlans();
		List<Plan> deletedPlans = new ArrayList<Plan>();
		
		for(Plan thePlan : allPlans) {
			
			if(thePlan.getAvailable() == 0) {
				
				deletedPlans.add(thePlan);
			}
		}
		
		return deletedPlans;
	}

	@Override
	public void restorePlan(long theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
        Query theQuery = currentSession.createQuery("update Plan set available=:passive where id=:planId");
		
		theQuery.setParameter("passive", 1);
		
		theQuery.setParameter("planId", theId);
		
		theQuery.executeUpdate();
		
	}
}
