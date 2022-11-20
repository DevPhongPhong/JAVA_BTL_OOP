package com.group5.btl.dto.swap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwapCreateFromView {
    String courseCode;
    Group groupSwap;
    List<Group> swapWish;
}
