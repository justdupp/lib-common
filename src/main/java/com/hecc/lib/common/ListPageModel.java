package com.hecc.lib.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuhoujun
 * @description: 列表分页
 * @date: Created In 下午10:06 on 2018/2/27.
 */
public class ListPageModel {

    /**
     * 当前页
     */
    private int page = 1;
    /**
     * 总页数
     */
    public int totalPages = 0;
    /**
     * 每页多少条数据
     */
    private int pageRecorders;
    /**
     * 总数据数
     */
    private int totalRows = 0;
    /**
     * 每页的起始数
     */
    private int pageStartRow = 0;
    /**
     * 每页显示数据的终止数
     */
    private int pageEndRow = 0;
    /**
     * 是否有下一页
     */
    private boolean hasNextPage = false;
    /**
     * 是否有前一页
     */
    private boolean hasPreviousPage = false;
    /**
     * 列表
     */
    private List list;

    /**
     * 通过对象集，记录总数划分
     *
     * @param list
     * @param pageRecorders
     */
    public ListPageModel(List list, int pageRecorders) {
        init(list, pageRecorders);
    }

    /**
     * 初始化list
     *
     * @param list
     * @param pageRecorders
     */
    public void init(List list, int pageRecorders) {
        this.pageRecorders = pageRecorders;
        this.list = list;
        totalRows = list.size();
        hasPreviousPage = false;
        if ((totalRows % pageRecorders) == 0) {
            totalPages = totalRows / pageRecorders;
        } else {
            totalPages = totalRows / pageRecorders + 1;
        }
        if (page >= totalPages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
        if (totalRows < pageRecorders) {
            this.pageStartRow = 0;
            this.pageEndRow = totalRows;
        } else {
            this.pageStartRow = 0;
            this.pageEndRow = pageRecorders;
        }
    }

    public String toString(int temp) {
        String str = Integer.toString(temp);
        return str;
    }

    /**
     * 处理分页
     */
    private void disposePage() {
        if (page == 0) {
            page = 1;
        }
        if ((page - 1) > 0) {
            hasPreviousPage = true;
        } else {
            hasPreviousPage = false;
        }
        if (page >= totalPages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
    }

    /**
     * 获取第几页的内容
     *
     * @param page
     * @return
     */
    public List getObjects(int page) {
        if (page == 0) {
            this.setPage(1);
        } else {
            this.setPage(page);
        }
        this.disposePage();
        /**
         * 判断是否为最后一页
         */
        if (page * pageRecorders < totalRows) {
            pageEndRow = page * pageRecorders;
            pageStartRow = pageEndRow - pageRecorders;
        } else {
            pageEndRow = totalRows;
            pageStartRow = pageRecorders * (totalPages - 1);
        }
        List objects = null;
        if (!list.isEmpty()) {
            objects = list.subList(pageStartRow, pageEndRow);
        }
        return objects;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public List getList() {
        return list;
    }


    public void setList(List list) {
        this.list = list;
    }


    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page;
    }


    public int getPageEndRow() {
        return pageEndRow;
    }


    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }


    public int getPageRecorders() {
        return pageRecorders;
    }


    public void setPageRecorders(int pageRecorders) {
        this.pageRecorders = pageRecorders;
    }


    public int getPageStartRow() {
        return pageStartRow;
    }


    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }


    public int getTotalPages() {
        return totalPages;
    }


    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    public int getTotalRows() {
        return totalRows;
    }


    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }


    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public static void main(String args[]) {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < 15; i++) {
            list.add(i + "");
        }
        ListPageModel pm = new ListPageModel(list, 10);

        List sublist = pm.getObjects(2);
        for (int i = 0; i < sublist.size(); i++) {
            System.out.println(sublist.get(i));
        }
        System.out.println(sublist.get(0));

    }
}
