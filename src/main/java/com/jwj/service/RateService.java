package com.jwj.service;

import com.jwj.pojo.Rate;

import java.util.List;


public interface RateService {

    //query rate according to categoryId for each category
    List<Rate> queryMovieRate(int rateCategoryId);
    List<Rate> queryBookRate(int rateCategoryId);
    List<Rate> queryTravelRate(int rateCategoryId);

    //add a rate
    int addRate(Rate rate);
    int addRateForBook(Rate rate);
    int addRateForTravel(Rate rate);

    //query rate according to author
    List<Rate> queryMovieRateByAuthor(Rate rate);
    List<Rate> queryBookRateByAuthor(Rate rate);
    List<Rate> queryTravelRateByAuthor(Rate rate);

    // This one can be shared by 3 categories
    //query rate by rateId
    Rate queryRateById(int rateId);

    // This one can be shared by 3 categories
    //update rate
    int updateRate(Rate rate);

    //delete rate
    int deleteRateById(int rateId);

    //add comment for this rate
    int addCommentById(int rateId);
}
