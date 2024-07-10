package net.demo.banking.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    public double  transactionId;
    public String transactionStatus;
    public String transactionType;
    public double transactionAmount;
    public Instant time;
}
