package model;

import role.Roles;

import java.io.Serializable;

/**
 * The type User.
 */
public class User implements Serializable {
    private String username;
    private String password;
    private Roles role;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param password the password
     * @param role     the role
     */
    public User(String username, String password, Roles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Roles getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Roles role) {
        this.role = role;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
