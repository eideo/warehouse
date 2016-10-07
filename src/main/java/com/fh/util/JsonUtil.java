package com.fh.util;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import  com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {
private	static ObjectMapper mapper;
	
	public static String beanToJson(Object bean)  {
		mapper = new ObjectMapper();  
		String value=null;
		try {
			value = mapper.writeValueAsString(bean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mapper=null;
		}
		return value;
	}
	

	

	 public static JavaType getCollectionType(ObjectMapper mapper,Class<?> collectionClass, Class<?>... elementClasses) {   
         return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
     }  
	
	
	 public static List getListFromJson(String jsonString,Class<?> collectionClass, Class<?>... elementClasses){
		 mapper=new  ObjectMapper();  
		  List ls=null;
		 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			JavaType javaType = getCollectionType(mapper,collectionClass, elementClasses); 
			try {
				ls =  mapper.readValue(jsonString, javaType); 
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				mapper=null;
			}
		 return ls;
		 
		 
	 }
	 
	 
	 
	 
	 
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
	
		

	}
}
