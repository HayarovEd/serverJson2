package model;

public class Model {
    String name;
    String password;
    String jwt;
    String message;

    public Model(String name, String password) {
        this.name = name;
        this.password = password;

    }

    /*public Model(String name, String jwt) {
        this.name = name;
        this.jwt = jwt;
    }*/

    public Model(String jwt) {
        this.jwt = jwt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

