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

    /**
     * Method to save personal info from user
     *
     * @param name  name of the user
     * @param email email of the user
     */
    public void setFirstStepInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Method to save credentials info from user
     *
     * @param username username of the user
     * @param password password of the user
     */
    public void setSecondStepInfo(String username, String password) {
        this.username = username;
        this.password = Encryption.encrypt(password);
    }

    public void setThirdStepInfo(String collage, String year, String phone) {
        this.college = collage;
        this.year = year;
        this.phone = phone;
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

}
