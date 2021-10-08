package com.vhais.my.documents.mydocuments.service;

import com.vhais.my.documents.mydocuments.model.Document;
import com.vhais.my.documents.mydocuments.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchEngineImpl implements SearchEngine {
	@Override
	public List<Document> findByType(Type documentType) {
		return storage().stream().filter(d -> d.getType().equals(documentType)).collect(Collectors.toList());
	}

	@Override
	public List<Document> listAll() {
		return storage();
	}

	private List<Document> storage() {
		final List<Document> result = new ArrayList<>();
		final Type type = new Type("PDF", "Portable Document Format", ".pdf");
		final Document document = new Document();
		document.setName("Book Template");
		document.setType(type);
		document.setLocation("/home/vhais/personalDevelopment/Spring/MyDocuments/BookTemplate.pdf");
		final Document document2 = new Document();
		document2.setName("neco");
		document2.setType(new Type("WEB", "Web Link", ".url"));
		document2.setLocation("/home/vhais/personalDevelopment/Spring/MyDocuments/neco.url");
		result.add(document);
		result.add(document2);
		return result;
	}
}
