package models;

import play.data.validation.Constraints;

public class LogIn{

    @Constraints.Required
    public String user;

    @Constraints.Required
    public String password;
}
