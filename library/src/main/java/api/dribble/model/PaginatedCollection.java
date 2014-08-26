package api.dribble.model;

/**
 * Created by bhaskar on 2014-08-26.
 */
public abstract class PaginatedCollection {
    public int page;
    public int pages;
    public int per_page;
    public int total;
}
