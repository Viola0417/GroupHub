package com.jwj.service;

import com.jwj.dao.TravelMapper;
import com.jwj.pojo.Travel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {
    private TravelMapper travelMapper;

    public void setTravelMapper(TravelMapper travelMapper) {
        this.travelMapper = travelMapper;
    }

    //query All travel histories
    public List<Travel> queryAllTravel() {
        return travelMapper.queryAllTravel();
    }

    //add a new travel
    public int addTravel(Travel travel) {
        return travelMapper.addTravel(travel);
    }

    //get travel country by its name
    public String getCountryByName(String travelName) {
        return travelMapper.getCountryByName(travelName);
    }

    //check whether this travel is in db
    public boolean checkTravelExist(String travelName) {
        return travelMapper.checkTravelExist(travelName) > 0;
    }

    //delete a travel by travelId
    public int deleteTravelById(int travelId) {
        return travelMapper.deleteTravelById(travelId);
    }

    //update a travel
    public int updateTravel(Travel travel) {
        return travelMapper.updateTravel(travel);
    }

    //query travel by id
    public Travel queryTravelById(int travelId) {
        return travelMapper.queryTravelById(travelId);
    }

    //query travel by name
    public List<Travel> queryTravelByName(String travelName) {
        return travelMapper.queryTravelByName(travelName);
    }
}
