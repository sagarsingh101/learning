package com.sagar.learning.rateLimiter;

/**
 * Created by sagarsingh on 2020-02-25
 */
public interface UserService {
    int fetchUserLimitByAPI(String userId, String api);
    void addUser(String userId,String api,int limit);
}
