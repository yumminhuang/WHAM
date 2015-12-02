package wham;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.json.JSONArray;

import wham.model.User;
import wham.operation.BookingOperation;

@Path("/book")
public class BookingResource extends ResourceBase {

    /**
     * Save a event for current user
     * @param id Event ID
     * @return
     */
    @POST
    @Path("/save")
    @RolesAllowed("USER")
    public String save(@FormParam("eId") String id) {
        User user = getCurrentUser();
        BookingOperation bo = new BookingOperation();
        bo.save(user.getEmailId(), id);
        return "success";
    }

    /**
     * Post a review
     * @param id
     * @param comment
     * @return
     */
    @POST
    @Path("/review")
    @RolesAllowed("USER")
    public String review(@FormParam("eId") String id, @FormParam("comment") String comment) {
        User user = getCurrentUser();
        BookingOperation bo = new BookingOperation();
        bo.review(user.getEmailId(), id, comment);
        return "success";
    }

    /**
     * Get all reviews of the event
     * @param id
     * @return
     */
    @GET
    @Path("/review/{eId}")
    public String review(@PathParam("eId") String id) {
        BookingOperation bo = new BookingOperation();
        List<String> reviews = bo.getAllReviewsForEvent(id);
        JSONArray jsonReviews = new JSONArray(reviews);
        return jsonReviews.toString();
    }

    /**
     * Post a rate
     * @param id
     * @param rate
     * @return
     */
    @POST
    @Path("/rate")
    @RolesAllowed("USER")
    public String rate(@FormParam("eId") String id, @FormParam("rate") String rate) {
        User user = getCurrentUser();
        BookingOperation bo = new BookingOperation();
        bo.rate(user.getEmailId(), id, Integer.parseInt(rate));
        return "success";
    }

    /**
     * Get average rate score of the event
     * @param id
     * @return
     */
    @GET
    @Path("/rate/{eId}")
    public String rate(@PathParam("eId") String id) {
        BookingOperation bo = new BookingOperation();
        return String.valueOf(bo.averageRate(id));
    }

}
