package net.demo.banking.entity;


import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Measurement(name = "transaction")
public class TransactionDetails {

    @Column
    private Double transactionId;

    @Column(tag=true)
    private String transactionStatus;

    @Column(tag=true)
    private String transactionType;

    @Column
    private Double transactionAmount;

    @Column(timestamp = true)
    private Instant time;

}
