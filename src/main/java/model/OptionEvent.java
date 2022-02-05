package model;

import utils.MathUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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

        BigDecimal bigOptionsNumber = BigDecimal.valueOf(optionsNumber);
        BigDecimal numberOfPossibleOptions = bigOptionsNumber.pow(numberOfAttempts);
        BigInteger optionsNumberFactorial = MathUtils.factorial(optionsNumber);

        for (int option = 1; option <= optionsNumber && option <= numberOfAttempts; option++) {
            BigDecimal combinations = (new BigDecimal(optionsNumberFactorial)).divide(
                new BigDecimal(MathUtils.factorial(optionsNumber - option).multiply(MathUtils.factorial(option))), 10, RoundingMode.HALF_UP);
            BigDecimal uniquePositiveOptions = uniquePositiveOptions(option, numberOfAttempts);
            BigDecimal probability = (combinations.multiply(uniquePositiveOptions)).divide(numberOfPossibleOptions, 10, RoundingMode.HALF_UP);
            probabilities.add(probability.doubleValue());
        }
        return probabilities;
    }

    private BigDecimal uniquePositiveOptions(int diffOpts, int numberOfAttempts) {
        BigDecimal summ = new BigDecimal(0);
        for (int i = 1; i < diffOpts; i++) {
            BigDecimal combinations = (new BigDecimal(MathUtils.factorial(diffOpts))).divide(
                new BigDecimal(MathUtils.factorial(diffOpts - i).multiply(MathUtils.factorial(i))), 10, RoundingMode.HALF_UP);
            summ = summ.add(uniquePositiveOptions(i, numberOfAttempts).multiply(combinations));
        }

        return (new BigDecimal(diffOpts).pow(numberOfAttempts)).subtract(summ);
    }
}