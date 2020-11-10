package com.jwj.controller;

import com.jwj.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/travel")
public class TravelController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("TravelServiceImpl")
    private TravelService travelService;
}
