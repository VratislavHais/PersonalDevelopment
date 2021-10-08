package com.vhais.my.documents.mydocuments;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.vhais.my.documents.mydocuments.config.MyDocumentsContext;
import com.vhais.my.documents.mydocuments.model.Document;
import com.vhais.my.documents.mydocuments.model.Type;
import com.vhais.my.documents.mydocuments.service.SearchEngine;
import com.vhais.my.documents.mydocuments.service.SearchEngineImpl;
import com.vhais.my.documents.mydocuments.service.ServiceSearchEngine;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MyDocumentsTest {
	private static Logger log = LoggerFactory.getLogger(MyDocumentsTest.class);
	private ClassPathXmlApplicationContext context;
	private SearchEngine engine;
	private Type webType;

	@BeforeEach
	public void setup() {
		context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
		engine = context.getBean(SearchEngine.class);
		webType = context.getBean("webType", Type.class);
	}

	@Test
	public void testMenu() {
		log.debug("About to read the Resource file: menu.txt");
		Resource resource = context.getResource("classpath:META-INF/data/menu.txt");
		try {
			InputStream is = resource.getInputStream();
			Scanner scanner = new Scanner(is);
			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindByType() {
		context.getBean(SearchEngine.class);
		final List<Document> documents = engine.findByType(webType);
		assertThat(documents).as("documents should not be null.").isNotNull();
		assertThat(documents.size()).isEqualTo(1);
		assertThat(webType.getName()).isEqualTo(documents.get(0).getType().getName());
		assertThat(webType.getDesc()).isEqualTo(documents.get(0).getType().getDesc());
		assertThat(webType.getExtension()).isEqualTo(documents.get(0).getType().getExtension());
	}

	@Test
	public void testListAll() {
		engine = context.getBean(SearchEngine.class);
		final List<Document> documents = engine.listAll();
		assertThat(documents).isNotNull();
		assertThat(documents.size()).isEqualTo(4);
	}
}
