package com.uticodes.payoneerandroidtest.utils;

import javax.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Results<T> {
    @Nullable
    State results;

    @Nullable
    T data;

    @Nullable
    String message;

    private Results(@Nullable State results, @Nullable T data, @Nullable String message) {
        this.results = results;
        this.data = data;
        this.message = message;
    }

    public static <T> Results<T> success(T data) {
        return new Results<>(State.SUCCESS, data, "Successful");
    }

    public static <T>Results<T> error(String error) {
        return new Results<>(State.ERROR, null, error);
    }

    public enum State {ERROR, SUCCESS}
}



