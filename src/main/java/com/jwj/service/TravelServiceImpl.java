package com.jwj.service;

import com.jwj.dao.TravelMapper;
import org.springframework.stereotype.Service;

@Service
public class TravelServiceImpl implements TravelService {
    private TravelMapper travelMapper;

    public void setTravelMapper(TravelMapper travelMapper) {
        this.travelMapper = travelMapper;
    }
}
