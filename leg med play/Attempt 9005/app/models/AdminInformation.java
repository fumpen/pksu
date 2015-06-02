package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdminInformation extends Model {

    @Id
    public Integer id;

    @Constraints.Required
    public String user;

    @Constraints.Required
    public String password;
}
