package com.flash.jewelry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;



@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/resources/servlet-context.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class AbstractContextControllerTests {

	@Autowired
	protected WebApplicationContext wac;
	@Autowired
	protected ApplicationContext ac;

}