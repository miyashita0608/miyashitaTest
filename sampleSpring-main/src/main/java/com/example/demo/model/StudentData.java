package com.example.demo.model;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class StudentData {

    private int id = 0;

    @NotBlank(message = "氏を入力してください")
    private String familyName;

    @NotBlank(message = "名を入力してください")
    private String firstName;

    @NotBlank(message = "セイを入力してください")
    private String familyNameKana;

    @NotBlank(message = "メイを入力してください")
    private String firstNameKana;

    @Pattern(regexp = "^(070|080|090)-\\d{4}-\\d{4}$",message="電話番号の形式で入力してください")
    private String tel;

    @Pattern(regexp = "^\\d{3}-\\d{4}$",message="郵便番号の形式で入力してください")
    private String post;

    private String undergraduate;

    private String department;

    public StudentData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String value) {
        this.familyName = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFamilyNameKana() {
        return familyNameKana;
    }

    public void setFamilyNameKana(String value) {
        this.familyNameKana = value;
    }

    public String getFirstNameKana() {
        return firstNameKana;
    }

    public void setFirstNameKana(String value) {
        this.firstNameKana = value;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String value) {
        this.tel = value;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String value) {
        this.post = value;
    }

    public String getUndergraduate() {
        return undergraduate;
    }

    public void setUndergraduate(String value) {
        this.undergraduate = value;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String value) {
        this.department = value;
    }

}
