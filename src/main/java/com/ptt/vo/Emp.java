package com.ptt.vo;

import java.util.Date;

public class Emp {
    private String username;//用户名
    private String name;//姓名
    private String password;//密码
    private String sex;//性别
    private Date birthday;//出生日期
    private Date hire_date;//入职日期
    private String position;//职位
    private String qualification;//学历
    private String professional;//专业
    private String experience;//经历
    private String flag;//身份标志
    private String super_id;//上级编号

    public Emp() {
    }

    public Emp(String username, String name, String password, String sex, Date birthday, Date hire_date, String position, String qualification, String professional, String experience, String flag, String super_id) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.hire_date = hire_date;
        this.position = position;
        this.qualification = qualification;
        this.professional = professional;
        this.experience = experience;
        this.flag = flag;
        this.super_id = super_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSuper_id() {
        return super_id;
    }

    public void setSuper_id(String super_id) {
        this.super_id = super_id;
    }
}
