package com.leopin.parkfifty.poc;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Car {
    @NotNull
    @Valid
    private Person driver;
    
    public Car(Person driver) {
        this.driver = driver;
    }

}
