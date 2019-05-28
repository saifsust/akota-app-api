package com.hungry.entities.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter
public class JsonArrayConverter implements AttributeConverter<List<String>, String> {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(JsonArrayConverter.class);

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {

		try {
			return new JSONArray(attribute).toString();
		} catch (NullPointerException e) {
			LOG.error("convertToDatabaseColumn : " + e.getMessage());
		}
		return null;

	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {

		try {

			List<String> json = new ArrayList<String>();
			JSONArray array = new JSONArray(dbData);

			for (int i = 0; i < array.length(); ++i) {
				json.add(array.get(i).toString());
			}
			return json;
		} catch (NullPointerException e) {
			LOG.error("convertToEntityAttribute : " + e.getMessage());
		}
		return null;
	}

}
