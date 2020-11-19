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
    public List<Rate> queryBookRate(int rateCategoryId) { return rateMapper.queryBookRate(rateCategoryId); }
    public List<Rate> queryTravelRate(int rateCategoryId) {
        return rateMapper.queryTravelRate(rateCategoryId);
    }

    //add a rate
    public int addRate(Rate rate) {
        return rateMapper.addRate(rate);
    }
    public int addRateForBook(Rate rate) { return rateMapper.addRateForBook(rate); }
    public int addRateForTravel(Rate rate) {
        return rateMapper.addRateForTravel(rate);
    }

    //query movie rate according to author and movie
    public List<Rate> queryMovieRateByAuthor(Rate rate) {
        return rateMapper.queryMovieRateByAuthor(rate);
    }
    public List<Rate> queryBookRateByAuthor(Rate rate) { return rateMapper.queryBookRateByAuthor(rate); }
    public List<Rate> queryTravelRateByAuthor(Rate rate) {
        return rateMapper.queryTravelRateByAuthor(rate);
    }

    //query rate by rateId
    public Rate queryRateById(int rateId) {
        return rateMapper.queryRateById(rateId);
    }

    //update rate
    public int updateRate(Rate rate) {
        return rateMapper.updateRate(rate);
    }

    //delete rate
    public int deleteRateById(int rateId) {
        return rateMapper.deleteRateById(rateId);
    }

    //add comment for this rate
    public int addCommentById(int rateId) {
        return rateMapper.addCommentById(rateId);
    }
}
