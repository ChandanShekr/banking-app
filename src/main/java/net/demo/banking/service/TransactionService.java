package net.demo.banking.service;

import net.demo.banking.dto.TransactionDto;

import java.time.Instant;
import java.util.List;

public interface TransactionService {

    void saveTransactionDetails(TransactionDto detailsDto);

    List<TransactionDto> getTransactionDetails(double transactionId, Instant start, Instant end);

    List<TransactionDto> getAllTransactions();
}
