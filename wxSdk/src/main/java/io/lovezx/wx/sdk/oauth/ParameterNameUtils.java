package io.lovezx.wx.sdk.oauth;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class ParameterNameUtils {  
	
	private static final Map<Class<?>, Map<Method, Boolean>> cache = new ConcurrentHashMap<Class<?>, Map<Method, Boolean>>();
	
  
    /** 
     * 获取指定类指定方法的参数名 
     * 
     * @param clazz 要获取参数名的方法所属的类 
     * @param method 要获取参数名的方法 
     * @return 按参数顺序排列的参数名列表，如果没有参数，则返回null 
     */  
    public static boolean hasOpenIdAttr(Class<?> clazz, final Method method) {  
    	//尝试从缓存中获取
    	Map<Method, Boolean> methodMap = cache.get(clazz);
    	if(methodMap != null){
    		Boolean hasOpenId = methodMap.get(method);
    		if(hasOpenId!=null) return hasOpenId;
    	}else{
    		cache.put(clazz, new ConcurrentHashMap<Method, Boolean>());
    	}
        final Class<?>[] parameterTypes = method.getParameterTypes();  
        if (parameterTypes == null || parameterTypes.length == 0) {  
        	cache.get(clazz).put(method, false);
            return false;  
        }  
        final Type[] types = new Type[parameterTypes.length];  
        for (int i = 0; i < parameterTypes.length; i++) {  
            types[i] = Type.getType(parameterTypes[i]);  
        }  
       // final String[] parameterNames = new String[parameterTypes.length];  
  
        String className = clazz.getName();  
        int lastDotIndex = className.lastIndexOf(".");  
        className = className.substring(lastDotIndex + 1) + ".class";  
        InputStream is = clazz.getResourceAsStream(className);
        
        final int[] hasOpenId = new int[]{0};
        try {  
            ClassReader classReader = new ClassReader(is);  
            
            classReader.accept(new ClassAdapter(new ClassWriter(ClassWriter.COMPUTE_MAXS)) {  
                @Override  
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {  
                    // 只处理指定的方法  
                    Type[] argumentTypes = Type.getArgumentTypes(desc);  
                    if (!method.getName().equals(name) || !Arrays.equals(argumentTypes, types)) {  
                        return null;  
                    }  
                    final int num=argumentTypes.length;
                    return new MethodVisitor() {  
                        @Override  
                        public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {  
                            // 静态方法第一个参数就是方法的参数，如果是实例方法，第一个参数是this  
                        	if (Modifier.isStatic(method.getModifiers())) {
                        		if((num-1)<index)return;
                        	}else{
                        		if(num<index)return;
                        	}
                            if(name.equals("openId")){
                            	hasOpenId[0] = 1;
                            }else{
                            	if(hasOpenId[0] == 1)return;
                            	hasOpenId[0] = 0;
                            }  
                        }

						@Override
						public AnnotationVisitor visitAnnotation(String arg0,
								boolean arg1) {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public AnnotationVisitor visitAnnotationDefault() {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public void visitAttribute(Attribute arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitCode() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitEnd() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitFieldInsn(int arg0, String arg1,
								String arg2, String arg3) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitFrame(int arg0, int arg1,
								Object[] arg2, int arg3, Object[] arg4) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitIincInsn(int arg0, int arg1) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitInsn(int arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitIntInsn(int arg0, int arg1) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitJumpInsn(int arg0, Label arg1) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitLabel(Label arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitLdcInsn(Object arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitLineNumber(int arg0, Label arg1) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitLookupSwitchInsn(Label arg0,
								int[] arg1, Label[] arg2) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitMaxs(int arg0, int arg1) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitMethodInsn(int arg0, String arg1,
								String arg2, String arg3) {
							System.out.println(arg1);
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitMultiANewArrayInsn(String arg0,
								int arg1) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public AnnotationVisitor visitParameterAnnotation(
								int arg0, String arg1, boolean arg2) {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public void visitTableSwitchInsn(int arg0, int arg1,
								Label arg2, Label[] arg3) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitTryCatchBlock(Label arg0, Label arg1,
								Label arg2, String arg3) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitTypeInsn(int arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void visitVarInsn(int arg0, int arg1) {
							// TODO Auto-generated method stub
							
						}  
                    };  
  
                }  
            }, 0);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        cache.get(clazz).put(method, hasOpenId[0]==1);
        return hasOpenId[0]==1;  
    }  
  
}  