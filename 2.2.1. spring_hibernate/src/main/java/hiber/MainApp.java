package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User VLAD = new User("Влад", "Лисненко", "vllis@mail.io");
      User PETR = new User("Петр", "Пешкин", "petrpesh@mail.io");
      User VIKT = new User("Виктор", "Макаров", "vikmak.io");
      User DEN = new User("Денис", "Веткин", "denvet@mail.io");

      Car audi = new Car("Audi", 11);
      Car bmw = new Car("BMW", 233);
      Car suzuki = new Car("Sisuki", 322);
      Car lada = new Car("Lada", 5);
      Car bentley = new Car("Fiat", 222);
      Car cherry = new Car("Mercedes-Benz", 15);

      userService.add(VLAD.setCar(audi).setUser(VLAD));
      userService.add(PETR.setCar(bmw).setUser(PETR));
      userService.add(VIKT.setCar(suzuki).setUser(VIKT));
      userService.add(DEN.setCar(bentley).setUser(DEN));
      userService.add(DEN.setCar(cherry).setUser(DEN));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("BMW", 2));

      try {
         User notFoundUser = userService.getUserByCar("UAZ", 42);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();

   }
}