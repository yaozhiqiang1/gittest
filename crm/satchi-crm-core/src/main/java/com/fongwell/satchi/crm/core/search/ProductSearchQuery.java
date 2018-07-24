package com.fongwell.satchi.crm.core.search;

import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
public class ProductSearchQuery extends SearchQuery {

    private String query;

    private Collection<Long> categoryIds;

    private Collection<Long> seriesIds;

    private Collection<String> numbers;

    private Collection<Long> ids;

    private String sortField;

    private boolean desc;

    private Collection<String> states;

    public Collection<Long> getIds() {
        return ids;
    }

    public void setIds(final Collection<Long> ids) {
        this.ids = ids;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(final String query) {
        this.query = query;
    }

    public Collection<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(final Collection<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Collection<Long> getSeriesIds() {
        return seriesIds;
    }

    public void setSeriesIds(final Collection<Long> seriesIds) {
        this.seriesIds = seriesIds;
    }

    public Collection<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(final Collection<String> numbers) {
        this.numbers = numbers;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(final String sortField) {
        this.sortField = sortField;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(final boolean desc) {
        this.desc = desc;
    }

    public Collection<String> getStates() {
        return states;
    }

    public void setStates(final Collection<String> states) {
        this.states = states;
    }
}
