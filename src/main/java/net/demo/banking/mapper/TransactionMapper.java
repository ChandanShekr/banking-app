package net.demo.banking.mapper;


import net.demo.banking.dto.TransactionDto;
import net.demo.banking.entity.TransactionDetails;

public class TransactionMapper {
    public static TransactionDto mapToTransactionDto(TransactionDetails transaction) {
        return new TransactionDto(
                transaction.getTransactionId(),
                transaction.getTransactionStatus(),
                transaction.getTransactionType(),
                transaction.getTransactionAmount(),
                transaction.getTime());

    }

    public static TransactionDetails mapToTransaction(TransactionDto transactionDto) {
        return new TransactionDetails(
                transactionDto.getTransactionId(),
                transactionDto.getTransactionStatus(),
                transactionDto.getTransactionType(),
                transactionDto.getTransactionAmount(),
                transactionDto.getTime());
    }
}
