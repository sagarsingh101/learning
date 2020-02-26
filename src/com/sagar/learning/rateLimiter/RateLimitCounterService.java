package com.sagar.learning.rateLimiter;

import java.util.Map;

/**
 * Created by sagarsingh on 2020-02-26
 */
public interface RateLimitCounterService {
    Map<Long,Integer> apiCallsRecorded(String userId,String api);
    void recordAPICall(String userId,String api);
}
