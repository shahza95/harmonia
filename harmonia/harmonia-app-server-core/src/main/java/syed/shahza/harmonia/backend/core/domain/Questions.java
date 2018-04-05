package syed.shahza.harmonia.backend.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Questions {
    private List<Question> questionList;

    private Questions(Builder builder) {
        this.questionList = builder.questionList;
    }
    
    public Questions(List<Question> questions) {
    	this.questionList = questions;
    }

    public List<Question> getQuestionList() {
        return this.questionList;
    }

    public static Builder aQuestionListBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Question> questionList;

        private Builder() {
            this.questionList = new ArrayList<>();
        }

        private Builder(List<Question> questions) {
            this.questionList = questions;
        }

        public Builder questionList(List<Question> questionList) {
            this.questionList.addAll(questionList);
            return this;
        }

        public Questions build() {
            return new Questions(this);
        }
    }
}
