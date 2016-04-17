package com.taylor.api.common.vo;

import java.io.Serializable;

/***
 * @notes: 查询Vo基类
 * @author: kuta.li
 * @date: 2015年12月30日-上午9:04:19
 */
public class BaseSearchVo implements Serializable{
    
    private static final long serialVersionUID = -6914788276091387834L;

    /**
     * 当前页
     */
    private int currPage = 1;
    
    /**
     * 每页记录数
     */
    private int pageSize;
    
    /**
     * 分页开始位置
     */
    private int offset;
    
    /**
     * 是否只查询一个
     */
    private boolean seleteOne;
    
    /**default constructor*/
    public BaseSearchVo() {
        super();
    }
    
    public BaseSearchVo(int currPage, int pageSize, boolean seleteOne) {
        super();
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.seleteOne = seleteOne;
        if (currPage < 2) {
            this.offset = 0;
        } else {
            this.offset = (currPage-1) * pageSize;
        }
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (currPage < 2) {
            this.offset = 0;
        } else {
            this.offset = (currPage-1) * pageSize;
        }
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isSeleteOne() {
        return seleteOne;
    }

    public void setSeleteOne(boolean seleteOne) {
        this.seleteOne = seleteOne;
    }
    

}
