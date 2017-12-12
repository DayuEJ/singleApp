package com.dayu.singapp.activeandroid;

import java.util.Calendar;

public final class CalendarSerializer extends TypeSerializer {
	public Class<?> getDeserializedType() {
		return Calendar.class;
	}

	public Class<?> getSerializedType() {
		return long.class;
	}

	public Long serialize(Object data) {
		return ((Calendar) data).getTimeInMillis();
	}

	public Calendar deserialize(Object data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis((Long) data);

		return calendar;
	}
}