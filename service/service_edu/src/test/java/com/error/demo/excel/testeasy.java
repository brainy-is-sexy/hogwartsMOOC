package com.error.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class testeasy {
    public static void main(String []args){
        String file="D:/Desktop/test1.xlsx";

    }

    private static List<datawrite> getData(){
        List<datawrite> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            datawrite data=new datawrite();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return  list;
    }
}
