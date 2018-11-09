package com.learn.Utils;

@FunctionalInterface
public interface Converter<S,D> {
    D convert(S s);
}
