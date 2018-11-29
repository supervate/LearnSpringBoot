package com.learn.Stream;

import com.learn.Stream.pojo.User;
import org.hibernate.validator.constraints.EAN;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamLearn {
    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("a");
        wordList.add("b");
        wordList.add("c");
        wordList.add("d");
        Stream<String> strea = wordList.stream();
        Stream<String> strea1 = wordList.parallelStream();
        List<String> output = wordList.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());
        output.forEach(System.out::println);
    }

    public static List<User> packageData(){
        List<User> users = new ArrayList();
        users.stream();
        return users;
    }
}

