package com.cookie.pojo;



public class BasePageParam {

    private Integer pageNum;
    private Integer pageSize;
    private String tokenAccess;

    public BasePageParam() {
        this.pageNum = 1;
        this.pageSize = 20;
    }

    public String getTokenAccess() {
        return tokenAccess;
    }

    public void setTokenAccess(String tokenAccess) {
        this.tokenAccess = tokenAccess;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append(",\"pageNum\":")
                .append(pageNum);
        sb.append(",\"pageSize\":")
                .append(pageSize);
        sb.append(",\"tokenAccess\":\"")
                .append(tokenAccess).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
