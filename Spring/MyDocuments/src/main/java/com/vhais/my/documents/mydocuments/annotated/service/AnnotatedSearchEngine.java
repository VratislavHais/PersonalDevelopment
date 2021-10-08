package com.vhais.my.documents.mydocuments.annotated.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vhais.my.documents.mydocuments.data.DocumentDAO;
import com.vhais.my.documents.mydocuments.model.Document;
import com.vhais.my.documents.mydocuments.model.Type;
import com.vhais.my.documents.mydocuments.service.SearchEngine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("engine")
@Scope("prototype")
public class AnnotatedSearchEngine implements SearchEngine {
	@Autowired
	private DocumentDAO documentDAO;

	@Override
	public List<Document> findByType(Type documentType) {
		return listAll().stream().filter(d -> d.getType().equals(documentType)).collect(Collectors.toList());
	}

	@Override
	public List<Document> listAll() {
		return Arrays.asList(documentDAO.getAll());
	}
}
