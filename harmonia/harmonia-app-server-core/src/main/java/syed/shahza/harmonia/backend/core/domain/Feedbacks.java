package syed.shahza.harmonia.backend.core.domain;

import java.util.ArrayList;
import java.util.List;

//Custom high level object of multiple Feedback DAOs
public class Feedbacks {
    private List<Feedback> feedbackList;

    private Feedbacks(Builder builder) {
        this.feedbackList = builder.feedbackList;
    }
    
    public Feedbacks(List<Feedback> feedbacks) {
    	this.feedbackList = feedbacks;
    }

    public List<Feedback> getFeedbackList() {
        return this.feedbackList;
    }

    public static Builder aFeedbackListBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Feedback> feedbackList;

        private Builder() {
            this.feedbackList = new ArrayList<>();
        }

        private Builder(List<Feedback> feedbacks) {
            this.feedbackList = feedbacks;
        }

        public Builder feedbackList(List<Feedback> feedbackList) {
            this.feedbackList.addAll(feedbackList);
            return this;
        }

        public Feedbacks build() {
            return new Feedbacks(this);
        }
    }
}
