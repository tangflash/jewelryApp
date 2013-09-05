package com.flash.jewelry.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flash.jewelry.controller.AbstractContextControllerTests;
import com.flash.jewelry.model.Client;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientMapperTest extends AbstractContextControllerTests {

	// private MockMvc mockMvc;
	@Autowired
	private ClientMapper clientMapper;

	@Before
	public void setup() throws Exception {
		// this.mockMvc = webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MateralInfInputController()).build();
		// this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void deleteClient() throws Exception {
		int id = 1;
		clientMapper.deleteClient(id);
		Client client = clientMapper.selectClientById(id);
		assertNull(client);
	}

	@Test
	public void insertClient() {
		Client client = new Client();
		client.setNumber("88");
		client.setName("88");		
		clientMapper.insertClient(client);
		client = clientMapper.selectClientByNumber("88");
		assertEquals("88", client.getNumber());
	}

	@Test
	public void updateClient() {
		Client client = new Client();
		client.setNumber("88");
		client.setName("88");		
		clientMapper.insertClient(client);
		client.setName("99");
		clientMapper.updateClient(client);
		client = clientMapper.selectClientByNumber("88");
		assertEquals("99", client.getName());
	}

	@Test
	public void repeatRowByNumberOnAdd() {
		int expectVal = 1;
		// insert one row
		Client client = new Client();
		client.setId(1);
		client.setNumber("88");
		client.setName("88");		
		clientMapper.insertClient(client);

		// check repeat row count
		client.setId(0);
		client.setNumber("88");
		int actulVal = clientMapper.repeatRowByNumber(client);
		assertEquals(expectVal, actulVal);
	}

	@Test
	public void repeatRowByNumberOnUpdate() {
		int expectVal = 0;
		// insert one row
		Client client = new Client();
		client.setId(1);
		client.setName("W5");
		client.setNumber("88");		
		clientMapper.insertClient(client);

		// check repeat row count
		client.setId(1);
		client.setNumber("99");		
		int actulVal = clientMapper.repeatRowByNumber(client);
		assertEquals(expectVal, actulVal);
	}
	@Test
	public void selectClient(){
		int expectVal = 2;		

		// insert two row data
		Client client = new Client();
		client.setId(1);
		client.setNumber("88");
		client.setName("88");		
		clientMapper.insertClient(client);
		
		client = new Client();
		client.setId(2);
		client.setNumber("99");
		client.setName("99");
		clientMapper.insertClient(client);
		
		Collection<Client> list = clientMapper.selectClient();
		//assertEquals(expectVal, list.size());
		assertTrue(list.size()>=expectVal);
		
	}
	
	@Test
	public void selectClientById(){
		String expectVal = "1";		

		// insert two row data			
		
		Client client2 = clientMapper.selectClientById(1);
		//assertEquals(expectVal, list.size());
		assertEquals(1, client2.getId());
		
	}
	
	@Test
	public void selectClientByNum(){
		String expectVal = "01";		
				
		Client client2 = clientMapper.selectClientByNumber("01");
		//assertEquals(expectVal, list.size());
		assertEquals(expectVal, client2.getNumber());
		
	}
	
	@Test
	public void getClientByNumOrName(){
		String clientNum = "01";
		String clientName = "Íø¼Í";
				
		Client client2 = clientMapper.getClientByNumOrName(clientNum);
		//assertEquals(expectVal, list.size());
		assertEquals(1, client2.getId());
		client2 = clientMapper.getClientByNumOrName(clientName);
		//assertEquals(expectVal, list.size());
		assertEquals(1, client2.getId());
		
	}
}
