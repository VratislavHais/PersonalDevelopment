package com.vhais.observer;

import com.vhais.display.DisplayElement;
import com.vhais.service.WeatherData;

public class StatisticsDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private float pressure;
	private WeatherData weatherData;

	public StatisticsDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}

	@Override
	public void display() {
		System.out.println(String.format("Temperature: %f°C\nHumidity: %f%%\nPressure: %fPa", temperature, humidity, pressure));
	}
}
