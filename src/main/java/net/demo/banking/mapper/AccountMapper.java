package net.demo.banking.mapper;

import net.demo.banking.dto.AccountDto;
import net.demo.banking.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(
        accountDto.getId(),
        accountDto.getAccountNo(),
        accountDto.getAccountHolderName(),
        accountDto.getPhoneNo(),
        accountDto.getEmail(),
        accountDto.getBalance()
        );

        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
        account.getId(),
        account.getAccountNo(),
        account.getAccountHolderName(),
        account.getPhoneNo(),
        account.getEmail(),
        account.getBalance()
        );

        return accountDto;
    }
}
