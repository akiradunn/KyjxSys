package com.brave.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**由于Redis不支持直接存储自定义的对象,所以要采用序列化的方式存储自定义对象,此类就是为了此过程而创建的
 * @author dunndunndunn
 *
 */
public class SerializableUtil implements Serializable{
	/**
	 * 序列化和反序列化集合自定义对象
	 */
	private static final long serialVersionUID = 1L;

	/**序列化对象
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj){
		ObjectOutputStream objectOutputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytes = byteArrayOutputStream.toByteArray();
		return bytes;
	}
	
	/**反序列化
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
        	byteArrayInputStream = new ByteArrayInputStream(bytes);
        	objectInputStream = new ObjectInputStream(byteArrayInputStream);
        	return objectInputStream.readObject();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return null;
    }
}
