package com.notifica.core.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.Normalizer;

import com.google.gson.Gson;

/**
 * Classe com funcionalidades auxiliares comuns.  
 */
public class Utils {


	public static Integer toInteger(Object campoDoRegistro) {
		 return (campoDoRegistro == null)? null : Integer.valueOf(campoDoRegistro.toString());
	}
	
	/**
	 * Método responsável por decodificar a string passada com o charset (oldCharset)
	 * passado e codificar para o novo charset (newCharset).
	 * @param str
	 * @param oldCharset
	 * @param newCharset
	 * @return String
	 * @throws IOException
	 */
	public static String convertCharset(String str, String oldCharset, String newCharset) {
		
		//TODO ACERTAR A CONVERSÃO DE CHARSET
		
		Charset charsetDecode = Charset.forName(oldCharset);
        Charset defaultCharsetEncode = Charset.forName(newCharset);
		
		ByteBuffer inputBuffer = ByteBuffer.wrap(str.getBytes());

        CharBuffer data = charsetDecode.decode(inputBuffer);

        ByteBuffer outputBuffer = defaultCharsetEncode.encode(data);
        byte[] outputData = outputBuffer.array();

        return new String(outputData);
	}
	
	/**
	 * Java object to java object Gson, and assign to a String
	 * @param obj
	 * @return String
	 */
	public static String convertToJson(Object obj) {
		Gson gson = new Gson();

		gson.toJson(obj);

		return gson.toJson(obj);
	}
	
	/**
	 * Remover acentos.
	 * @param str
	 * @return String
	 */
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
