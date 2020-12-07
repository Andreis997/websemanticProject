package com.hypnos.websemantic.controllers;

import com.hypnos.websemantic.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeachingToolsController {

    @GetMapping("/list")
    public String listItems() {
        return "Test!";
    }

    public String listSpecificItems(
            @RequestParam(value = "xDays", defaultValue = "0", required = false) Integer xDays,
            @RequestParam(value = "category", defaultValue = "", required = false) String category
    ) {
        return "";
    }

    @GetMapping("/item/")
    public String showItem(@RequestParam String id) {
        return "";
    }

    @PostMapping("/item/add")
    public String addItem(@ModelAttribute Item item) {
        return "";
    }
}
