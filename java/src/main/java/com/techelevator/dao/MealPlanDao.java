package com.techelevator.dao;

import com.techelevator.model.MealPlan;

import java.security.Principal;
import java.util.List;

public interface MealPlanDao {

    List<MealPlan> getAllMyMealPlans(int userId);
}
