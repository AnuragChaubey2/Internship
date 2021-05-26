package com.example.internship;

public class UserHelper {
   String Fname;
    String name;
    String mail;
    String pass;
    public UserHelper(){}

    public UserHelper(String fname, String name, String mail, String pass) {
        this.Fname = fname;
        this.name = name;
        this.mail = mail;
        this.pass = pass;
    }
    public String getFname() {
        return Fname;
    }

    public String getName() {
        return name;
    }

    public String getMaill() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public void setFname(String fname) {
        this.Fname = fname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaill(String maill) {
        this.mail = maill;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
