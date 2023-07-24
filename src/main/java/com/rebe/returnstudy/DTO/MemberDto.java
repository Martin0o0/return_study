package com.rebe.returnstudy.DTO;



public class MemberDto {

    Integer studentId;
    String name;
    String year;
    String club;

    public MemberDto(){

    }

    public MemberDto(Integer studentId, String name, String year, String club) {
        this.studentId = studentId;
        this.name = name;
        this.year = year;
        this.club = club;
    }

    public MemberDto(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }


    @Override
    public String toString() {
        return "MemberDto{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", club='" + club + '\'' +
                '}';
    }
}
