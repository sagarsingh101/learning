package com.sagar.learning.rateLimiter;

import java.util.HashMap;

/**
 * Created by sagarsingh on 2020-02-25
 */
public class HashMapUserService implements UserService {
    private HashMap<String,HashMap<String,Integer>> USER_API_LIMIT;

    public HashMapUserService() {
        this.USER_API_LIMIT = new HashMap<>();
    }

    @Override
    public int fetchUserLimitByAPI(String userId, String api) {
        if(USER_API_LIMIT.containsKey(userId) && USER_API_LIMIT.get(userId).containsKey(api)) {
            return USER_API_LIMIT.get(userId).get(api);
        }
        return -1;
     }

    @Override
    public void addUser(String userId, String api, int limit) {
        if (!USER_API_LIMIT.containsKey(userId)) {
            USER_API_LIMIT.put(userId, new HashMap<>());
        }
        USER_API_LIMIT.get(userId).put(api,limit);
    }
}
