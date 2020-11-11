package com.jwj.service;

import com.jwj.pojo.Travel;

import java.util.List;

public interface TravelService {

    //query All travel histories
    List<Travel> queryAllTravel();

    //add a new travel
    int addTravel(Travel travel);

    //get travel country by its name
    String getCountryByName(String travelName);

    //check whether this travel is in db
    boolean checkTravelExist(String travelName);

    //delete a travel history by travelId
    int deleteTravelById(int travelId);

    //update a travel
    int updateTravel(Travel travel);

    //query movie by id
    Travel queryTravelById(int travelId);

    //query movie by name
    List<Travel> queryTravelByName(String travelName);
}
