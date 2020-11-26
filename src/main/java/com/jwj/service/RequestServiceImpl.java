package com.jwj.service;

import com.jwj.dao.RequestMapper;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestMapper requestMapper;

    public void setRequestMapper(RequestMapper requestMapper) {
        this.requestMapper = requestMapper;
    }
}
