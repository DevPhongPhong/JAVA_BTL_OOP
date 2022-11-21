package com.group5.btl.service;

import java.util.List;

import com.group5.btl.dto.swap.SwapCreate;
import com.group5.btl.dto.swap.SwapInfo;
import com.group5.btl.dto.swap.SwapPreview;
import com.group5.btl.dto.swap.SwapUpdate;
import com.group5.btl.model.Student;
import com.group5.btl.model.Swap;

public interface SwapService {
    Swap getById(int id);

    List<Swap> getAll();

    SwapPreview getPreview(Swap swap);

    SwapInfo getInfo(Swap swap);

    List<SwapPreview> getPreviews(List<Swap> listSwap,int page, int size);
    
    List<SwapPreview> getByCourseId(Integer courseId);
    
    List<SwapPreview> getByUserId(Student student);

    int create(SwapCreate swapCreateDTO);

    void update(SwapUpdate swapUpdate);

    int delete(int id);
}
