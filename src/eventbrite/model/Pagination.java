package eventbrite.model;

import com.google.gson.annotations.SerializedName;

/**
 * @Author: yummin
 * Date: 15/11/6
 */
public class Pagination {

    @SerializedName("object_count")
    private int count;
    @SerializedName("page_number")
    private int pageNumber;
    @SerializedName("page_count")
    private int pageCount;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
