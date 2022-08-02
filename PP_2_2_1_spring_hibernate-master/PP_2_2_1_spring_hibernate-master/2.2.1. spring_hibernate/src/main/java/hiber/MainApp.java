package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import hiber.service.CarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      CarService carService = context.getBean(CarService.class);
      carService.add(new Car("BMW", 5, user1));
      carService.add(new Car("AUDI", 4, user2));
      carService.add(new Car("MERCEDES", 63, user3));
      carService.add(new Car("PORSCHE", 911, user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<User> userByCar = userService.getUserbyCar("MERCEDES", 63);
      for (User userOfCar : userByCar) {
         System.out.println("Id = "+userOfCar.getId());
         System.out.println("First Name = "+userOfCar.getFirstName());
         System.out.println("Last Name = "+userOfCar.getLastName());
         System.out.println("Email = "+userOfCar.getEmail());
         System.out.println();
      }

      context.close();
   }
}
