package controllers;

import com.avaje.ebean.Ebean;
import models.AdminInformation;
import models.AdminSession;
import models.Booking;
import models.LogIn;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.List;
import java.util.UUID;

import static play.libs.Json.toJson;

public class Application extends Controller {


    public static Boolean nullexeptionen(){
        boolean w = false;
        try {
            session().get("thisSession");
        }
        catch (NullPointerException e){
            w = true;
        }
        return w;
    }



    public static void sessionCleanup(){
        List<AdminSession> adminSessionList = Ebean.find(AdminSession.class).findList();
        for (int i = 0; i < adminSessionList.size(); i++){
            if (adminSessionList.get(i).timenow < (System.currentTimeMillis() - 150000)){
                AdminSession sessionen = Ebean.find(AdminSession.class, adminSessionList.get(i).timenow);
                Ebean.delete(sessionen);
            }
        }
    }


    public static Boolean authenticate(){
        Boolean x = false;
        sessionCleanup();
        List<AdminSession> adminSessionList = Ebean.find(AdminSession.class).findList();
        if (nullexeptionen() || adminSessionList.isEmpty() || session().get("thisSession") == null){
            Logger.info("-------------------_________liste tom_______-----------------");
            return false;
        }
        else{
            for (int i=0 ; i < adminSessionList.size() ; i++){
                if (session().get("thisSession").equals(adminSessionList.get(i).sessionUUID)){
                    Logger.info("--------------------------------______eval true_______---------------");
                    x = true;
                    break;
                }
                else {
                    Logger.info("--------------------------------______eval false_______---------------");
                    x = false;
                }
            }
        }
        return x;
    }



    public static Result createAdminv2(){
        AdminInformation createAdmin = Form.form(AdminInformation.class).bindFromRequest().get();
        createAdmin.save();
        return redirect(routes.Application.opretAdmin());
    }


    public static Result logOn(){
        boolean x = false;
        LogIn logOn = Form.form(LogIn.class).bindFromRequest().get();
        List<AdminInformation> adminInformationList = Ebean.find(AdminInformation.class).findList();
        for (int i=0;i < adminInformationList.size();i++){
            if(logOn.user.equals(adminInformationList.get(i).user) && logOn.password.equals(adminInformationList.get(i).password)) {
                String sessionId = UUID.randomUUID().toString();
                Long a = System.currentTimeMillis();
                AdminSession userSession = new AdminSession(a, logOn.user, sessionId);
                userSession.save();
                session().put("thisSession", sessionId);
                x = true;
                break;
            }}
        if (x){
            return redirect(routes.Application.admin());
        }
        else {
            return redirect(routes.Application.index());
        }
    }



    public static Result opretAdmin(){
        return ok(opretAdmin.render());
    }



    public static Result login() {
        if (authenticate()){
            return redirect(routes.Application.admin());
        }
        else {
            session().clear();
            Logger.info("---------------caller login---------------------");
            return ok(login.render());
        }
    }



    public static Result index() {
        return ok(index.render());
    }


    public static Result gallery() {
        return ok(Gallery.render());
    }



    public static Result activities(){
        return ok(Activities.render());
    }



    public static Result contact(){
        return ok(Contact.render());
    }



    public static Result bestil() {
        return ok(Bestil.render());
    }


    public static Result admin(){
        if (authenticate()) {
            List<Booking> bookings = Ebean.find(Booking.class).findList();
            return ok(Admin.render(bookings));
        }
        else{
            return redirect(routes.Application.login());
        }
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
