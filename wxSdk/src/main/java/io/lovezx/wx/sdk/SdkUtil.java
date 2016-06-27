package io.lovezx.wx.sdk;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

public class SdkUtil {
	
	public static <T> List<T> createEmptyList(Class<T> clazz){
		return new EmptyList<T>();
	}
	
	private static class EmptyList<T> extends AbstractList<T>
	implements RandomAccess, Serializable{
    
		private static final long serialVersionUID = 1L;
      
		@Override
		public T get(int index) {
			return null;   
		}

		@Override
		public int size() {
			return 0;
		}
		
	}
}
