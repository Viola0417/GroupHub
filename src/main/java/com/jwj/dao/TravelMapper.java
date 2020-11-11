package com.jwj.dao;

import com.jwj.pojo.Travel;

import java.util.List;

public interface TravelMapper {

    //query All travel histories
    List<Travel> queryAllTravel();

    //add a new travel
    int addTravel(Travel travel);

    //get travel country by its name
    String getCountryByName(String travelName);

    //check whether this travel is in db
    int checkTravelExist(String travelName);

    //delete a travel by travelId
    int deleteTravelById(int travelId);

    //update a travel
    int updateTravel(Travel travel);

    //query travel by id
    Travel queryTravelById(int travelId);

    //query travel by name
    List<Travel> queryTravelByName(String travelName);
}
