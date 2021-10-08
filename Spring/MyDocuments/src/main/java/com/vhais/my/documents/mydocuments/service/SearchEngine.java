package com.vhais.my.documents.mydocuments.service;

import com.vhais.my.documents.mydocuments.model.Document;
import com.vhais.my.documents.mydocuments.model.Type;

import java.util.List;

public interface SearchEngine {
	List<Document> findByType(Type documentType);
	List<Document> listAll();
}
