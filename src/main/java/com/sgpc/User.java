package com.sgpc;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Getter
    private static int counter = 0;
    {
        counter++;
    }
    private String name;
    private String gender;
    private int age;
    private int height;
    private int weight;


    public User (String name,  int age) {
        this.name = name;
        this.age = age;
    }

}


