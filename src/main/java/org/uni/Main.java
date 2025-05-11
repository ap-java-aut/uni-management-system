package org.uni;

import org.uni.cli.CLI;
import org.uni.framework.SingletonSessionFactory;

public class Main {
    public static void main(String[] args) {
        CLI ci = new CLI();
        ci.start();

        SingletonSessionFactory.shutdown();
    }
}