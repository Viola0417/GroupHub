package com.jwj.service;

import com.jwj.pojo.Rate;

import java.util.List;


public interface RateService {

    //query movie rate according to categoryId
    List<Rate> queryMovieRate(int rateCategoryId);

    //add a rate
    int addRate(Rate rate);

    //query movie rate according to author and movie
    List<Rate> queryMovieRateByAuthor(Rate rate);

    //query rate by rateId
    Rate queryRateById(int rateId);

    //update rate
    int updateRate(Rate rate);
}
