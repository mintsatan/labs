package com.company;

public class DancingCouples extends Human {

    DancingCouples (String str) {
        super(str);
    }

    public String appear(String place) {
        return getName() + " appear " + place + ". ";
    }
}
