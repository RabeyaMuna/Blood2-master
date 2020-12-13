package com.example.blood.Activities.Model;

import androidx.annotation.NonNull;

public class UserData {

    private String Name, Email, Contact, Address,age,password,BloodGroup,Gender,  Division,postID;

    public UserData() {

    }
    public UserData(String name,String contact,String bloodGroup,String locaton,String division){
        this.Name=name;
        this.Contact=contact;
        this.Address=locaton;
        this.Division=division;
        this.BloodGroup=bloodGroup;
    }
public UserData(String id,String name,String phone,String locaton,String blood,String division){
        this.Name=name;
        this.postID=id;
    this.Contact=phone;
    this.Address=locaton;
    this.Division=division;
    this.BloodGroup=blood;
}


    public UserData(String name, String emailID, String passw, String Age, String Address, String phoneNo, String Gender, String BloodGroup, String Division){
        this.Name=name;
        this.Email=emailID;
        this.Contact=phoneNo;
        this.Address=Address;
        this.Gender=Gender;
        this.password=passw;
        this.age=Age;
        this.BloodGroup=BloodGroup;
        this.Division=Division;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        this.Division = division;
    }

    public String getName() {
        return Name;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.BloodGroup = bloodGroup;
    }

    public String getEmail() {
        return Email;
    }
    public String getPassword() {
        return password;
    }
    public String getGender() {
        return Gender;
    }

    public void setName(String name) { this.Name = name; }

    public void setEmail(String email) {
        this.Email = email;
    }


    public void setAge(String age) {
        this.Gender = age;
    }
   public  String getAge(){
        return age;
   }

    public void setGender(String gender) {
        this.Gender = gender;
    }


}
