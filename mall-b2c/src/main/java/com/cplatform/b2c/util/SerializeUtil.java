package com.cplatform.b2c.util;

import java.io.*;

public class SerializeUtil {

    public static byte[] serialize(Serializable object) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(object);
            return bos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            // ignore
        } finally {
            try {
                out.close();
            } catch (Exception ex) {

            }
            closeQuietly(bos);
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            return in.readObject();
        } finally {
            closeQuietly(bis);
            try {
                in.close();
            } catch (Exception ex) {

            }
        }
    }


    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
}