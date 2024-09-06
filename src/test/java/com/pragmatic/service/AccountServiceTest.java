package com.pragmatic.service;

import static org.junit.jupiter.api.Assertions.*;

import com.pragmatic.model.Account;
import com.pragmatic.model.Transaction;
import com.pragmatic.repository.AccountRepository;
import com.pragmatic.repository.TransactionRepository;
import com.pragmatic.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountsRepoTest;

    @Mock
    private TransactionRepository transactionsRepoTest;

    @InjectMocks
    AccountServiceImpl accountServiceTest;

    @Test
    public void moneyTransferTest () throws IOException {
        Account accountTestFrom = new Account()
                .setBalance(20.0)
                .setId(1);

        Account accountTestTo = new Account()
                .setBalance(0.0)
                .setId(2);

        Transaction transaction = new Transaction()
                .setAccountFromId(2)
                .setAccountToId(1)
                .setAmount(10.0);

        Mockito.when(transactionsRepoTest.createNewTransaction(1,2,10.0)).thenReturn(transaction);
        Transaction transactionTest = accountServiceTest.moneyTransfer(accountTestFrom, accountTestTo, 10.0);

        assertEquals(10.0, accountTestTo.getBalance());
        assertEquals(10.0, accountTestFrom.getBalance());
        assertEquals(10.0, transactionTest.getAmount());
        assertEquals(transaction, transactionTest);
    }

}
