package org.zxb.common;

import org.zxb.common.exception.ZxbException;

public class Demo {

    public static void main(String[] args) {
        TestException te = new TestException("bar","test");
        System.out.println( ((ZxbException)te).getFieldName());
    }

    public static class TestException extends ZxbException {

        public TestException(String messag) {
            super(messag);
        }

        public TestException(String fileName, String message) {
            this(message);
            this.fieldName = fileName;
        }
    }
}
