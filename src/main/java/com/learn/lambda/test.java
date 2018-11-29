package com.learn.lambda;

public class test {
    public static void main(String[] args) {
        new test().tests();
    }
    public void tests(){
        String[] ss = new String[]{"absssc","adc","aaaa"};
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(this.toString());
            }

            @Override
            public String toString() {
                return super.toString()+"rrr";
            }
        });
        t1.run();
        Thread t2 = new Thread(()-> System.out.println(this.toString()));
        t2.run();
    }
    @Override
    public String toString() {
        return super.toString()+"aaa";
    }
}
