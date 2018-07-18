package com.pedrodeveloper14.ifmsaelsalvador.utils;

public class SignUpData {

    private volatile static SignUpData ourInstance = new SignUpData();

    public synchronized static SignUpData getInstance() {
        if (ourInstance == null) {
            ourInstance = new SignUpData();
        }
        return ourInstance;
    }

    private SignUpData() {
    }


    private String name, email, username, password, college, year, phone;

    public void setFirstStepInfo(String fName, String email) {
        this.name = fName;
        this.email = email;
    }

    public void setSecondStepInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setThirdStepInfo(String collage, String year, String phone) {
        this.college = collage;
        this.year = year;
        this.phone = phone;
    }

    private void setName(String name) {
        this.name = name;
    }


    private void setEmail(String email) {
        this.email = email;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setCollege(String college) {
        this.college = college;
    }

    private void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCollege() {
        return college;
    }

    public String getYear() {
        return year;
    }

    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }
}
