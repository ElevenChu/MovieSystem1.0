package com.movie.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: Hr
 * @BelongsPackage: com.hr.utils
 * @CreateTime: 2020-09-16 14:11
 * @Description: 通用的分页工具类
 */
public class PageUtil<T> {

    private int pageIndex;//当前页码
    private int pageSize; //页面大小
    private int totalcount; //总条数
    private int pageCount; //总页数
    private List<T> records; //分页的数据

    private int numberStart; //开始序号
    private int numberEnd; //结束序号
    private List<Integer> numbers=new ArrayList<Integer>(); //序号

    public PageUtil(int pageIndex, int pageSize, int totalcount, List<T> records) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalcount = totalcount;
        this.records = records;
        //人工计算总页数
        this.pageCount=(this.totalcount%this.pageSize==0)?(this.totalcount/this.pageSize):(this.totalcount/this.pageSize+1);

        //给序号赋值
        if(this.pageCount<=10){
            this.numberStart=1;
            this.numberEnd=this.pageCount;
        }else{
            this.numberStart=this.pageIndex-4;
            this.numberEnd=this.pageIndex+5;
            if(this.numberStart<1){
                this.numberStart=1;
                this.numberEnd=10;
            }
            if(this.numberEnd>this.pageCount){
                this.numberStart=this.pageCount-9;
                this.numberEnd=this.pageCount;
            }
        }
        for(int i=numberStart;i<=numberEnd;i++){
            numbers.add(i);
        }

    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getNumberStart() {
        return numberStart;
    }

    public void setNumberStart(int numberStart) {
        this.numberStart = numberStart;
    }

    public int getNumberEnd() {
        return numberEnd;
    }

    public void setNumberEnd(int numberEnd) {
        this.numberEnd = numberEnd;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
