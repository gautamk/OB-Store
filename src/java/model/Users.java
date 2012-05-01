/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import libraries.Validation;

/**
 *
 * @author gautam
 */
@Entity
@Table(name = "USERS", catalog = "", schema = "OBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByAddress", query = "SELECT u FROM Users u WHERE u.address = :address"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 300)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE")
    private Integer phone;

    public Users() {
    }

    public Users(String email) {
        this.email = email;
    }

    public Users(String email, String password)throws IllegalArgumentException {
        this.setEmail(email);
        this.setPassword(password);
    }
    
    public Users(String email,String password,String address,String phone)throws IllegalArgumentException, NumberFormatException{
        this.setEmail(email);
        this.setPassword(password);
        this.setAddress(address);        
        this.setPhone(phone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)throws IllegalArgumentException {
        if (Validation.isValidEmail(email)) {
            this.email = email;
        }else{
            throw new IllegalArgumentException("Invalid Email address");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException{
        if (Validation.isValidPassword(password)){
            this.password = password;
        }else{
            throw new IllegalArgumentException("Password must be atleast 6 characters long");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setPhone(String phone)throws NumberFormatException {
        this.setPhone(Integer.parseInt(phone));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Users[ email=" + email + " ]";
    }
}
