package com.example.demo.model;

import org.hibernate.validator.constraints.NotBlank;

public class ScoreData {
    private String nengetsu;

    private int scoreSum;

    private double scoreAvg;

    private double scoreAvg_round;

    private int student_id;

    private String studentName;

    private String testDate;

    private String subject;

    private int score;

    public ScoreData() {
    }

    public String getNengetsu() {
        return nengetsu;
    }

    public void setNengetsu(String value) {
        this.nengetsu = value;
    }

    public int getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(int value) {
        this.scoreSum = value;
    }

    public double getScoreAvg() {
        return scoreAvg;
    }

    public void setScoreAvg(double value) {
        this.scoreAvg = value;
    }

    public double getScoreAvg_round() {
        return scoreAvg_round;
    }

    public void setScoreAvg_round(double value) {
        this.scoreAvg_round = value;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int value) {
        this.score = value;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int value) {
        this.student_id = value;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String value) {
        this.studentName = value;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String value) {
        this.testDate = value;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String value) {
        this.subject = value;
    }

}
