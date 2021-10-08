package com.vhais.my.documents.mydocuments.data;

import com.vhais.my.documents.mydocuments.model.Type;

import java.util.Map;

public class TypeDataRepository implements TypeDataDAO {
	private Map<String, Type> types = null;

	public Map<String, Type> getTypes() {
		return types;
	}

	public void setTypes(Map<String, Type> types) {
		this.types = types;
	}

	@Override
	public Type[] getAll() {
		return types.values().toArray(new Type[types.values().size()]);
	}

	@Override
	public Type findById(String id) {
		return types.get(id);
	}
}
