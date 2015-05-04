package models;


import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Booking extends Model {

    @Id
    public String id;

    public String name;

    public String lname;

    public java.sql.Date dato;


}
