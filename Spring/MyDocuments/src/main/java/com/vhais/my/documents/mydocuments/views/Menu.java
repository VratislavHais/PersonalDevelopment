package com.vhais.my.documents.mydocuments.views;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Menu {
	private Resource menuFile = null;

	public Resource getMenuFile() {
		return menuFile;
	}

	public void setMenuFile(Resource menuFile) {
		this.menuFile = menuFile;
	}

	public void printMenu() {
		try (InputStream is = getMenuFile().getInputStream();
			 Scanner scanner = new Scanner(is)) {
			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
