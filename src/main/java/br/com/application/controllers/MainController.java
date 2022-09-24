package br.com.application.controllers;

import br.com.application.model.Response;
import br.com.application.model.dao.UserDao;
import br.com.application.model.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class MainController {

    protected UserDao dao = new UserDao();
    @CrossOrigin(origins = "*")
    @PostMapping("create")
    public  @ResponseBody String create(@RequestParam String name, @RequestParam String city, @RequestParam String email, @RequestParam String bornDate, @RequestParam String password){
        Response response = new Response();
        try{
            User user = new User();
            user.setName(name);
            user.setCity(city);
            user.setEmail(email);
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(bornDate);
            java.sql.Date newDate = new java.sql.Date(date1.getTime());
            user.setBornDate(newDate);
            String encodedPass = Base64.getEncoder().encodeToString(password.getBytes());
            user.setPassword(encodedPass);
            dao.save(user);

            response.setStatus(HttpStatus.OK);
            response.setSuccessMessage("saved");


        }catch (ParseException exception){

            response.setErrorMessage(exception.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        return response.toString();

    }

    @GetMapping("get")
    public List<User> getUser(Integer id){
        var userOpt = dao.findById(id);
        User user = new User();
        List<User> users = dao.findById(id);
        return users;

    }

    @PutMapping("update")
    public String updateUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String gender, @RequestParam String city, @RequestParam String email, @RequestParam String bornDate){
        User user = new User();
        List<User> users = dao.findById(id);

        Response response = new Response();
        try{
            if(!users.isEmpty()){
                User userToUpdate = new User();
                userToUpdate.setId(id);
                userToUpdate.setName(name);
                userToUpdate.setGender(gender);
                userToUpdate.setCity(city);
                userToUpdate.setEmail(email);
                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(bornDate);
                java.sql.Date newDate = new java.sql.Date(date1.getTime());
                userToUpdate.setBornDate(newDate);

                dao.update(userToUpdate);

                response.setStatus(HttpStatus.OK);
                response.setSuccessMessage("updated");
            }
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response.toString();
    }

    @DeleteMapping("delete")
    public String delete(@RequestParam Integer id){
        Response response = new Response();
        try{
            dao.removeById(id);
            response.setStatus(HttpStatus.OK);
            response.setSuccessMessage("deleted");
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response.toString();
    }

}
