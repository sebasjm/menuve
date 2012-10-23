package com.menuve.dao.api;

import java.util.List;

/**
 *
 * @author sebasjm
 */
public class PagedResult {
    
    public final List entries;
    public final int total;
    public final int pages;
    public final int page;
    public final int size;
    public final boolean isFirstPage;
    public final boolean isLastPage;
    public final boolean isLastEntry;

    public PagedResult(List entries, int total, int page, int size) {
        this.entries = entries;
        this.total = total;
        this.page = page;
        this.size = size;
        this.pages = total / size + (total % size == 0 ? 0 : 1);
        this.isFirstPage = page == 1;
        this.isLastPage = page == pages;
        this.isLastEntry = !this.isFirstPage && this.isLastPage && (total % size == 1);
    }
    
}
