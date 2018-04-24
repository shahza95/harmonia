package syed.shahza.harmonia.backend.core.domain;

//POJO for Lecturer object to use for business logic
public class Lecturer {
    private final String username;
    private final String password;

    private Lecturer(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public static Builder aLecturer() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Lecturer build() {
            return new Lecturer(this);
        }
    }
}