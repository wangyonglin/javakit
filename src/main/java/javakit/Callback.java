package javakit;

public interface Callback<T> {
    void resove(T cls);
    default void reject(Exception e){};
}
