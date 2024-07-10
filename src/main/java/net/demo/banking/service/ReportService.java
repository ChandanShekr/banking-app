package net.demo.banking.service;

import net.demo.banking.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {
    String generateTransactionReport(List<TransactionDto> metrics) throws Exception;
}
