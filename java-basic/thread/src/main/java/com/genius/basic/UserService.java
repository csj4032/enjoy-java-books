package com.genius.basic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserService {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i < 100; i++) {
            int id = i;
            new Thread(() -> {
                String birthDay = new UserService().birthDate(id);
                System.out.println(id + " : " + birthDay);
            }).start();
        }

        Thread.sleep(10);

        for (int i = 1; i < 100; i++) {
            int id = i;
            new Thread(() -> {
                String birthDay = new UserService().birthDateSafe(id);
                System.out.println(id + " : " + birthDay);
            }).start();
        }
    }

    private String birthDate(int userId) {
        Date birthDate = new Date(userId, 5, 4);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(birthDate);
    }

    private String birthDateSafe(int userId) {
        Date birthDate = new Date(userId, 5, 4);
        final SimpleDateFormat df = ThreadSafeFormatter.dataFormatter.get();

        return df.format(birthDate);
    }
}