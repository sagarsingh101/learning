package com.sagar.learning.rateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sagarsingh on 2020-02-25
 */
public class HourlyRateLimiter implements RateLimiterService {
    private RateLimitCounterService counterService;
    private UserService userService;
    private static final Map<String,Integer> DEFAULT_API_LIMIT = new HashMap<>();

    public HourlyRateLimiter(UserService userService,long timeLimit) {
        this.counterService = new HashMapRateLimiterService(timeLimit);
        this.userService = userService;
    }

    @Override
    public void addDefaultLimits(String api,int limit) {
        DEFAULT_API_LIMIT.put(api,limit);
    }

    @Override
    public boolean isLimitReached(String userId,String api) {
        int userLimit = userService.fetchUserLimitByAPI(userId,api);
        if(userLimit==-1) {
            userLimit = DEFAULT_API_LIMIT.getOrDefault(api,0);
        }
        int lastHourCount =aggregateAPICalls(counterService.apiCallsRecorded(userId,api));
        if(lastHourCount<userLimit) {
            counterService.recordAPICall(userId,api);
            return false;
        } else {
            return true;
        }

    }

    private int aggregateAPICalls(Map<Long, Integer> userApiCalls) {
        if(userApiCalls==null) {
            return 0;
        }
        int sum = 0;
        for(int callByTimeStamp:userApiCalls.values()) {
            sum+=callByTimeStamp;
        }
        return sum;
    }


}
