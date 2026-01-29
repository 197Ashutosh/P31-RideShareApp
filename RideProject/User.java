public class User {
    private int user_id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String license;

    public User(String name, String email, String password, String role, String license) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.license = license;
    }

    public int getUser_id() { return user_id; }
    public void setUser_id(int id) { this.user_id = id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getLicense() { return license; }
}
