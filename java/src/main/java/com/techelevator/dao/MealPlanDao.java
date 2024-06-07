package com.techelevator.dao;

import com.techelevator.model.MealPlan;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;


@Component
public interface MealPlanDao {

    int findIdByUsername(String username);

    List<MealPlan> getAllMyMealPlans(Principal principal);
}
