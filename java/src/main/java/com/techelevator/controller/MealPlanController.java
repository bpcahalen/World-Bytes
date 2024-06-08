package com.techelevator.controller;


import com.techelevator.dao.MealPlanDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.MealPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/mealplans")
@CrossOrigin
public class MealPlanController {

    @Autowired MealPlanDao mealPlanDao;
    @Autowired UserDao userDao;

    public MealPlanController(MealPlanDao mealPlanDao, UserDao userDao) {
        this.mealPlanDao = mealPlanDao;
        this.userDao = userDao;
    }


    // API calls go here
    @GetMapping("/library")
    public List<MealPlan> getAllMyMealPlans(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return getAllMyMealPlans(principal);
    }

}
