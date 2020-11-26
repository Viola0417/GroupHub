package com.jwj.controller;

import com.jwj.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class RequestController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("RequestServiceImpl")
    private RequestService requestService;
}
