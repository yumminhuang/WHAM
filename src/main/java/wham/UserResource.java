package wham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.json.JSONArray;

import wham.model.Event;
import wham.model.User;
import wham.operation.BookingOperation;
import wham.operation.PreferenceOperation;
import wham.operation.UserOperation;

@Path("/users")
public class UserResource extends ResourceBase {

    @POST
    public String createNewUser(@FormParam("fName") String fName, @FormParam("lName") String lName,
            @FormParam("emailId") String email, @FormParam("password") String password,
            @FormParam("phone") String phone, @FormParam("address") String address, @FormParam("city") String city,
            @FormParam("zipCode") String zipCode) {
        User newUser = new User();
        newUser.setFName(fName);
        newUser.setLName(lName);
        newUser.setEmailId(email);
        newUser.setPassword(password);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setCity(city);
        newUser.setZipCode(zipCode);

        UserOperation uo = new UserOperation();
        uo.createUser(newUser);

        return "success";
    }

    @GET
    @Path("/current")
    @RolesAllowed("USER")
    public String current() {
        User user = getCurrentUser();
        return user.serialize().toString();
    }

    /**
     * Get user's preferences
     * @return JSON array of sub-category id
     */
    @GET
    @Path("/preferences")
    @RolesAllowed("USER")
    public String getPreferences() {
        User user = getCurrentUser();
        PreferenceOperation po = new PreferenceOperation();
        JSONArray subcategories = new JSONArray(po.getSubCategory(user.getEmailId()));
        return subcategories.toString();
    }

    /**
     * Get user's booking history
     * @return JSON array of booked event ids
     */
    @GET
    @Path("/history")
    @RolesAllowed("USER")
    public String getAllSavedEvents() {
        JSONArray eIds = new JSONArray();
        User user = getCurrentUser();
        BookingOperation bo = new BookingOperation();
        for (Event event : bo.getAllBookingByUser(user.getEmailId())) {
            eIds.put(event.getEId());
        }
        return eIds.toString();
    }

    @POST
    @Path("/createpreference")
    @RolesAllowed("USER")
    public String createPreference(@FormParam("preference1") String preference1,
            @FormParam("preference2") String preference2, @FormParam("preference3") String preference3) {
        User user = getCurrentUser();
        List<Integer> subcategories = new ArrayList<Integer>();

        subcategories.add(Integer.parseInt(preference1));
        subcategories.add(Integer.parseInt(preference2));
        subcategories.add(Integer.parseInt(preference3));

        PreferenceOperation po = new PreferenceOperation();
        po.createPreference(user.getEmailId(), subcategories);

        return "success";
    }

    @PUT
    @Path("/updateuser")
    @RolesAllowed("USER")
    public String updateUser(@FormParam("fname") String fName, @FormParam("lname") String lName,
            @FormParam("password") String password, @FormParam("phone") String phone,
            @FormParam("address") String address, @FormParam("city") String city,
            @FormParam("zipCode") String zipCode) {

        User user = getCurrentUser();
        Map<String, String> modifiedAttrs = new HashMap<String, String>();

        modifiedAttrs.put("address", address);
        modifiedAttrs.put("city", city);
        modifiedAttrs.put("fName", fName);
        modifiedAttrs.put("lName", lName);
        modifiedAttrs.put("password", password);
        modifiedAttrs.put("phone", phone);
        modifiedAttrs.put("zipCode", zipCode);

        UserOperation uo = new UserOperation();
        uo.updateUser(user.getEmailId(), modifiedAttrs);

        return "success";
    }

}
