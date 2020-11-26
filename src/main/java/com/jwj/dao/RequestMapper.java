package com.jwj.dao;

import com.jwj.pojo.Request;

import java.util.List;

public interface RequestMapper {

    //add a new request
    int addRequest(Request request);

    //query all requests of this author
    List<Request> queryRequestByAuthor(String requestAuthor);

    //count how many requests are unresolved
    int countUnresolvedRequest();

    //query all unresolved requests
    List<Request> queryUnresolvedRequest();

    //mark request as with incorrect information
    int markAsIncorrect(Request request);

    //get request by id
    Request getRequestById(int requestId);

    //mark request as resolved
    int markAsResolved(Request request);

}
