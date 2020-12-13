package com.example.blood.Activities.Model;

public class Blood_Group_profile_items {
    private String Blood_bank_Name, Blood_bank_Phone, Blood_bank_BloodGroup, Blood_bank_Division;
public Blood_Group_profile_items(){}
    public Blood_Group_profile_items(String Blood_bank_Name, String Blood_bank_Phone, String Blood_bank_BloodGroup, String Blood_bank_Division) {
        this.Blood_bank_Name = Blood_bank_Name;
        this.Blood_bank_Phone = Blood_bank_Phone;
        this.Blood_bank_BloodGroup = Blood_bank_BloodGroup;
        this.Blood_bank_Division = Blood_bank_Division;
    }

    public String getBlood_bank_Name() {
        return Blood_bank_Name;
    }

    public void setBlood_bank_Name(String blood_bank_Name) {
        Blood_bank_Name = blood_bank_Name;
    }

    public String getBlood_bank_Phone() {
        return Blood_bank_Phone;
    }

    public void setBlood_bank_Phone(String blood_bank_Phone) {
        Blood_bank_Phone = blood_bank_Phone;
    }

    public String getBlood_bank_BloodGroup() {
        return Blood_bank_BloodGroup;
    }

    public void setBlood_bank_BloodGroup(String blood_bank_BloodGroup) {
        Blood_bank_BloodGroup = blood_bank_BloodGroup;
    }

    public String getBlood_bank_Division() {
        return Blood_bank_Division;
    }

    public void setBlood_bank_Division(String blood_bank_Division) {
        Blood_bank_Division = blood_bank_Division;
    }
}
