package com.pragmatic.dao.rowmapper;

import com.pragmatic.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Transaction.builder()
                .transactionId(rs.getLong("transaction_id"))
                .sourceAccountId(rs.getLong("source_account_id"))
                .destinationAccountId(rs.getLong("destination_account_id"))
                .amount(rs.getBigDecimal("amount"))
                .actionDate(rs.getDate("action_date"))
                .build();
    }
}
