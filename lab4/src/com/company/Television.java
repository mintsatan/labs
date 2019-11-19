package com.company;

public class Television extends Media {
    private String name;
    private int wavelength;
    private int waveFrequency;

    public String getName() {
        return name;
    }

    Television(String name, int wavelength, int waveFrequency) {
        this.name = name;
        this.wavelength = wavelength;
        this.waveFrequency = waveFrequency;
    }

    @Override
    protected String translate(Themes broadcast1, Themes broadcast2, Themes broadcast3) {
        return "In those days " + getName() + " was translating" + " some news about: " + broadcast1 + ", " + broadcast2 + ", " +  broadcast3 + ". ";
    }
}
