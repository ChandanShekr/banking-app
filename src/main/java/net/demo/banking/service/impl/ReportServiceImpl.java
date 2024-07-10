package net.demo.banking.service.impl;

import net.demo.banking.dto.TransactionDto;
import net.demo.banking.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Override
    public String generateTransactionReport(List<TransactionDto> metrics) throws Exception {

        try{
            File file = ResourceUtils.getFile("classpath:template.jrxml");
            InputStream inputStream = new FileInputStream(file);
            System.out.println(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(metrics);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Chandan1");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            System.out.println(jasperPrint);

            String path = "C:\\Users\\chand\\OneDrive\\Desktop\\banking-app\\output";
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\report.pdf");

            System.out.println("Report Generated in ouput folder");
        }
        catch(JRException e) {
            System.err.println("JasperReports Exception:" + e.getMessage());
            e.printStackTrace();
        }
        catch(IOException e){
            System.err.println("IO Exception:" + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e) {
            System.err.println("Error in exporting report" + e.getMessage());
        }
        return "report";
    }
}