package com.dayu.singapp.activeandroid;

import java.io.File;
public final class FileSerializer extends TypeSerializer {
	public Class<?> getDeserializedType() {
		return File.class;
	}

	public Class<?> getSerializedType() {
		return String.class;
	}

	public String serialize(Object data) {
		if (data == null) {
			return null;
		}

		return ((File) data).toString();
	}

	public File deserialize(Object data) {
		if (data == null) {
			return null;
		}

		return new File((String) data);
	}
}
