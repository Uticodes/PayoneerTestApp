package com.uticodes.payoneerandroidtest.utils;

import javax.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Results<T> {
    @Nullable
    Status status;

    @Nullable
    T data;

    @Nullable
    String message;

    private Results(@Nullable Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Results<T> success(T data) {
        return new Results<>(Status.SUCCESS, data, "Successful");
    }

    public static <T>Results<T> error(String error) {
        return new Results<>(Status.ERROR, null, error);
    }

    public enum Status {ERROR, SUCCESS}
}



