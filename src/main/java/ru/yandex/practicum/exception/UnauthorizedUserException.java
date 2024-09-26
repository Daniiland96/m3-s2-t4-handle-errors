package ru.yandex.practicum.exception;

public class UnauthorizedUserException extends RuntimeException {
    private String user;
    private String owner;

    public UnauthorizedUserException(String user, String owner) {
        this.user = user;
        this.owner = owner;
    }

    public String getUser() {
        return user;
    }

    public String getOwner() {
        return owner;
    }
}