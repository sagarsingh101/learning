package com.sagar.learning.rateLimiter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sagarsingh on 2020-02-26
 */
public class RateLimiterInitializer {
    private UserService userService;
    private RateLimitCounterService counterService;
    private long time;
    List<Class<?>> apiControllerList;

    public static RateLimiterService getDefault(Class<?>...classes) {
       return new RateLimiterInitializer()
                .withUserService(new HashMapUserService())
               .wiithCutOffTime(10)
                .forClasses(classes)
                .build();
    }

    public RateLimiterInitializer withUserService(UserService userService) {
        this.userService =userService;
        return this;
    }

    public RateLimiterInitializer wiithCutOffTime(long time) {
        this.time = time;
        return this;
    }
    public RateLimiterInitializer forClasses(Class<?> ...classes) {
        apiControllerList = Arrays.asList(classes);
        return this;
    }

    public RateLimiterService build() {
        RateLimiterService rateLimiterService = new HourlyRateLimiter(userService,time);
        for(Class clazz:apiControllerList) {
            Method[] methods = clazz.getMethods();
            for(Method method:methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for(Annotation annotation:annotations) {
                    if(annotation instanceof RateLimit) {
                        RateLimit rateLimitAnnotation = (RateLimit) annotation;
                        rateLimiterService.addDefaultLimits(rateLimitAnnotation.api(),rateLimitAnnotation.limit());
                    }
                }
            }
        }
        return rateLimiterService;
     }
}

