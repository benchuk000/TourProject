package common.classes;

public class User implements  java.io.Serializable{
    private String login;
    private String password;
    private int id;
    private int type;
    private String email;
    private String userName;
    private String userSurname;

    public User(int id, String login, String password, String email, int type,String userName,String userSurname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.type = type;
        this.userName = userName;
        this.userSurname = userSurname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }
}

