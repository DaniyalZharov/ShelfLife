//
//package org.example.shelflife.controllers;
//
//import org.example.shelflife.services.FoodService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Controller
//public class WebController {
//
//    @Autowired
//    private FoodService service;
//
//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }
//
//    @GetMapping("/about")
//    public String about() {
//        return "about";
//    }
//
//    @GetMapping("/contact")
//    public String contact() {
//        return "contact";
//    }
//
//    @GetMapping("/foods")
//    public String showFoods(Model model) {
//        List<Map<String,Object>> view = service.findAll().stream().map(f -> {
//            long days = ChronoUnit.DAYS.between(LocalDate.now(), f.getExpiryDate());
//            Map<String,Object> m = new HashMap<>();
//            m.put("name", f.getName());
//            m.put("expiryDate", f.getExpiryDate());
//            m.put("daysRemaining", days);
//            m.put("isExpired", days <= 0);
//            m.put("isNearExpiry", days > 0 && days <= 2);
//            m.put("mainIngredient", f.getMainIngredient());
//            return m;
//        }).collect(Collectors.toList());
//        model.addAttribute("foods", view);
//        return "foods";
//    }
//
//    @GetMapping("/recipes")
//    public String recipes(@RequestParam(required = false, defaultValue = "") String ingredient, Model model) {
//        // URL-encode the ingredient to handle spaces and special characters
//        String encodedIngredient = ingredient.replace(" ", "_").toLowerCase();
//        model.addAttribute("ingredient", ingredient);
//        return "recipes";
//    }
//}


package org.example.shelflife.controllers;

import org.example.shelflife.entities.FoodItem;
import org.example.shelflife.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class WebController {
    @Autowired
    private FoodService service;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/foods")
    public String showFoods(Model model) {
        List<Map<String,Object>> view = service.findAll().stream().map(f -> {
            long days = ChronoUnit.DAYS.between(LocalDate.now(), f.getExpiryDate());
            Map<String,Object> m = new HashMap<>();
            m.put("id", f.getId());  // Add the ID for delete functionality
            m.put("name", f.getName());
            m.put("expiryDate", f.getExpiryDate());
            m.put("daysRemaining", days);
            m.put("isExpired", days <= 0);
            m.put("isNearExpiry", days > 0 && days <= 2);
            m.put("mainIngredient", f.getMainIngredient());
            m.put("quantity", f.getQuantity());
            return m;
        }).collect(Collectors.toList());
        model.addAttribute("foods", view);
        return "foods";
    }

    @GetMapping("/recipes")
    public String recipes(@RequestParam(required = false) String ingredient, Model model) {
        // If no ingredient is provided, don't set a default
        if (ingredient == null || ingredient.trim().isEmpty()) {
            model.addAttribute("ingredient", "");
        } else {
            // URL-encode the ingredient to handle spaces and special characters
            String encodedIngredient = ingredient.replace(" ", "_").toLowerCase();
            model.addAttribute("ingredient", ingredient);
        }
        return "recipes";
    }
}