package main.java.com.pragmatic.dao;

import main.java.com.pragmatic.model.Account;
import main.java.com.pragmatic.model.Transaction;
import main.java.com.pragmatic.repository.AccountRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class TransactionConverter {
    public static Transaction parseTransaction(String data) {
        String[] values = data.split(",");

        if (values.length != 5) {
            throw new IllegalArgumentException("Invalid user data. Number of csv rows is incorrect");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(Integer.valueOf(values[0]));
        transaction.setAccountFromId(Integer.valueOf(values[1]));
        transaction.setAccountToId(Integer.valueOf(values[2]));
        transaction.setAmount(Double.valueOf(values[3]));
        transaction.setActionDate(Date.from(Instant.ofEpochSecond(Integer.valueOf(values[4]))));

        return transaction;
    }
}
