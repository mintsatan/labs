package com.company;

public class Shorties extends Human {
    private  int planetCoordinateX = 56;
    private int planetCoordinateY = 98;
    private int planetCoordinateZ = 3;
    private ShortysHouse house;

    Shorties(String str, int number, String type, ShortysHouse house) {
        super(str, number, type);
        this.house = house;
        house.isOpen = true;
    }

    static String throwBalls() {
        return "Shorties throws Balls. ";
    }

    String dodge(boolean hit, Things thing) {

        class EyeOfWorker {
            public String bruise() {
                return " has a black eye.";
            }
        }

        EyeOfWorker eye = new EyeOfWorker();

        thing.fall();
        if (hit) {
            try {
                return getName() + pain() + getName() + " didn't have time to dodge. " + came(house, 15) + getName() + eye.bruise();
            } catch (InstitutionIsOpenException err) {
                return err.getMessage();
            }
        } else {
            return getName() + " dodges " + thing.getName() + ". ";
        }
    }

    public String calculations(boolean guess) {
        return "Calculation " + getName() + "'s " + (guess ? "turned out to be true. " : " turned out to be wrong. ");
    }

    @Override
    public String standing() {
        return getName() + " is standing on the platform. ";
    }

    @Override
    public String didSomething(String action, boolean negation, String thing) {
        return getName() + (negation ? " didn't " : " ") + action + " " + thing + ". ";
    }

    @Override
    public String checkCoordinates() {
        if (planetCoordinateX == 56 && planetCoordinateY == 98 && planetCoordinateZ == 3) {
            return "Coordinates of " + getName() + " haven't changed. ";
        } else {
            return "New coordinates: " + planetCoordinateX + ", " + planetCoordinateY + ", " + planetCoordinateZ + ". ";
        }
    }

    public String saw(Shorties shorty) {
        return getName() + " saw " + shorty.getName() + ". ";
    }
}

