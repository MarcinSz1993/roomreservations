package com.example.roomreservations.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataForTransferRequest {
    private String accountNumber;

    private double amount;

    private String transactionType;

}
