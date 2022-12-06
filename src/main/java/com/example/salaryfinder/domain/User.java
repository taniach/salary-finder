package com.example.salaryfinder.domain;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @Column
    @CsvBindByName(column = "Name")
    private String name;

    @Column
    @CsvBindByName(column = "Salary")
    private Double salary;

    public User() {
    }

    public User(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(salary, user.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }
}
