package com.vhais.my.documents.mydocuments.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vhais.my.documents.mydocuments.data.DocumentDAO;
import com.vhais.my.documents.mydocuments.model.Document;
import com.vhais.my.documents.mydocuments.model.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceSearchEngine implements SearchEngine {
	private static final Logger log = LoggerFactory.getLogger(ServiceSearchEngine.class);
	private DocumentDAO documentDAO;

	public ServiceSearchEngine() {
		if (log.isDebugEnabled()) log.debug("ServiceSearchEngine created: " + this);
	}

	public DocumentDAO getDocumentDAO() {
		return documentDAO;
	}

	public void setDocumentDAO(DocumentDAO documentDAO) {
		if (log.isDebugEnabled()) log.debug("Document DAO set: " + documentDAO);
		this.documentDAO = documentDAO;
	}

	@Override
	public List<Document> findByType(Type documentType) {
		return listAll().stream().filter(d -> d.getType().equals(documentType)).collect(Collectors.toList());
	}

	@Override
	public List<Document> listAll() {
		return Arrays.asList(documentDAO.getAll());
	}
}
