package com.group5.btl.dto.swap;

import com.group5.btl.model.Course;
import com.group5.btl.model.Student;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SwapPreview {
    public int id;
    public String userName;
    public String createdDate;
    public String courseCode;
    public String courseName;
    public Short studyGroup;
    public Short practiceGroup;
}
