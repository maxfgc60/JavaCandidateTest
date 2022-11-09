package com.stepstone.quiz.service.result;

/**
 * Represents the outcome of the operation
 */
public abstract class Result<T> {

    /**
     * Successful operation result
     */
    public static class Ok<T> extends Result<T> {
        private final T data;

        public Ok(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    /**
     * Operation error
     */
    public static class Error<T> extends Result<T> {
        private final ErrorType type;
        private final String message;

        public Error(ErrorType type, String message) {
            this.type = type;
            this.message = message;
        }

        public ErrorType getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }
    }
}
