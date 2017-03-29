package com.vasanth.jasper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
 
public class MainController {
 
   public static void main(String[] args) throws JRException, IOException {
 
        // Compile jrxml file.
//	   String reportFileNameSource = context.getRealPath("/reports/" + reportName + ".jrxml");
       JasperReport jasperReport = JasperCompileManager
               .compileReport("E:/Vasanth.JAVA/Mywork/JasperReports/Jasper/WebContent/reports/Invoice_Table_Based.jrxml");
 
       // Parameters for report
       Map<String, Object> parameters = new HashMap<String, Object>();
 
       // DataSource
       // This is simple example, no database.
       // then using empty datasource.
       JRDataSource dataSource = new JREmptyDataSource();
 
       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
               parameters, dataSource);
 
    
       // Make sure the output directory exists.
       File outDir = new File("E:/Vasanth.JAVA/Mywork/JasperReports/Jasper/WebContent/generateReport");
       outDir.mkdirs();
 
       //JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
       DataBeanList DataBeanList = new DataBeanList();
       ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();
       
       JRBeanCollectionDataSource beanColDataSource = new 
    	         JRBeanCollectionDataSource(dataList);
       String sourceFileName="E:/Vasanth.JAVA/Mywork/JasperReports/Jasper/WebContent/reports/DisplayRaw.jrxml";
       JasperCompileManager.compileReport(sourceFileName);
       Map parameters2 = new HashMap();
       try {
          JasperFillManager.fillReportToFile( 
             sourceFileName, parameters, beanColDataSource);
       } catch (JRException e) {
          e.printStackTrace();
       }
       
       // Export to PDF.
       JasperExportManager.exportReportToPdfFile(jasperPrint,
              "E:/Vasanth.JAVA/Mywork/JasperReports/Jasper/WebContent/generateReport/StyledTextReport.pdf");
       // Export to html
       JasperExportManager.exportReportToHtmlFile(jasperPrint,
               "E:/Vasanth.JAVA/Mywork/JasperReports/Jasper/WebContent/generateReport/sample_report.html");
       
       System.out.println("Done!");
   }
}