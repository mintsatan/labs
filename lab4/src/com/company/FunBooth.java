package com.company;

public class FunBooth extends Institution {
    private static TypeOfInstitution type = TypeOfInstitution.BOOTH;

    FunBooth(String name, int square, TypeOfInstitution type) {
        super(name, square, type);
    }

    public String earnings(Human human) {
        return human.getName() + " trying to make money in " + getName() + ". ";
    }

    @Override
    public String open() {
        return "Fun booth opened.";
    }

    public TypeOfInstitution getType() {
        return type;
    }

    protected static void setType(TypeOfInstitution institution) {
        type = institution;
    }
}
