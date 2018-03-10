package syed.shahza.harmonia.backend.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Moods {
    private List<Mood> moodList;

    private Moods(Builder builder) {
        this.moodList = builder.moodList;
    }
    
    public Moods(List<Mood> moods) {
    	this.moodList = moods;
    }

    public List<Mood> getMoodList() {
        return this.moodList;
    }

    public static Builder aMoodListBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Mood> moodList;

        private Builder() {
            this.moodList = new ArrayList<>();
        }

        private Builder(List<Mood> moods) {
            this.moodList = moods;
        }

        public Builder moodList(List<Mood> moodList) {
            this.moodList.addAll(moodList);
            return this;
        }

        public Moods build() {
            return new Moods(this);
        }
    }
}
