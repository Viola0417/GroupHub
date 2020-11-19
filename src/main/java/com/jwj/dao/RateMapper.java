package com.jwj.dao;

import com.jwj.pojo.Rate;

import java.util.List;

public interface RateMapper {

    //query movie rate according to categoryId
    List<Rate> queryMovieRate(int rateCategoryId);
    List<Rate> queryBookRate(int rateCategoryId);
    List<Rate> queryTravelRate(int rateCategoryId);

    //add a rate
    int addRate(Rate rate);
    int addRateForBook(Rate rate);
    int addRateForTravel(Rate rate);

    //query movie rate according to author and movie
    List<Rate> queryMovieRateByAuthor(Rate rate);
    List<Rate> queryBookRateByAuthor(Rate rate);
    List<Rate> queryTravelRateByAuthor(Rate rate);

    //query rate by rateId
    Rate queryRateById(int rateId);

    //update rate
    int updateRate(Rate rate);

    //delete rate
    int deleteRateById(int rateId);

    //add comment for this rate
    int addCommentById(int rateId);
}
