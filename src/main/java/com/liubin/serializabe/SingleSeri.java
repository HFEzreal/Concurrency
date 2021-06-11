package com.liubin.serializabe;

import com.liubin.Concurrency.singleton.EnumMode;
import com.liubin.Concurrency.singleton.HungryMode;
import com.liubin.Concurrency.singleton.LazyMode;

import java.io.*;

/**
 * @description TODO
 * @author liubin
 * @date 21/6/3 14:11
 */
public class SingleSeri {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        singletonIsSerializable(LazyMode.doubleCheckAndVolatile(), LazyMode.doubleCheckAndVolatile());
        singletonIsSerializable(EnumMode.Singleton.INSTANCE.getInstance(), EnumMode.Singleton.INSTANCE.getInstance());
        singletonIsSerializable(EnumMode.Singleton.INSTANCE, EnumMode.Singleton.INSTANCE);
        singletonIsSerializable(HungryMode.getInstance(), HungryMode.getInstance());
    }

    private static <T> void singletonIsSerializable(T t1, T t2) throws IOException, ClassNotFoundException {
        // write object to file
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("tempFile"));
        objectOutputStream.writeObject(t1);
        // read object from file
        File file = new File("tempFile");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        T lazyMode = (T) inputStream.readObject();
        System.out.println(lazyMode);
        System.out.println(t2);
        System.out.println(t2 == lazyMode);

    }
}
