package com.company;

public class FunBooth extends Institution {
    private static TypeOfInstitution type = TypeOfInstitution.BOOTH;

    FunBooth(String name, int square) {
        super(name, square);
    }

    public class Curtain {
        boolean CisOpen;

        public Curtain(){
            if (isOpen) {
                CisOpen = false;
            }
        }

        public String openCurtain() {
            CisOpen = true;
            return "Curtain is open.";
        }

        public String closeCurtain() {
            CisOpen = false;
            return "Curtain is closed.";
        }
    }

    public String earnings(Human human) throws InstitutionIsOpenException {
        if (!isOpen) throw new InstitutionIsOpenException("Unable to interact with closed establishment");
        return human.getName() + " trying to make money in " + getName() + ". ";
    }

    public TypeOfInstitution getType() {
        return type;
    }

    protected static void setType(TypeOfInstitution institution) {
        type = institution;
    }
}
