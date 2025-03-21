package com.t3h.insuranceclaim.dto;

public class CustomerDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    
    // Getters and setters for each field
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
}
