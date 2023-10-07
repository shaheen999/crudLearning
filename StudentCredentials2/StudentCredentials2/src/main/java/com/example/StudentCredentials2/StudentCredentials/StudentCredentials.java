package com.example.StudentCredentials2.StudentCredentials;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "student")
public class StudentCredentials {
    @Id
    private String sid;
    private String sname;
    private String sclass;
    private String section;
    private String srollNo;
    private String fatherName;
    private String sphonenumber;
    private String susername;

}


