package controllers;

import com.avaje.ebean.Ebean;
import models.Booking;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
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

    public static Result admin(){
        List<Booking> bookings = Ebean.find(Booking.class).findList();
        return ok(Admin.render(bookings));
    }

    public static Result bestil() {
        return ok(Bestil.render());
    }

    public static Result addBooking(String name, String lname, String arrDate, String depDate, String room){
        Booking booking = new Booking();
        booking.name = name;
        booking.lname = lname;
        booking.arrDate = arrDate;
        booking.depDate = depDate;
        booking.room = room;
        Ebean.save(booking);
        return ok(Json.toJson(booking));
    }

    public static Result deleteBooking(Integer id)
    {
        Booking booking = Ebean.find(Booking.class, id);
        Ebean.delete(booking);
        return ok();
    }

    public static Result jsRoutes() {
        response().setContentType("text/javascript");
        return ok(play.Routes.javascriptRouter("jsRoutes",
                controllers.routes.javascript.Application.addBooking(),
                controllers.routes.javascript.Application.deleteBooking()
        ));
    }

}
