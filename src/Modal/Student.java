package Modal;

import java.awt.*;
import java.io.Serializable;

public class Student implements Serializable {
    public static Long INDEX = Long.valueOf(0);
    private Long id;
    private String studentId;
    private String name;
    private Integer age;
    private Gender gender;
    private String address;
    private Double averageMark;

    public Student(String studentId,String name, Integer age, Gender gender, String address, Double averageMark) {
        this.id = Long.valueOf(++INDEX);
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.averageMark = averageMark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public String toString() {
        return "Name = " + this. name + ", Average Mark = " + this.averageMark;
    }
}
