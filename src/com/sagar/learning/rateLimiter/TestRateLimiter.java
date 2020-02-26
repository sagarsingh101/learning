package com.sagar.learning.rateLimiter;

import java.util.Random;

/**
 * Created by sagarsingh on 2020-02-25
 */
public class TestRateLimiter {
    private static final Random random = new Random();
    private static final UserService userService = new HashMapUserService();
    public static void main(String[] args) {
       RateLimiterService service = new RateLimiterInitializer()
               .withUserService(userService)
               .forClasses(TestRESTController.class)
               .wiithCutOffTime(20)
               .build();
            userService.addUser("abc","user",20);
                TestRESTController testRESTController = new TestRESTController();
        RateLimiterContext.setContext(service);
        while (true) {
            if(testRESTController.getUserDetails("abc")) {
                System.out.println("Call Successful on " + System.currentTimeMillis()/1000);
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
            }
        }
    }
}
