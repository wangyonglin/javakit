package javakit.result;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public  interface ResultCallback<T> {
    void resove(T cls);
    default void reject(InstantiationException e){};
    default void reject(IllegalStateException e){}
    default void reject(RuntimeException e){};
    default void reject(InterruptedException e){};
    default void reject(ExecutionException e){};
    default void reject(IOException e){};
    default void reject(Exception e){};
}
