package com.jwj.service;

import com.jwj.dao.RequestMapper;
import com.jwj.pojo.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestMapper requestMapper;

    public void setRequestMapper(RequestMapper requestMapper) {
        this.requestMapper = requestMapper;
    }

    public int addRequest(Request request) {
        return requestMapper.addRequest(request);
    }

    //query all requests of this author
    public List<Request> queryRequestByAuthor(String requestAuthor) {
        return requestMapper.queryRequestByAuthor(requestAuthor);
    }

    //count how many requests are unresolved
    public int countUnresolvedRequest() {
        return requestMapper.countUnresolvedRequest();
    }

    //query all unresolved requests
    public List<Request> queryUnresolvedRequest() {
        return requestMapper.queryUnresolvedRequest();
    }

    //mark request as with incorrect information
    public int markAsIncorrect(Request request) {
        return requestMapper.markAsIncorrect(request);
    }

    //get request by id
    public Request getRequestById(int requestId) {
        return requestMapper.getRequestById(requestId);
    }

    //mark request as resolved
    public int markAsResolved(Request request) {
        return requestMapper.markAsResolved(request);
    }
}
