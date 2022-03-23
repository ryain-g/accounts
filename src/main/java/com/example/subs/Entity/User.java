package com.example.subs.Entity;


import javax.persistence.*;


//configure this class as Entity
@Entity
// create table user in subscriber database with attribute the table columns
@Table(name = "user")
public class User {

    //define the next attribute as id
    @Id
    //make the database  generate the id value automatically
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

//define the attribute as column in the table
    @Column(name="user_name",nullable = false, length = 50)
    private String userName;

    @Column( name= "user_email",nullable = false, length = 500)
    private String userEmail;

    @Column( name= "user_numb",nullable = false, length = 500)
    private double userPhoneNumb;

    @Column( name= "User_Balance",columnDefinition = "double default 0",nullable = false, length = 500)
    private double userBalance;

// create the set and get methods to enable  access to the attribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getUserPhoneNumb() {
        return userPhoneNumb;
    }

    public void setUserPhoneNumb(double userPhoneNumb) {
        this.userPhoneNumb = userPhoneNumb;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

    // create the toString method to  return a string representation of the objects.
    @Override
    public String toString() {
        return "Users{" +
                "userId=" + id +
                ", UserName='" + userName + '\'' +
                ", email='" + userEmail + '\'' +
                ", numb=" + userPhoneNumb +
                ", UserBalance=" + userBalance+
                '}';
    }
}
