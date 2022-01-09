package model;

public class OptionEvent extends Event {

    private final int optionsNumber;

    public OptionEvent(int optionsNumber) {
        this.optionsNumber = optionsNumber;
    }

    public double eventChance(int numberOfEvents) {
        return Math.pow(1d / optionsNumber, numberOfEvents);
    }
}