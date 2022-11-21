package com.group5.btl.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto<T> {
    public int page;
    public int countPage;
    public List<T> listObject;
}
