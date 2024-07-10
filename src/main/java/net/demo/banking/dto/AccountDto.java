package net.demo.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AccountDto {

    private Long id;
    private String accountNo;
    private String accountHolderName;
    private String phoneNo;
    private String email;
    private double balance;
}
