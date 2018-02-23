package syed.shahza.harmonia.backend.dto;

public class LecturerDto {
    private String username;
    private String password;

    public LecturerDto() {
        super();
    }

    private LecturerDto(Builder builder) {
        this();
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Builder aLecturerDto() {
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

        public LecturerDto build() {
            return new LecturerDto(this);
        }
    }
}
