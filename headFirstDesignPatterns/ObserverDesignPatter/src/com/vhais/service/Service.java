package com.vhais.service;

import com.vhais.observer.Observer;

public interface Service {
	void registerObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers();
}
