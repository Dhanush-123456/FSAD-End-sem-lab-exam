package com.klef.fsad.exam;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_account")
public class CustomerAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Column(name = "description", length = 255)
    private String description;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date date;
    
    @Column(name = "status", length = 50)
    private String status;

    // Default constructor is required by Hibernate
    public CustomerAccount() {
    }

    public CustomerAccount(String name, String description, Date date, String status) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    // Getters and Setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomerAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
