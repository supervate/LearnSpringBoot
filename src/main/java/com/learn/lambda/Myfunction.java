package com.learn.lambda;

import java.util.Comparator;

@FunctionalInterface
public interface Myfunction<E> {
    int compare(E o1, E o2);
}
