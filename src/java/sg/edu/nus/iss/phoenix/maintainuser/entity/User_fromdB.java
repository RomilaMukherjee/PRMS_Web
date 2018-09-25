/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.maintainuser.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;

/**
 *
 * @author PrannoySablok
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User_1.findAll", query = "SELECT u FROM User_1 u")
    , @NamedQuery(name = "User_1.findById", query = "SELECT u FROM User_1 u WHERE u.id = :id")
    , @NamedQuery(name = "User_1.findByPassword", query = "SELECT u FROM User_1 u WHERE u.password = :password")
    , @NamedQuery(name = "User_1.findByName", query = "SELECT u FROM User_1 u WHERE u.name = :name")
    , @NamedQuery(name = "User_1.findByRole", query = "SELECT u FROM User_1 u WHERE u.role = :role")})
public class User_fromdB implements Serializable {

    @OneToMany(mappedBy = "assingedBy")
    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "role")
    private String role;
    private ArrayList<Role> roles = new ArrayList<Role>();

    public User_fromdB() {
    }

    public User_fromdB(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        if (!(object instanceof User_fromdB)) {
            return false;
        }
        User_fromdB other = (User_fromdB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public void setAll(String idIn, String passwordIn, String nameIn,
			String roleIn) {
		this.id = idIn;
		this.password = passwordIn;
		this.name = nameIn;
		Role e = new Role(roleIn);
		this.roles.add(e);
	}
    
    /**
	 * hasEqualMapping-method will compare two User instances and return true if
	 * they contain same values in all persistent instance variables. If
	 * hasEqualMapping returns true, it does not mean the objects are the same
	 * instance. However it does mean that in that moment, they are mapped to
	 * the same row in database.
	 */
    public boolean hasEqualMapping(sg.edu.nus.iss.phoenix.maintainuser.entity.User_fromdB valueObject) {

		if (valueObject.getId() != this.id) {
			return (false);
		}
		if (this.password == null) {
			if (valueObject.getPassword() != null)
				return (false);
		} else if (!this.password.equals(valueObject.getPassword())) {
			return (false);
		}
		if (this.name == null) {
			if (valueObject.getName() != null)
				return (false);
		} else if (!this.name.equals(valueObject.getName())) {
			return (false);
		}
		if (this.roles.get(0).getRole() != null) {
			if (valueObject.roles.get(0).getRole() != null)
				return (false);
		} else if (!this.roles.get(0).equals(valueObject.roles.get(0).getRole())) {
			return (false);
		}

		return true;
	}
    
    /**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in console logs.
	 */
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer("toString: ");
		out.append("\nclass User, mapping to table user\n");
		out.append("Persistent attributes: \n");
		out.append("id = " + this.id + "\n");
		out.append("password = " + this.password + "\n");
		out.append("name = " + this.name + "\n");
		out.append("role = " + this.roles.get(0).getRole() + "\n");
		return out.toString();
    }

   
    
}
