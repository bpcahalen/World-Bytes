package com.techelevator.controller;


import com.techelevator.dao.MealPlanDao;
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

    private final MealPlanDao dao;

    public MealPlanController(MealPlanDao dao) {
        this.dao = dao;
    }


    // API calls go here
    @RequestMapping(path = "/id/username", method = RequestMethod.GET)
    public int findIdByUsername(@RequestParam String username) {
        return mealPlanDao.findIdByUsername(username);
    }

    @GetMapping("/library")
    public List<MealPlan> getAllMyMealPlans(Principal principal) {
        return getAllMyMealPlans(principal);
    }

}
