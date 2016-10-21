package com.fh.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {
	public static void cacheSave(Object key,Object o,String cache_name){

		getCache(cache_name).put(new Element(key,o));	
	
		 
		 
	}

	public static void removeCache(Object key,String cache_name){
		getCache(cache_name).remove(key);	
		 
		 
	}
	
	
	public static Element getCacheObject(Object key,String cache_name){
		return getCache(cache_name).get(key);	
		 
		 
	}
	
	private static Cache getCache(String cache_name) {
		return  CacheManager.getInstance().getCache(cache_name);
		
	}
	/**
	 * 
	 * @param key
	 * @param cache_name
	 * @param elementClasses
	 * @return
	 */
	public static Class<?> getCacheObject(Object key,String cache_name,Class<?>... elementClasses){
		Object object=getCacheObject(key,cache_name).getObjectValue();
		if(object==null){
			return null;
		}
		return (Class<?>)object;	
	}
}
