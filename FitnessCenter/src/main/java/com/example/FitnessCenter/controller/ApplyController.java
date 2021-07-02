package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.service.ApplyService;
import com.example.FitnessCenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/applys")
public class ApplyController {

    private final ApplyService  applyService;
    private final UserService userService;

    @Autowired
    public ApplyController(ApplyService applyService, UserService userService) {
        this.applyService = applyService;
        this.userService = userService;
    }
}
