package com.fongwell.satchi.crm.core.common.query;

import java.util.Collection;

/**
 * Created by roman on 18-4-4.
 */
public class RequestData {

    private String query;

    private Collection<Long> categoryIds;

    private Collection<Long> seriesIds;

    private Collection<String> numbers;

    private String sortField;

    private boolean desc;

    private int from;

    private int size;

    private Collection<String> states;

    public RequestData(String query, Collection<Long> categoryIds, Collection<String> numbers, Collection<String> states,
                       int from, int size) {
        this.query = query;
        this.categoryIds = categoryIds;
        this.numbers = numbers;
        this.states = states;
        this.from = from;
        this.size = size;
    }

    public RequestData(String query, Collection<Long> categoryIds, Collection<Long> seriesIds, int from, int size) {
        this.query = query;
        this.categoryIds = categoryIds;
        this.seriesIds = seriesIds;
        this.from = from;
        this.size = size;
    }

    public RequestData(int from, int size) {
        this.from = from;
        this.size = size;
    }

    public Collection<Long> getSeriesIds() {
        return seriesIds;
    }

    public void sort(String sortField,boolean desc){
        this.sortField = sortField;
        this.desc = desc;
    }

    public String getQuery() {
        return query;
    }

    public Collection<String> getNumbers() {
        return numbers;
    }

    public int getFrom() {
        return from;
    }

    public int getSize() {
        return size;
    }

    public Collection<String> getStates() {
        return states;
    }

    public Collection<Long> getCategoryIds() {
        return categoryIds;
    }

    public String getSortField() {
        return sortField;
    }

    public boolean isDesc() {
        return desc;
    }

    public static enum SortFieldParam{
        salePrice,sale,createdDate
    }
}
