package com.group5.btl.dto.swap;

import java.util.List;

import com.group5.btl.dto.user.UserPreview;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SwapWishPreview {
    public int ID;
    public short studyGroup;
    public short practiceGroup;
    public List<JoinSwapPreview> listJoinSwapPreview;
    
}
