package com.vhais.my.documents.mydocuments.data;

import com.vhais.my.documents.mydocuments.model.Document;

import java.util.List;

public class DocumentRepository implements DocumentDAO {
	private List<Document> documents = null;

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	@Override
	public Document[] getAll() {
		return documents.toArray(new Document[documents.size()]);
	}
}
