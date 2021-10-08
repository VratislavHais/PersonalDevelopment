package com.vhais.my.documents.mydocuments;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vhais.my.documents.mydocuments.config.MyDocumentsContext;
import com.vhais.my.documents.mydocuments.model.Document;
import com.vhais.my.documents.mydocuments.model.Type;
import com.vhais.my.documents.mydocuments.service.SearchEngine;

import java.util.List;

public class MyDocumentsBeanConfigurationTest {
	private ApplicationContext context;
	private SearchEngine engine;
	private Type webType;

	@BeforeEach
	public void setup() {
		context = new AnnotationConfigApplicationContext(MyDocumentsContext.class);
		engine = context.getBean(SearchEngine.class);
		webType = context.getBean(Type.class);
	}

	@Test
	public void testFindByType() {
		final List<Document> documents = engine.findByType(webType);
		assertThat(documents).as("documents should not be null.").isNotNull();
		assertThat(documents.size()).isEqualTo(1);
		assertThat(webType.getName()).isEqualTo(documents.get(0).getType().getName());
		assertThat(webType.getDesc()).isEqualTo(documents.get(0).getType().getDesc());
		assertThat(webType.getExtension()).isEqualTo(documents.get(0).getType().getExtension());
	}

	@Test
	public void testListAll() {
		final List<Document> documents = engine.listAll();
		assertThat(documents).isNotNull();
		assertThat(documents.size()).isEqualTo(4);
	}
}
