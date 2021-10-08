package com.vhais.my.documents.mydocuments.data;

import com.vhais.my.documents.mydocuments.model.Type;

public interface TypeDataDAO {
	Type[] getAll();
	Type findById(String id);
}
