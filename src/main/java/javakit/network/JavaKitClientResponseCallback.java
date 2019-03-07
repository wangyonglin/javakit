package javakit.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public interface JavaKitClientResponseCallback<T> {
    default void success(T res){};
    default void failure(Exception e){};
}