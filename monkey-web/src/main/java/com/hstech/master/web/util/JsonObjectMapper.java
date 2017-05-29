package com.hstech.master.web.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1862207557985441822L;

	public JsonObjectMapper() {
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		setSerializationInclusion(JsonInclude.Include.NON_NULL);
		disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
		configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
	}
}
