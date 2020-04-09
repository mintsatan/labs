package com.company;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @author Yana Mikhailova R3141
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Interactiv4ik my = new Interactiv4ik(new CollectionWork(System.getenv("env_variable_for_my_5_laba")), System.in);
    }
}
