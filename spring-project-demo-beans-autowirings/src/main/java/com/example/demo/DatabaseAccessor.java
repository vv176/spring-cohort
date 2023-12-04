package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // invoking no args constr new DatabaseAccessor()
public class DatabaseAccessor {

    // ticking time bomb for NPE
    /**private final Logger logger; // hidden dep, constructor based

    @Autowired
    public DatabaseAccessor(Logger l) {
        this.logger = l;
    }

    public void talktoDB() {
        System.out.println("ok talking....");
        logger.log();
    }**/

}
