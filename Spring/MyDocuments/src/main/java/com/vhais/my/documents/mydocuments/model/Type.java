package com.vhais.my.documents.mydocuments.model;

import java.util.Objects;

public class Type {
	private String name;
	private String desc;
	private String extension;

	public Type(String name, String desc, String extension) {
		this.name = name;
		this.desc = desc;
		this.extension = extension;
	}

	public Type() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Type type = (Type) o;
		return name.equals(type.name) &&
				extension.equals(type.extension);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, desc, extension);
	}
}
