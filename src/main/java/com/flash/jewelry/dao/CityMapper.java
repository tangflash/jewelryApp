package com.flash.jewelry.dao;

import java.util.Collection;
import java.util.List;

import com.flash.jewelry.controller.City;

public interface CityMapper {
	Collection<City> selectCity(List list);
	void updateCity(City city); 
	void deleteCity(int id);
	void insertCity(City city);
}
