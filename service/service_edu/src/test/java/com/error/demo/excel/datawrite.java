package com.error.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class datawrite {
    @ExcelProperty(value = "学生编号",index = 0)
    private  Integer sno;

    @ExcelProperty(value = "姓名",index = 1)
    private String sname;
}
