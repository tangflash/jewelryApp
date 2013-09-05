package com.flash.jewelry.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flash.jewelry.dao.CityMapper;

public class MybatisMain {
	
	/**
	 * @param args
	 * @throws IOException
	 */
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {		
		
		/*InputStream inputStream = Resources
				.getResourceAsStream("mybatis-conf.xml");*/
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("servlet-context.xml");
		/*SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);*/		
		//SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			//CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			CityMapper cityMapper = (CityMapper)applicationContext.getBean("cityMapper");
			/*City city = cityMapper.selectCity(1);
			System.out.println(city.getName());
			System.out.println(city.getId());
			System.out.println(city.getLastUpDate());

			city.setName("11");			
			cityMapper.updateCity(city);
			sqlSession.commit();
			System.out.println("update sucessfull");*/
			//cityMapper.deleteCity(1);
			
			/*City city = new City();
			city.setName("GuiLin22");
			city.setCountryId(87);
			city.setLastUpDate(new Date());
			cityMapper.insertCity(city);
			//cityMapper.deleteCity(611);
			sqlSession.commit();*/
			
			List list = new ArrayList(5);
			list.add(1);
			list.add(2);
			//list.add(3);
			Collection<City>  collection = cityMapper.selectCity(list);
			Iterator<City> iterator = collection.iterator();
			while (iterator.hasNext()) {
				City city = iterator.next();
				List<Country> country = city.getCountry();
				/*foreach (Country country : country) {
					System.out.println();
				}*/
				/*for (Object object : country) {
					System.out.println(((Country)object).getName());
				}*/
				System.out.println("cityName:" + city.getName());		
				//System.out.println("countryName:" + country.getName());
			}
			
			//System.out.println(city.getId());
		} finally {
			//sqlSession.close();
		}
	}

}
