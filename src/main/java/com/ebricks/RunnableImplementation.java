package com.ebricks;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunnableImplementation implements Runnable {

    private Shape s;

    RunnableImplementation(Shape s) {
        this.s = s;
    }

    public void run() {

        s.display();

    }

}

