package com.example.elessar1992.movies_realm.activities.activities.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elessar1992 on 3/8/18.
 */

public class MoviesResponse
{
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Movie> results;
    @SerializedName("totalResults")
    private int totalResults;
    @SerializedName("totalPages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
