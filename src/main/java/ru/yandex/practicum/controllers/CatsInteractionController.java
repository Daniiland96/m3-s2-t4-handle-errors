package ru.yandex.practicum.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.exception.HappinessOverflowException;
import ru.yandex.practicum.exception.IncorrectCountException;

import java.util.Map;

@RestController
@RequestMapping("/cats")
public class CatsInteractionController {

    private int happiness = 0;

    @GetMapping("/converse")
    public Map<String, String> converse() {
        if (happiness >= 10) {
            throw new HappinessOverflowException(happiness);
        }
        happiness++;
        return Map.of("talk", "Мяу");
    }

    @GetMapping("/pet")
    public Map<String, String> pet(@RequestParam(required = false) final Integer count) {
        if (count == null) {
            throw new IncorrectCountException("Параметр count равен null.");
        }
        if (count <= 0) {
            throw new IncorrectCountException("Параметр count имеет отрицательное значение.");
        }
        if (happiness >= 10) {
            throw new HappinessOverflowException(happiness);
        }
        happiness += count;
        return Map.of("talk", "Муррр. ".repeat(count));
    }

    @GetMapping("/happiness")
    public Map<String, Integer> happiness() {
        return Map.of("happiness", happiness);
    }

    @GetMapping("/feed")
    public Map<String, Integer> feed() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Метод /feed ещё не реализован.");
    }

    @ExceptionHandler
    public Map<String, String> handle(final IncorrectCountException e) {
        return Map.of(
                "error", "Ошибка с параметром count.",
                "errorMessage", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHappinessOverflow(final HappinessOverflowException e) {
        return new ErrorResponse(
                "Слишком большое значение [happiness]",
                "Осторожно, вы так избалуете котика! Уровень happinness: " + e.getHappinessLevel()
        );
    }
}