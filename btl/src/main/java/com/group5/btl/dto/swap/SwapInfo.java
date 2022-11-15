package com.group5.btl.dto.swap;

import java.util.List;
import com.group5.btl.model.SwapWish;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SwapInfo {
    private int id;
    private List<SwapWish> listSwapWish;
}
