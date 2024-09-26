package ru.yandex.practicum.exception;

public class HappinessOverflowException extends RuntimeException {
    private int happinessLevel;

    public HappinessOverflowException(int happinessLevel) {
        super(String.valueOf(happinessLevel));
         this.happinessLevel = happinessLevel;
    }

    public int getHappinessLevel() {
        return happinessLevel;
    }
}