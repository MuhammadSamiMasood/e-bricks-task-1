package com.ebricks.task1;


import java.util.concurrent.Callable;

public class CallableImplementation implements Callable<String> {

    private Shape s;

    CallableImplementation(Shape s) {
        this.s = s;
    }

    public String call() throws Exception {
        String string = s.display();
        return string;
    }
}

