package com.vasanth.jasper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperPrint {


		@SuppressWarnings("unchecked")
		   public static void main(String[] args) throws JRException {
		      String sourceFileName =
		      "E:/Vasanth.JAVA/Mywork/JasperReports/Jasper/WebContent/reports/template.jasper";

		      DataBeanList DataBeanList = new DataBeanList();
		      ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();

		      JRBeanCollectionDataSource beanColDataSource =
		      new JRBeanCollectionDataSource(dataList);

		      Map parameters = new HashMap();
		      /**
		       * Passing ReportTitle and Author as parameters
		       */
		      parameters.put("ReportTitle", "List of Contacts");
		      parameters.put("Author", "Prepared By Manisha");

		      try {
		    	  String jasperPrint=JasperFillManager.fillReportToFile(
		         sourceFileName, parameters, beanColDataSource);
		    	  
		    	  JasperExportManager.exportReportToPdfFile(jasperPrint,
		                  "E:/Vasanth.JAVA/Mywork/JasperReports/Jasper/WebContent/generateReport/template.pdf");
		           
		      } catch (JRException e) {
		         e.printStackTrace();
		      }
		      System.out.println("Done::::!");
		   }
	}


