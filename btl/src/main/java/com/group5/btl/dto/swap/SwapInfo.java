package com.group5.btl.dto.swap;

import java.util.List;
import com.group5.btl.model.SwapWish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwapInfo {
    public int id;
    public List<SwapWishPreview> listSwapWishPreview;
}
