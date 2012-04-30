/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gautam
 */
@Entity
@Table(name = "SHIPPED", catalog = "", schema = "OBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shipped.findAll", query = "SELECT s FROM Shipped s"),
    @NamedQuery(name = "Shipped.findById", query = "SELECT s FROM Shipped s WHERE s.id = :id"),
    @NamedQuery(name = "Shipped.findByEmail", query = "SELECT s FROM Shipped s WHERE s.email = :email"),
    @NamedQuery(name = "Shipped.findByAddress", query = "SELECT s FROM Shipped s WHERE s.address = :address"),
    @NamedQuery(name = "Shipped.findByPhone", query = "SELECT s FROM Shipped s WHERE s.phone = :phone"),
    @NamedQuery(name = "Shipped.findByTimestamp", query = "SELECT s FROM Shipped s WHERE s.timestamp = :timestamp"),
    @NamedQuery(name = "Shipped.findByStatus", query = "SELECT s FROM Shipped s WHERE s.status = :status")})
public class Shipped implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 300)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE")
    private Integer phone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "STATUS")
    private String status;

    public Shipped() {
    }

    public Shipped(Integer id) {
        this.id = id;
    }

    public Shipped(Integer id, Date timestamp, String status) {
        this.id = id;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shipped)) {
            return false;
        }
        Shipped other = (Shipped) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Shipped[ id=" + id + " ]";
    }
    
}
