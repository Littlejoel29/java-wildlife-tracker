import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.allEndangered());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered_sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("rangerName");
      int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
      String latLong = request.queryParams("latLong");
      TimseStamp lastSeen = request.queryParams();
      Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
      sighting.save();
      model.put("sighting", sighting);
      model.put("animals", EndangeredAnimal.allEndangered());
      String endangered_animal = EndangeredAnimal.find(animalIdSelected).getName();
      model.put("endangered_animal", endangered_animal);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("rangerName");
      int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
      String latLong = request.queryParams("latLong");
      Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
      sighting.save();
      model.put("sighting", sighting);
      model.put("animals", Animal.all());
      String animal = Animal.find(animalIdSelected).getName();
      model.put("animal", animal);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.allEndangered());
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      boolean endangered = request.queryParamsValues("endangered")!=null;
      if (endangered) {
        String name = request.queryParams("name");
        String health = request.queryParams("health");
        String age = request.queryParams("age");
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
        endangeredAnimal.save();
        model.put("animals", Animal.all());
        model.put("endangeredAnimals", EndangeredAnimal.allEndangered());
      } else {
        String name = request.queryParams("name");
        Animal animal = new Animal(name);
        animal.save();
        model.put("animals", Animal.all());
        model.put("endangeredAnimals", EndangeredAnimal.allEndangered());
      }
      response.redirect("/");
        return null;
      });

    get("/animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(Integer.parseInt(request.params("id")));
      model.put("animal", animal);
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered_animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("template", "templates/endangered_animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/animals/:id", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Animal animal = Animal.find(Integer.parseInt(request.params("id")));
    //   String name = request.queryParams("name");
    //   animal.updateName(name);
    //   String url = String.format("/animals/%d", animal.getId());
    //   model.put("template", "templates/success-update.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    // post("/endangered_animal/:id", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
    //   String name = request.queryParams("name");
    //   endangeredAnimal.updateName(name);
    //   String url = String.format("/endangered_animal/%d", endangeredAnimal.getId());
    //   model.put("template", "templates/success-update.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    get("/error", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/error.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
