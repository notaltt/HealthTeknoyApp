package com.example.teknoyhealthapp;

public class User {
    //variables
    private String fullName, classification, address, email, password,
            phoneNumber, timeVisit, temperature, username, lastVisit, recentExposure, symptoms, lastBarcodeDate, barcode, update;
    //constructor
    public User() {
    }

    public User(String fullName, String classification, String address, String email, String password, String phoneNumber, String timeVisit, String temperature, String username, String lastVisit, String recentExposure, String symptoms, String lastBarcodeDate, String barcode, String update) {
        this.fullName = fullName;
        this.classification = classification;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.timeVisit = timeVisit;
        this.temperature = temperature;
        this.username = username;
        this.lastVisit = lastVisit;
        this.recentExposure = recentExposure;
        this.symptoms = symptoms;
        this.lastBarcodeDate = lastBarcodeDate;
        this.barcode = barcode;
        this.update = update;
    }

    public User(String fullName, String email, String password, String phoneNumber, String username) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public User(String fullName, String classification, String address, String email, String phoneNumber, String username, String password) {
        this.fullName = fullName;
        this.classification = classification;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public User(String lastBarcodeDate) {
        this.lastBarcodeDate = lastBarcodeDate;
    }

    //getter and setter
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTimeVisit() {
        return timeVisit;
    }

    public void setTimeVisit(String timeVisit) {
        this.timeVisit = timeVisit;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getRecentExposure() {
        return recentExposure;
    }

    public void setRecentExposure(String recentExposure) {
        this.recentExposure = recentExposure;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getLastBarcodeDate() {
        return lastBarcodeDate;
    }

    public void setLastBarcodeDate(String lastBarcodeDate) {
        this.lastBarcodeDate = lastBarcodeDate;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
