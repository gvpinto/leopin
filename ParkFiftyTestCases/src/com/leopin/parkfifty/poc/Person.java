package com.leopin.parkfifty.poc;

import javax.validation.constraints.NotNull;

public class Person {
    @NotNull
    private String name;
    
    public Person(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
