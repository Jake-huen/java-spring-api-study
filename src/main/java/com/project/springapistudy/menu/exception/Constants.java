package com.project.springapistudy.menu.exception;

public class Constants {

    public enum ExceptionClass {
        MENU("Menu");
        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass(){
            return exceptionClass;
        }

        @Override
        public String toString(){
            return getExceptionClass() + " Exception.";
        }

    }
}
