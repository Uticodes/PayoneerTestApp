package com.uticodes.payoneerandroidtest.remote.model;

import lombok.Data;

@Data
public class Payment {
    private String reference;
    private int amount;
    private String currency;
}
