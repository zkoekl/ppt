package model;

import utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class OptionEvent extends Event {

    private final int optionsNumber;

    public OptionEvent(int optionsNumber) {
        this.optionsNumber = optionsNumber;
    }

    public double eventChance(int numberOfEvents) {
        return Math.pow(1d / optionsNumber, numberOfEvents);
    }

    public List<Double> getDiffOptionsPresence(int numberOfAttempts) {
        List<Double> probabilities = new ArrayList<>();
        double numberOfPossibleOptions = Math.pow(optionsNumber, numberOfAttempts);
        long optionsNumberFactorial = MathUtils.factorial(optionsNumber);
        for (int option = 1; option <= optionsNumber && option <= numberOfAttempts; option++) {
            long combinations = optionsNumberFactorial / (MathUtils.factorial(optionsNumber - option) * MathUtils.factorial(option));
            long uniquePositiveOptions = uniquePositiveOptions(option, numberOfAttempts);
            Double probability = (combinations * uniquePositiveOptions) / numberOfPossibleOptions;
            //TODO add optional rounding
            probabilities.add(probability);
        }
        return probabilities;
    }

    private long uniquePositiveOptions(int diffOpts, int numberOfAttempts) {
        //TODO add caching
        long summ = 0;
        for (int i = 1; i < diffOpts; i++) {
            long combinations = MathUtils.factorial(diffOpts) / (MathUtils.factorial(diffOpts - i) * MathUtils.factorial(i));
            summ += uniquePositiveOptions(i, numberOfAttempts) * combinations;
        }
        return (long) (Math.pow(diffOpts, numberOfAttempts) - summ);
    }
}