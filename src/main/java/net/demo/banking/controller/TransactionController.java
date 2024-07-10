package net.demo.banking.controller;

import net.demo.banking.dto.TransactionDto;
import net.demo.banking.service.ReportService;

import net.demo.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ReportService reportService;

    @PostMapping("/add")
    public void addTransactionDetails(@RequestBody TransactionDto metric) {

        transactionService.saveTransactionDetails(metric);
    }

    @GetMapping("/{transactionId}")
    public List<TransactionDto> getTransactionDetails(
            @PathVariable double transactionId,
            @RequestParam Instant start,
            @RequestParam Instant end) {
        return transactionService.getTransactionDetails(transactionId, start, end);
    }

    @GetMapping("/getAll")
    public List<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

   @GetMapping("/{transactionId}/report")
    public ResponseEntity<String> generateTransactionReport(@PathVariable double transactionId, @RequestParam Instant start, @RequestParam Instant end) {
        List<TransactionDto> metrics = transactionService.getTransactionDetails(transactionId, start, end);
        String report;
        try {
            report = reportService.generateTransactionReport(metrics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

       return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=\"report.pdf\"")
                .body(report);
    }
}
