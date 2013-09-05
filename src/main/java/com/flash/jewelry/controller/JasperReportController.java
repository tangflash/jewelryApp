package com.flash.jewelry.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flash.jewelry.dao.MockDataFactory;


@Controller
public class JasperReportController {
	
	//protected static Logger logger = Logger.getLogger("controller");
	
    /**
     * Retrieves the PDF report file
     * 
     * @return
     */
    @RequestMapping(value = "/getpdfReport", method = RequestMethod.GET)
    public ModelAndView doSalesReportPDF(HttpServletRequest request,
    		HttpServletResponse response) 
		 {
		//logger.debug("Received request to download PDF report");
		
		// Retrieve our data from a mock data provider
		MockDataFactory dataprovider = new MockDataFactory();
		
		// Assign the datasource to an instance of JRDataSource
		// JRDataSource is the datasource that Jasper understands
		// This is basically a wrapper to Java's collection classes
		JRDataSource categoryData  = dataprovider.getCategoriesData();

		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		
		// must have the empty data source!!!
		JREmptyDataSource emptyData = new JREmptyDataSource();
		
		//parameterMap.put("userList", emptyData);
		List list = new ArrayList();
		Country country = new Country();
		country.setName("tjw");
		list.add(country);
		country = new Country();
		country.setName("gkrong");
		list.add(country);
		//country.setLastUpdate(new Date());
		
		String uri = request.getRequestURI();
		String format = uri.substring(uri.lastIndexOf(".") + 1);	
		parameterMap.put("datasource", list);
		parameterMap.put("format", format);	
		//parameterMap.put("categoryData", categoryData);
		parameterMap.put("Country", country);
		return new ModelAndView("simpleReport", parameterMap);		
	}
}
