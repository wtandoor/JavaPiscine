package school21.spring.service.models;

public class User {
    private long identifier;
    private String email;

    public User(long identifier, String email) {
        this.identifier = identifier;
        this.email = email;
    }

    public long getIdentifier() {
        return identifier;
    }

    public String getEmail() {
        return email;
    }
}
