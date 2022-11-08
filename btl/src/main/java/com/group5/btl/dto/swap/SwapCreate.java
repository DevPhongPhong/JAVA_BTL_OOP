package com.group5.btl.dto.swap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SwapCreate {
    private int courseId;
    private int userId;
    private List<Integer> listCourseID;
}
