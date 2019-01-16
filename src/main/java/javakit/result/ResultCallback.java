package javakit.result;

public  interface ResultCallback<T> {
    void resove(T cls);
    default void reject(RuntimeException e){};
}
