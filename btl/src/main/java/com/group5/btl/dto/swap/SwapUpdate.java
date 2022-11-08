package com.group5.btl.dto.swap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SwapUpdate {
    private int swapId;
    private List<Integer> listCourseID;
}
