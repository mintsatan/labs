package com.company;

import static com.company.TypeOfThings.*;

public interface Quality {

    static int quality(TypeOfThings typ) {
        switch (typ) {
            case FABRIC:
                return 3;
            case WOOD:
                return 6;
            case GLASS:
                return 2;
            case METAL:
                return 8;
            case PAPER:
                return 1;
            case RUBBER:
                return 4;
            case PLASTIC:
                return 5;
            default:
                throw new IllegalStateException("Unexpected value: " + typ);
        }
    }
}
