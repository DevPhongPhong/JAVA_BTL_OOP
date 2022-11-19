package com.group5.btl.service;

import com.group5.btl.dto.swap.JoinSwapPreview;
import com.group5.btl.model.JoinSwap;
import com.group5.btl.model.Student;
import com.group5.btl.model.SwapWish;

public interface JoinSwapService {
    JoinSwap GetByID(int id);
    JoinSwap GetByStudentAndSwapWish(int stuId, int swID);
    JoinSwapPreview GetPreview(JoinSwap js);
    void CreateJoinSwap(SwapWish sw, Student s);
    void DeleteJoinSwap(JoinSwap js);
}
