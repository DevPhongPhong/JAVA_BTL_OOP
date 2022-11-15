package com.group5.btl.dto.swap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SwapCreate {
    private int courseId;
    private int userId;
    private List<Integer> listCourseWishID;
}
