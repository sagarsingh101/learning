package com.sagar.learning.rateLimiter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sagarsingh on 2020-02-26
 */
public class HashMapRateLimiterService implements RateLimitCounterService {
    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, TreeMap<Long,Integer>>> USER_API_LIMIT_COUNTER = new ConcurrentHashMap<>();
    private final Long TIME_LIMIT;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public HashMapRateLimiterService(long timeLimit) {
        TIME_LIMIT = timeLimit;
    }

    /**
     * Adding and checking is done in buckets of seconds , this can changed to have minutely buckets to save space
     * if we have overall hourly limit;
     * @param userId
     * @param api
     * @return
     */
    public Map<Long,Integer> apiCallsRecorded(String userId, String api) {
        if(USER_API_LIMIT_COUNTER.containsKey(userId) && USER_API_LIMIT_COUNTER.get(userId).containsKey(api)) {
            long currentTime = System.currentTimeMillis()/1000;
            try {
                readWriteLock.readLock().lock();
                return USER_API_LIMIT_COUNTER.get(userId).get(api).subMap(currentTime - TIME_LIMIT, true, currentTime, true);
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
        return null;
    }

    public void recordAPICall(String userId,String api){
        if(!USER_API_LIMIT_COUNTER.containsKey(userId)) {
            USER_API_LIMIT_COUNTER.put(userId,new ConcurrentHashMap<>());
        }
        if(!USER_API_LIMIT_COUNTER.get(userId).containsKey(api)) {
            USER_API_LIMIT_COUNTER.get(userId).put(api,new TreeMap<>());
        }
        long currentTime=System.currentTimeMillis()/1000;
        try {
            readWriteLock.writeLock().lock();
            int currentCount = USER_API_LIMIT_COUNTER.get(userId).get(api).getOrDefault(currentTime, 0);
            currentCount++;
            USER_API_LIMIT_COUNTER.get(userId).get(api).put(currentTime, currentCount);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

}
