package javakit.result;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface JavaKitClientResponseCallback<T> {
    void success(T res);
    //TODO: IllegalArgumentException 此异常表明向方法传递了一个不合法或不正确的参数。
    default void failure(IllegalArgumentException e){};
    //TODO: InstantiationException 实例化异常。当试图通过newInstance()方法创建某个类的实例，
    // 而该类是一个抽象类或接口时，抛出该异常。
    default void failure(InstantiationException e){};
    //TODO: IllegalStateException 在不合理或不正确时间内唤醒一方法时出现的异常信息。
    default void failure(IllegalStateException e){}
    default void failure(RuntimeException e){};
    default void failure(InterruptedException e){};
    default void failure(ExecutionException e){};
    default void failure(IOException e){};
    default void failure(Exception e){};
}
