/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.service.Base64Coder;
import java.io.*;
import java.util.*;
/**
 *
 * @author federico
 */
public class ToString {
    
    public static Object fromString(String s) throws IOException, ClassNotFoundException
    {
        byte [] data = Base64Coder.decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        
        Object o = ois.readObject();
        ois.close();
        return o;
    }
    
    public static String toString(Serializable o) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos= new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return new String( Base64Coder.encode(baos.toByteArray()));
    }
}
