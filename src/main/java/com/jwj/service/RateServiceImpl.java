package com.jwj.service;

import com.jwj.dao.RateMapper;
import com.jwj.pojo.Rate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {
    private RateMapper rateMapper;

    public void setRateMapper(RateMapper rateMapper) {
        this.rateMapper = rateMapper;
    }

    //query movie rate according to categoryId
    public List<Rate> queryMovieRate(int rateCategoryId) {
        return rateMapper.queryMovieRate(rateCategoryId);
    }

    //add a rate
    public int addRate(Rate rate) {
        return rateMapper.addRate(rate);
    }

    //query movie rate according to author and movie
    public List<Rate> queryMovieRateByAuthor(Rate rate) {
        return rateMapper.queryMovieRateByAuthor(rate);
    }

    //query rate by rateId
    public Rate queryRateById(int rateId) {
        return rateMapper.queryRateById(rateId);
    }

    //update rate
    public int updateRate(Rate rate) {
        return rateMapper.updateRate(rate);
    }
}
