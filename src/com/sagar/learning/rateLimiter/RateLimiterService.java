package com.sagar.learning.rateLimiter;

/**
 * Created by sagarsingh on 2020-02-25
 */
public interface RateLimiterService {
    boolean isLimitReached(String userId,String api);
    void addDefaultLimits(String api,int limit);
}
