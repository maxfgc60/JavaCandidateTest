package com.stepstone.quiz.data;


import java.util.Objects;

public class Question {
    private final String id;
    private final String type;
    private final String text;
    private final String answer;

    public Question(String id,
                    String type,
                    String text,
                    String answer) {
        this.id = id;
        this.type = type;
        this.text = text;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id.equals(question.id) && type.equals(question.type) && text.equals(question.text) && answer.equals(question.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, text, answer);
    }
}
