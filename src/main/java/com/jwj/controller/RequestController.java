package com.jwj.controller;

import com.jwj.pojo.Request;
import com.jwj.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/request")
public class RequestController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("RequestServiceImpl")
    private RequestService requestService;

    @RequestMapping("/toAddRequest")
    public String toAddRequest() {
        return "userAddRequest";
    }

    @RequestMapping("/addRequest")
    public String addRequest(@RequestParam("requestCategoryId") String categoryIdStr, @RequestParam("requestTitle") String requestTitle,
                             @RequestParam("requestDescription") String requestDescription, HttpSession session, Model model) throws ParseException {
        int requestCategoryId = Integer.parseInt(categoryIdStr);
        String requestAuthor = (String) session.getAttribute("userName");

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date requestTime = sdf.parse(nowTime);

        Request request = new Request(requestAuthor, requestCategoryId, requestTime, requestTitle, requestDescription);
        requestService.addRequest(request);

        String successMsg = "Your request has been submitted!";
        model.addAttribute("successMsg", successMsg);
        return "userAddRequest";

//        if (requestCategoryId == 1) {
//            return "userAddRequest";
//        } else if (requestCategoryId == 2) {
//            return "userAddRequestForBook";
//        }
//        return "userAddRequestForTravel";
    }

    @RequestMapping("/checkRequest")
    public String checkRequest(HttpSession session, Model model) {
        String requestAuthor = (String) session.getAttribute("userName");
        List<Request> requestList = requestService.queryRequestByAuthor(requestAuthor);
        model.addAttribute("requestList", requestList);
        for (Request r: requestList) {
            System.out.println(r.toString());
        }
        return "userRequestStatus";
    }

    @RequestMapping("/adminCheckUnresolvedRequest")
    public String adminCheckUnresolvedRequest(Model model) {
        List<Request> unresolvedRequestList = requestService.queryUnresolvedRequest();
        model.addAttribute("unresolvedRequestList", unresolvedRequestList);
        return "adminUnresolvedRequest";
    }

    @RequestMapping("/markIncorrect")
    public String markIncorrect(int requestId, Model model) {
        System.out.println("requestId => " + requestId);
        Request request = requestService.getRequestById(requestId);
        requestService.markAsIncorrect(request);

        List<Request> unresolvedRequestList = requestService.queryUnresolvedRequest();
        model.addAttribute("unresolvedRequestList", unresolvedRequestList);

        String successMsg = "Request has been marked as incorrect information, user will be informed!";
        model.addAttribute("successMsg", successMsg);
        return "adminUnresolvedRequest";
    }

    @RequestMapping("/markResolved")
    public String markResolved(int requestId, Model model) {
        System.out.println("requestId => " + requestId);
        Request request = requestService.getRequestById(requestId);
        requestService.markAsResolved(request);

        List<Request> unresolvedRequestList = requestService.queryUnresolvedRequest();
        model.addAttribute("unresolvedRequestList", unresolvedRequestList);

        String successMsg = "Request has been marked as resolved!";
        model.addAttribute("successMsg", successMsg);
        return "adminUnresolvedRequest";
    }
}
