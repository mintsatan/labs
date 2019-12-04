package com.company;

public interface Pain {
    public default String pain() {
        return " hurts. ";
    }
}
