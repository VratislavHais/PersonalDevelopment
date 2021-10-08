package com.vhais.my.documents.mydocuments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vhais.my.documents.mydocuments.data.DocumentDAO;
import com.vhais.my.documents.mydocuments.data.DocumentRepository;
import com.vhais.my.documents.mydocuments.model.Document;
import com.vhais.my.documents.mydocuments.model.Type;
import com.vhais.my.documents.mydocuments.service.SearchEngine;
import com.vhais.my.documents.mydocuments.service.ServiceSearchEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class MyDocumentsContext {
	private Map<String, Document> documents = new HashMap<>();
	private Map<String, Type> types = new HashMap<>();

	@Bean
	public Type webType() {
		return getTypeFromMap("web");
	}

	@Bean
	public SearchEngine engine() {
		ServiceSearchEngine engine = new ServiceSearchEngine();
		engine.setDocumentDAO(documentDAO());
		return engine;
	}

	public MyDocumentsContext() {
		Type type = new Type("PDF", "Portable Document Format", ".pdf");

		Document document = new Document();
		document.setName("Book Template");
		document.setType(type);
		document.setLocation("/home/vhais/personalDevelopment/Spring/MyDocuments/neco.pdf");
		documents.put("doc1", document);
		types.put("pdf", type);

		document = new Document();
		document.setName("Sample Contract");
		document.setType(type);
		document.setLocation("/home/vhais/personalDevelopment/Spring/MyDocuments/Sample.url");
		documents.put("doc2", document);

		type = new Type("NOTE", "Text Notes", ".txt");
		document = new Document();
		document.setName("Clustering with RabbitMQ");
		document.setType(type);
		document.setLocation("/home/vhais/personalDevelopment/Spring/MyDocuments/neco.txt");
		documents.put("doc3", document);
		types.put("note", type);

		type = new Type("WEB", "Web Link", ".url");
		document = new Document();
		document.setName("Pro Spring Security Book");
		document.setType(type);
		document.setLocation("http://apress.com/9781430248187");
		documents.put("doc4", document);
		types.put("web", type);
	}

	private DocumentDAO documentDAO() {
		DocumentRepository documentDAO = new DocumentRepository();
		documentDAO.setDocuments(new ArrayList<>(documents.values()));
		return documentDAO;
	}

	private Document getDocumentFromMap(String documentKey) {
		return documents.get(documentKey);
	}

	private Type getTypeFromMap(String typeKey) {
		return types.get(typeKey);
	}
}
