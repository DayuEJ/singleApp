package com.dayu.singapp.activeandroid;

import java.util.Date;

public final class UtilDateSerializer extends TypeSerializer {
	public Class<?> getDeserializedType() {
		return Date.class;
	}

	public Class<?> getSerializedType() {
		return long.class;
	}

	public Long serialize(Object data) {
		if (data == null) {
			return null;
		}

		return ((Date) data).getTime();
	}

	public Date deserialize(Object data) {
		if (data == null) {
			return null;
		}

		return new Date((Long) data);
	}
}