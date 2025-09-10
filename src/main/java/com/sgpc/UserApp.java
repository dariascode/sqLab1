package com.sgpc;

import java.util.Arrays;
import java.util.Scanner;

public class UserApp {
    //Vlad Test git
    public static void main(String[] args) {
        UserController controller = new UserController();

        User u1 = new User("Alice", "Female", 25, 165, 55);
        User u2 = new User("Bob", "Male", 30, 175, 80);
        User u3 = new User("Charlie", "Male", 40, 180, 95);
        User u4 = new User("Diana", "Female", 22, 160, 45);


        User[] users = {u1, u2, u3, u4};


        System.out.println("\nUser list");
        Arrays.stream(users).forEach(System.out::println);

        System.out.println("\n BMI and obesity level");
        Arrays.stream(users).forEach(u ->
                System.out.printf(
                        u.getName(),
                        controller.calculateBMI(u),
                        controller.obesityLevel(u))
        );

        System.out.println("\n Not healthy users");
        Arrays.stream(users)
                .filter(u -> !controller.isHealthy(u))
                .forEach(u ->
                        System.out.printf( u.getName(), controller.obesityLevel(u))
                );


        System.out.println("\n Custom BMI levels");
        Arrays.stream(users).forEach(u -> {
            double bmi = controller.calculateBMI(u);
            String level;
            if (bmi < 20) level = "LOW";
            else if (bmi < 27) level = "OK";
            else level = "HIGH";
            System.out.printf( u.getName(), bmi, level);
        });


        System.out.println("\nChange age");
        u1.setAge(u2.getAge());
        System.out.println("Alice new age = " + u1.getAge());


        System.out.println("\n Younger user");
        User younger = controller.getYoungerUser(u1, u2);
        System.out.println("Younger is: " + younger.getName());


        System.out.println("\nSearch by name");
        String searchName = "Charlie";
        Arrays.stream(users)
                .filter(u -> u.getName().equalsIgnoreCase(searchName))
                .findFirst()
                .ifPresent(System.out::println);


        System.out.println("\nAverage age ");
        double avgAge = Arrays.stream(users).mapToInt(User::getAge).average().orElse(0);
        System.out.println("Average age = " + avgAge);


        runMenu(users, controller);
    }


    private static void runMenu(User[] users, UserController controller) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Show all users");
            System.out.println("2. Search user by name");
            System.out.println("3. Show youngest user");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> Arrays.stream(users).forEach(System.out::println);
                case 2 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    Arrays.stream(users)
                            .filter(u -> u.getName().equalsIgnoreCase(name))
                            .findFirst()
                            .ifPresentOrElse(
                                    System.out::println,
                                    () -> System.out.println("User not found")
                            );
                }
                case 3 -> {
                    if (users.length >= 2) {
                        User youngest = Arrays.stream(users)
                                .reduce((a, b) -> controller.getYoungerUser(a, b))
                                .orElse(null);
                        System.out.println("Youngest: " + youngest);
                    }
                }
                case 4 -> System.out.println("Bye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
}
