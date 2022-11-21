package com.group5.btl.dto.swap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinSwapManage {
	private int id;
	private String userName;
	private String courseName;
	private int practiceGroup;
	private int studyGroup;
	private int practiceGroupWish;
	private int studyGroupWish;
}
