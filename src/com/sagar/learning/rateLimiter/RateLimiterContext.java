package com.sagar.learning.rateLimiter;

/**
 * Created by sagarsingh on 2020-02-26
 */
public class RateLimiterContext {
    private static RateLimiterService rateLimiterService;
    private static final Object SYNC_OBJECT = new Object();
    private RateLimiterContext () {
    }

    public static void setContext(RateLimiterService instantiation) {
        if(rateLimiterService!=null) {
            throw new RuntimeException("Cannot initialize rate limiter service twice in same context");
        }
        synchronized (SYNC_OBJECT) {
            if(rateLimiterService==null) {
                rateLimiterService=instantiation;
            } else {
                throw new RuntimeException("Cannot initialize rate limiter service twice in same context");
            }
        }
    }

    public static RateLimiterService getRateLimiterService() {
        return rateLimiterService;
    }
}
