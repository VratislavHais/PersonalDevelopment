package com.vhais;

import com.vhais.observer.CurrentConditionsDisplay;
import com.vhais.observer.StatisticsDisplay;
import com.vhais.service.WeatherData;

public class Main {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();

		CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.3f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
