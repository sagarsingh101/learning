package com.sagar.learning.rateLimiter;

/**
 * Created by sagarsingh on 2020-02-26
 */
public class TestRESTController {

    @RateLimit(api="user",limit=30)
    public boolean getUserDetails(String userId)  {
        if(RateLimiterContext.getRateLimiterService().isLimitReached(userId,"user")) {
            System.out.println("Rejecting as limit for user reached on " + System.currentTimeMillis()/1000);
            return false;
        } else {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {

            }
            return true;
        }
    }

    @RateLimit(api="update",limit=20)
    public boolean updateUser(String userId)  {
        if(RateLimiterContext.getRateLimiterService().isLimitReached(userId,"update")) {
            System.out.println("Rejecting as limit for update reached on " + System.currentTimeMillis()/1000);
            return false;
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
            return true;
        }
    }
}
