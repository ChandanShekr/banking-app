package net.demo.banking.service.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxTable;
import com.influxdb.query.FluxRecord;

import net.demo.banking.dto.TransactionDto;
import net.demo.banking.dto.mapToTransactionDto;
import net.demo.banking.entity.TransactionDetails;
import net.demo.banking.mapper.TransactionMapper;
import net.demo.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private InfluxDBClient influxDBClient;


    @Override
    public void saveTransactionDetails(TransactionDto detailsDto) {
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        TransactionDetails transactionDetails = TransactionMapper.
                mapToTransaction(detailsDto);
        writeApi.writeMeasurement("Chandan1", "Ethersys", WritePrecision.NS, transactionDetails);
    }

    @Override
    public List<TransactionDto> getTransactionDetails(double transactionId, Instant start, Instant end) {
        return List.of();
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        try {
            String query = "from(bucket: \"Chandan1\")\n" +
                    "  |> range(start: -5h)\n" +
                    "  |> filter(fn: (r) => r[\"_measurement\"] == \"transaction\")\n" +
                    "  |> group(columns: [\"_time\"])";

            List<FluxTable> tables = influxDBClient.getQueryApi().query(query, "Ethersys");
            Map<Instant, TransactionDetails> metricsMap = new HashMap<>();

            for (FluxTable table : tables) {
                for (FluxRecord record : table.getRecords()) {
                    Instant time = record.getTime();
                    TransactionDetails metric = metricsMap.getOrDefault(time, new TransactionDetails());

                    metric.setTime(time);
                    metric.setTransactionStatus(record.getValueByKey("transactionStatus") != null ?
                            String.valueOf(record.getValueByKey("transactionStatus")) : "null");
                    metric.setTransactionType(record.getValueByKey("transactionType") != null ?
                            String.valueOf(record.getValueByKey("transactionType")) : "null");
                    metric.setTransactionAmount(record.getValueByKey("transactionAmount") != null ?
                            Double.parseDouble(String.valueOf(record.getValueByKey("transactionAmount"))) : 0.0);

                    metricsMap.put(time, metric);
                }
            }

            return metricsMap.values().stream()
                    .map(mapToTransactionDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging purposes
            throw new RuntimeException("Error fetching all transactions", e);
        }
    }
}