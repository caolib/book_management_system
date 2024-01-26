package com.clb.util;

public class ThreadLocalUtil {
    // ThreadLocal对象
    public static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }


    public static <T> void set(T value) {
        THREAD_LOCAL.set(value);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
