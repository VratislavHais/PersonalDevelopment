package com.vhais.my.documents.mydocuments.annotated.data;

import org.springframework.stereotype.Repository;

import com.vhais.my.documents.mydocuments.data.DocumentDAO;
import com.vhais.my.documents.mydocuments.model.Document;
import com.vhais.my.documents.mydocuments.model.Type;

import java.util.ArrayList;
import java.util.List;

@Repository("documentDAO")
public class AnnotatedDocumentRepository implements DocumentDAO {
	@Override
	public Document[] getAll() {
		return storage();
	}

	private Document[] storage() {
		final List<Document> result = new ArrayList<>();
		final Type type = new Type("PDF", "Portable Document Format", ".pdf");
		Document document = new Document();
		document.setName("Book Template");
		document.setType(type);
		document.setLocation("/home/vhais/personalDevelopment/Spring/MyDocuments/neco.pdf");
		result.add(document);
		document = new Document();
		document.setName("Sample Contract");
		document.setType(type);
		document.setLocation("/home/vhais/personalDevelopment/Spring/MyDocuments/Sample.pdf");
		result.add(document);
		document = new Document();
		document.setName("Clustering with RabbitMQ");
		document.setType(new Type("NOTE", "Text Notes", ".txt"));
		document.setLocation("/home/vhais/personalDevelopment/Spring/MyDocuments/neco.txt");
		result.add(document);
		document = new Document();
		document.setName("Pro Spring Security Book");
		document.setType(new Type("WEB", "Web Link", ".url"));
		document.setLocation("http://www.apress.com/9781430248187");
		result.add(document);
		return result.toArray(new Document[result.size()]);
	}
}
