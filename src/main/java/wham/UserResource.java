package wham;

import java.io.StringWriter;
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

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.json.JSONArray;

import eventbrite.Credentials;
import eventbrite.EventbriteClient;
import eventbrite.exception.RequestException;
import eventbrite.operation.EventRequest;
import wham.config.MailerComponent;
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
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setCity(city);
        newUser.setZipCode(zipCode);

        UserOperation uo = new UserOperation();
        uo.createUser(newUser);

        // Sending email
        try {
            MailerComponent.sendMail(email, "Welcome to WHAM!", buildWelcomeEmail(newUser));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

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
     *
     * @return JSON array of booked event
     * @throws RequestException
     */
    @GET
    @Path("/history")
    @RolesAllowed("USER")
    public String getAllSavedEvents() throws RequestException {
        JSONArray events = new JSONArray();
        User user = getCurrentUser();
        BookingOperation bo = new BookingOperation();
        // Use Eventbrite API to retrieve data
        EventbriteClient client = new EventbriteClient(new Credentials());
        EventRequest request = new EventRequest();

        for (Event event : bo.getAllBookingByUser(user.getEmailId())) {
            request.setId(Long.parseLong(event.getEId()));
            events.put(client.get(request).extractAttributes());
        }
        return events.toString();
    }

    @POST
    @Path("/createpreference")
    @RolesAllowed("USER")
    public String createPreference(@FormParam("preferences") String preferences) {
        User user = getCurrentUser();
        List<Integer> subcategories = new ArrayList<Integer>();

        JSONArray jsonArray = new JSONArray(preferences);
        if (jsonArray != null)
            for (int i = 0, size = jsonArray.length(); i < size; i++)
                subcategories.add(Integer.parseInt(jsonArray.getString(i)));

        PreferenceOperation po = new PreferenceOperation();
        po.createPreference(user.getEmailId(), subcategories);

        return "success";
    }

    @PUT
    @Path("/updateuser")
    @RolesAllowed("USER")
    public String updateUser(@FormParam("fName") String fName, @FormParam("lName") String lName,
            @FormParam("phone") String phone, @FormParam("address") String address, @FormParam("city") String city,
            @FormParam("zipCode") String zipCode) {

        User user = getCurrentUser();
        Map<String, String> modifiedAttrs = new HashMap<String, String>();

        modifiedAttrs.put("address", address);
        modifiedAttrs.put("city", city);
        modifiedAttrs.put("fName", fName);
        modifiedAttrs.put("lName", lName);
        modifiedAttrs.put("phone", phone);
        modifiedAttrs.put("zipCode", zipCode);

        UserOperation uo = new UserOperation();
        uo.updateUser(user.getEmailId(), modifiedAttrs);

        return "success";
    }

    private String buildWelcomeEmail(User user) throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        /* next, get the Template */
        Template t = ve.getTemplate("./src/main/resources/Welcome.vm");
        VelocityContext context = new VelocityContext();
        context.put("firstName", user.getFName());
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }

}
