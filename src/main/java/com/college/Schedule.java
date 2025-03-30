package com.college;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/* 
  Колекція, в якій зберігатися документ баз даних MongoDB, що представляє сутність рядку з таблиці з розкладом коледжу.
*/
@Document(collection = "college-schedule")
public class Schedule {
    @Id
    private String id;
    private String studentFirstName;
    private String studentLastName;
    private String teacherFirstName;
    private String teacherLastName;
    private String courseName;
    private String departmentName;
    private String roomNumber;
    private String semester;
    private String year;
    private String startTime;
    private String endTime;

    public Schedule(String studentFirstName, String studentLastName, String teacherFirstName, String teacherLastName, String courseName, String departmentName, String roomNumber, String semester, String year, String startTime, String endTime) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.courseName = courseName;
        this.departmentName = departmentName;
        this.roomNumber = roomNumber;
        this.semester = semester;
        this.year = year;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        return "Schedule {" +
                " id=\"" + id + "\"\n" +
                " studentFirstName=\"" + studentFirstName + "\"\n" +
                " studentLastName=\"" + studentLastName + "\"\n" +
                " teacherFirstName=\"" + teacherFirstName + "\"\n" +
                " teacherLastName=\"" + teacherLastName + "\"\n" +
                " courseName=\"" + courseName + "\"\n" +
                " departmentName=\"" + departmentName + "\"\n" +
                " roomNumber=\"" + roomNumber + "\"\n" +
                " semester=\"" + semester + "\"\n" +
                " year=\"" + year + "\"\n" +
                " startTime=\"" + startTime + "\"\n" +
                " endTime=\"" + endTime + "\"\n" +
                "}";
    }
}