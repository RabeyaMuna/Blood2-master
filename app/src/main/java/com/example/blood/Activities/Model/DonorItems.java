package com.example.blood.Activities.Model;

public class DonorItems {

    private String donorName, donorPhone,donorBloodGroup, donorDivision;

    public DonorItems(String donorName, String donorPhone,String donorBloodGroup,String donorDivison) {
        this.donorName = donorName;
        this.donorPhone = donorPhone;
        this.donorBloodGroup = donorBloodGroup;
        this.donorDivision = donorDivison;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public String getDonorBloodGroup() {
        return donorBloodGroup;
    }

    public void setDonorBloodGroup(String donorBloodGroup) {
        this.donorBloodGroup = donorBloodGroup;
    }

    public String getDonorDivision() {
        return donorDivision;
    }

    public void setDonorDivision(String donorDivision) {
        this.donorDivision = donorDivision;
    }
}
