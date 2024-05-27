package com.example.tabelog_nagoyameshi;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTimeConverter implements Converter<String, Time> {
	@Override
	public Time convert(String source) {
		if(source == null || source.isEmpty()) {
			return null;
		}
		LocalTime localTime = LocalTime.parse(source);
		return Time.valueOf(localTime);
	}

}
