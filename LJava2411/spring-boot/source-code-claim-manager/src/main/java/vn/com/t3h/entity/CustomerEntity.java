
package vn.com.t3h.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String bankNumber;
    private String bankName;

    @OneToMany(mappedBy = "customerEntity",cascade = CascadeType.ALL)
    List<ClaimEntity> claims;
    public CustomerEntity() {

    }

    public List<ClaimEntity> getClaims() {
        return claims;
    }

    public void setClaims(List<ClaimEntity> claims) {
        this.claims = claims;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

// Getters and Setters
}
