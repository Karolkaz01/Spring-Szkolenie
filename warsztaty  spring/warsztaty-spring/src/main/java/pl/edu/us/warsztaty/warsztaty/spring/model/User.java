package pl.edu.us.warsztaty.warsztaty.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 20, message = "Number of characters should be between 2 and 20.")

    private String name;

    private String surname;

    @Min(value = 18 , message = "age should be greater or equal 18")
    @Max(value = 70 , message = "age should be lower then 70")
    private Integer age;

    public User(){

    }



    public User(Long id, String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
