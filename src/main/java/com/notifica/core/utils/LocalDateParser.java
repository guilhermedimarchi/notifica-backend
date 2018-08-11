package com.notifica.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LocalDateParser {
	public static LocalDate parseString(String date){
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static Date parseJavaScriptDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		return sdf.parse(date);
	}

	public static Gson getNewGson() {
		return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
				.create();
	}
	public static LocalDate tryParse(String date) throws Exception{
		try{
			return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		}catch (Exception e) {
			try {
				return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
			} catch (Exception e1) {
				try {
					return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
				} catch (Exception e2) {
					try {
						return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
					} catch (Exception e3) {				
						try {
							return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						} catch (Exception e4) {				
							throw new Exception("Não foi possivel formatar a data", e4);
						}
					}
				}
			}
		}
	}

	public static Date tryParseToDate(String date) throws Exception{
		try{
			return Date.from(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(ZoneId.systemDefault()).toInstant());

		}catch (Exception e) {
			try {
				return Date.from(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atStartOfDay(ZoneId.systemDefault()).toInstant());
			} catch (Exception e1) {
				try {				
					return Date.from(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")).atStartOfDay(ZoneId.systemDefault()).toInstant());
				} catch (Exception e2) {
					try {
						return Date.from(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).atStartOfDay(ZoneId.systemDefault()).toInstant());
					} catch (Exception e3) {				
						try {
							return Date.from(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.systemDefault()).toInstant());
						} catch (Exception e4) {				
							throw new Exception("Não foi possivel formatar a data", e4);
						}
					}
				}
			}
		}
	}

}
