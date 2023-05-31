package com.example.lifer.Data;

public class Contact {
    private String name;
    private String phoneNumber;
    private String address;

    public Contact(String name, String phoneNumber,String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
