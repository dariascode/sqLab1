package com.sgpc;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class UserController {

    public int getAgeDifference(User user1, User user2) {
        validateUser(user1);
        validateUser(user2);
        return Math.abs(user1.getAge() - user2.getAge());
    }

    public double calculateBMI(User user) {
        validateUser(user);
        double heightInMeters = (double) user.getHeight() /100;
        return user.getWeight() / (heightInMeters * heightInMeters);
    }

    public String obesityLevel(User user) {
        validateUser(user);
        double bmi = calculateBMI(user);

        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }

    public boolean isHealthy(User user) {
        validateUser(user);
        return obesityLevel(user).equalsIgnoreCase("Normal");
    }

    public User getYoungerUser(User user1, User user2) {
        validateUser(user1);
        validateUser(user2);

        return user1.getAge() <= user2.getAge() ? user1 : user2;
    }

    private void validateUser(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }
        if (user.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        if (user.getHeight() <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
    }
}
