package com.api.datagenerator;


import com.api.models.User;
import com.github.javafaker.Faker;

public class UserDataGenerator {

    private static final Faker faker = new Faker();

    // Generate a valid user
    public static User getValidUser() {
        User user = new User();
        user.setEmail(faker.internet().emailAddress());
        user.setUsername(faker.name().username());
        user.setPassword(faker.internet().password(8, 16));
        return user;
    }

}
