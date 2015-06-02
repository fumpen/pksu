package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by frederik on 29-05-2015.
 */

@Entity
public class AdminSession extends Model {

        @Id
        public Long timenow;

        @Constraints.Required
        public String adminName;

        @Constraints.Required
        public String sessionUUID;

        public AdminSession(Long timenow, String adminName, String sessionUUID){
            this.timenow = timenow;
            this.adminName = adminName;
            this.sessionUUID = sessionUUID;
        }
}
