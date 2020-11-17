package com.jwj.controller;

import com.jwj.pojo.Travel;
import com.jwj.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/travel")
public class TravelController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("TravelServiceImpl")
    private TravelService travelService;

    //show all travel histories;
    @RequestMapping("/toTravel")
    public String toTravel(Model model) {
        List<Travel> travelList = travelService.queryAllTravel();
        model.addAttribute("travelList", travelList);
        return "adminTravel";
    }

    @RequestMapping("/toAddTravel")
    public String toAddTravel(Model model) {
        return "adminAddTravel";
    }

    @RequestMapping("/addTravel")
    public String addTravel(@RequestParam("travelName") String travelName, @RequestParam("travelCountry") String travelCountry,
                           @RequestParam("travelDescription") String travelDescription, Model model) {
        System.out.println("name => " + travelName + ", country => " + travelCountry + ", description => " + travelDescription);

        // if this travel has been added(same travel name)
        //prompt error msg
        String errorMsg = "";
        String msg = "";
        if (travelService.checkTravelExist(travelName)) {
            errorMsg = "This travel has been added before!";
            model.addAttribute("errorMsg", errorMsg);
            return "adminAddTravel";
        }
        //else add to database
        travelService.addTravel(new Travel(travelName, travelCountry, travelDescription));
        msg = "Successfully add movie, return";
        model.addAttribute("msg", msg);
        return "adminAddTravel";
    }

    @RequestMapping("/toUpdateTravel")
    public String toUpdateTravel(int travelId, Model model) {
        System.out.println("travelId => " + travelId);
        Travel travel = travelService.queryTravelById(travelId);
        model.addAttribute("queryTravel", travel);
        return "adminUpdateTravel";
    }

    @RequestMapping("/updateTravel")
    public String updateTravel(Travel travel) {
        Travel originalTravel = travelService.queryTravelById(travel.getTravelId());
        travel.setTotalRateNumber(originalTravel.getTotalRateNumber());
        travel.setTotalRateScore(originalTravel.getTotalRateScore());
        travelService.updateTravel(travel);
        return "redirect:/travel/toTravel";
    }

    @RequestMapping("/deleteTravel")
    public String deleteTravel(int travelId) {
        travelService.deleteTravelById(travelId);
        return "redirect:/travel/toTravel";
    }

    @RequestMapping("/toUserTravel")
    public String toUserTravel(Model model) {
        List<Travel> travelList = travelService.queryAllTravel();
        model.addAttribute("travelList", travelList);
        return "userTravel";
    }

    @RequestMapping("/userQueryTravel")
    public String userQueryTravelByName(@RequestParam("queryTravelName") String travelName, Model model) {
        List<Travel> travel = travelService.queryTravelByName(travelName);
        model.addAttribute("travelList", travel);
        return "userTravel";
    }
}
