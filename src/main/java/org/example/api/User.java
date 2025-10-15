package org.example.api;

import java.util.concurrent.TimeUnit;

public class User {
    String email, phoneNum;
    Long lastActiveTime;

    public User() {
        lastActiveTime = System.currentTimeMillis();
    }

    public boolean activeAfter(int threshold, TimeUnit timeUnit) {
        return System.currentTimeMillis() - lastActiveTime > timeUnit.toMillis(threshold);
    }
}
