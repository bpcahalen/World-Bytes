package com.techelevator.dao;

import com.techelevator.model.MealPlan;

import java.security.Principal;
import java.util.List;

public interface MealPlanDao {

    int findIdByUsername(String username);

    List<MealPlan> getAllMyMealPlans(Principal principal);
}
