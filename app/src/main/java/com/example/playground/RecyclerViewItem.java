package com.example.playground;

public class RecyclerViewItem {

    private int ID;
    private String Login;
    private String Name;
    private String Email;
    private String Role;

    // TEST CODE - REMOVE
    private String title;

    // Default constructor required for Gson
    public RecyclerViewItem() {
    }
    public RecyclerViewItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Getters & setters or ID
    public int getID() {
        return ID;
    }

    public String getLogin() {
        return Login;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getRole() {
        return Role;
    }

    // Setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setRole(String role) {
        Role = role;
    }

    @Override
    public String toString() {
        return "RecyclerViewItem{" +
                "ID=" + ID +
                ", Login='" + Login + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}
