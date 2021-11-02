package com.ddang.auction.items.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PageCriteria {
    private Integer currentPageNum;
    private Integer recordsPerPage;
    private Integer totalRecords;
    private Integer pageSize;

    public int getStartRecord(){
        return (currentPageNum - 1) * recordsPerPage;
    }
    public int getStartPage(){
        return (currentPageNum / pageSize) * pageSize + 1;
    }
    public int getEndPage(){
        if(getMaxPage() < (currentPageNum / pageSize + 1) * pageSize){
            return getMaxPage();
        }
        return (currentPageNum / pageSize + 1) * pageSize;
    }
    public int getMaxPage(){
        if(totalRecords%pageSize==0){
            return totalRecords/pageSize;
        }
        return (totalRecords/pageSize + 1);
    }
}
