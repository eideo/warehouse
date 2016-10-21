package com.fh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomerDateAndTimeDeserialize extends JsonDeserializer<Date> {

private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//yyyy-MM-dd'T'HH:mm:ssz//"yyyy-MM-dd'T'HH:mm:ssX"
	//sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

    @Override
    public Date deserialize(JsonParser paramJsonParser,
            DeserializationContext paramDeserializationContext)
             {
      
      //  sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            String str = paramJsonParser.getText().trim();
        	System.out.println("this is be invoked time   "+str);
            return sdf.parse(str);
        } catch (Exception e) {
        	System.out.println("error is ");
        
          e.printStackTrace();
        }
        System.out.println("test test");
        return null;
    }


}
