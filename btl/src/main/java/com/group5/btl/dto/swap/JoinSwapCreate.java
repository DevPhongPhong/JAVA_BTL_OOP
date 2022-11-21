package com.group5.btl.dto.swap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinSwapCreate {
	
	private int userId;
	private int swapWishId;
}
