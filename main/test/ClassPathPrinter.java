package main.test;

import java.net.URL;
import java.net.URLClassLoader;

public class ClassPathPrinter {
    public static void main(String[] args) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        for (URL url : ((URLClassLoader)cl).getURLs()) {
            System.out.println(url.getFile());
        }
    }
}