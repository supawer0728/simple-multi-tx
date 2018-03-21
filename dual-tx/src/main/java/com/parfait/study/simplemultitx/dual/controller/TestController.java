package com.parfait.study.simplemultitx.dual.controller;

import com.parfait.study.simplemultitx.dual.service.LogicService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final LogicService logicService;

    @Autowired
    public TestController(@NonNull LogicService logicService) {
        this.logicService = logicService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/required")
    public void required() {
        logicService.saveWithRequired();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/required-exception")
    public void requiredException() {
        logicService.saveWithRequiredException();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/requires-new")
    public void requiresNew() {
        logicService.saveWithRequiresNew();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/requires-new-exception")
    public void requiresNewException() {
        logicService.saveWithRequiresNewException();
    }
}
