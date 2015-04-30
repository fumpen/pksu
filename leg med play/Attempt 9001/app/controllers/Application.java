package controllers;

import models.Booking;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result gallery() {
        return ok(Gallery.render());
    }

    public static Result addBooking(){
        Booking booking = Form.form(Booking.class).bindFromRequest().get();
        booking.save();
        return redirect(routes.Application.index());
    }

    public static  Result getBookings(){
        List<Booking> bookings = new Model.Finder(String.class, Booking.class).all();
        return ok(toJson(bookings));
    }

}
