package com.group5.btl.dto.swap;

import java.security.Timestamp;
import com.group5.btl.model.Course;
import com.group5.btl.model.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SwapPreview {
    private int id;
    private Student userId;
    private String createdDate;
    private Course courseId;
}
