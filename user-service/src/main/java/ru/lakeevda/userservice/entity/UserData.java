package ru.lakeevda.userservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "user_data", schema = "public", catalog = "shoplist")
@Schema(description = "User's information")
public class UserData {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    @Schema(description = "Identification")
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    @Schema(description = "Name")
    private String name;
    @Basic
    @Column(name = "password", nullable = false, length = -1)
    @Schema(description = "Password")
    private String password;
    @Basic
    @Column(name = "phone", nullable = false)
    @Schema(description = "Phone")
    private Integer phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        UserData userData = (UserData) o;
//
//        if (id != null ? !id.equals(userData.id) : userData.id != null) return false;
//        if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
//        if (password != null ? !password.equals(userData.password) : userData.password != null) return false;
//        if (phone != null ? !phone.equals(userData.phone) : userData.phone != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        result = 31 * result + (phone != null ? phone.hashCode() : 0);
//        return result;
//    }
}
