package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
  public static Result index() {
    return ok("Hello World");
    //return ok(index.render("Your new application is ready."));
  }

  public static Result todo(){
    return status(418,"Some Strange Response");
  }
  
}