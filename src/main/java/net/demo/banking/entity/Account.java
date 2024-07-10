package net.demo.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="account_no", unique = true, nullable = false)
    private String accountNo;

    @Column(name="account_holder_name")
    private String accountHolderName;

    @Column(name="phone_no")
    private String phoneNo;

    @Column(name = "email_id", unique = true)
    private String email;

    @Column(name="balance")
    private double balance;
}
