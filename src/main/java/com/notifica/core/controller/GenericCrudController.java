package com.notifica.core.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.notifica.core.business.GenericCrudBusiness;
import com.notifica.core.utils.PageRequestWrapper;
import com.notifica.core.utils.PageWrapper;
import com.notifica.core.utils.WrapperJsonObject;


public abstract class GenericCrudController<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericCrudController.class);
    public static final String JSON = "application/json";
    
    public abstract GenericCrudBusiness<T> getBusinessClass();

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = { JSON })
    public List<T> pageLoad() {
    	try {
    		return getBusinessClass().listarTodos();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return new ArrayList<T>();
		}
        
    }
  
    @RequestMapping(value = "/listar/pages", method = RequestMethod.POST, produces = { JSON })
    public String paginar(@RequestBody String json) throws JsonProcessingException {
    	
        PageRequestWrapper request = getNewGson().fromJson(json, PageRequestWrapper.class);
        Page<T> pageResult = getBusinessClass().listarTodos(request.getSpecification(), request.getPageable());
        PageWrapper<T> wrapper = new PageWrapper<>(pageResult, "listar/pages");
        
        return new ObjectMapper()
        		.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))
        		.writeValueAsString(wrapper);
        
    }

    @RequestMapping(value = "salvar", method = RequestMethod.POST, produces = { JSON })
    public T salvar(@RequestBody WrapperJsonObject<T> t) {
        return getBusinessClass().salvar(t.getT());
    }

    @RequestMapping(value = "/buscar-por-id", method = RequestMethod.GET, produces = { JSON })
    public T buscar(@RequestParam Integer id) {
        return getBusinessClass().buscar(id);
    }

    @RequestMapping(value = "/deletar", method = RequestMethod.POST)
    public void deletar(@RequestBody WrapperJsonObject<T> t) {
    	getBusinessClass().deletar(t.getT());
    }
    
    public Type getClassType() {
        Type mySuperclass = this.getClass().getGenericSuperclass();
        Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
        return tType;
    }
    
    private Gson getNewGson() {
		return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
				.create();
	}
    
}
