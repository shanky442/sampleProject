package shl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shl.Customer;
import shl.CustomerRepository;
import shl.Games;
import shl.GamesRepository;

import java.util.List;

//@Controller
public class DBController {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private GamesRepository gamesRepository;

    @RequestMapping("/clean")
    public @ResponseBody String clean(){
        repository.deleteAll();
        return "Success";
    }

    @RequestMapping("/saveNewUser")
    public @ResponseBody String saveNewUser(@RequestParam("fName") String firstName, @RequestParam("lName") String lastName){
        repository.save(new Customer(firstName, lastName));
        return "Success";
    }

    @RequestMapping("/getAllUser")
    public @ResponseBody List<Customer> getAllUser(){
        List<Customer> listOfCust=repository.findAll();
        return listOfCust;
    }

    @RequestMapping("/saveNewGame")
    public @ResponseBody String saveNewGame(){
        //repository.deleteAll();

        Games game =new Games();
        game.setDate("22/06");
        game.setFirstParty("me");
        game.setSecondParty("you");
        gamesRepository.save(game);
        return "Success";
    }

    @RequestMapping("/getAllGame")
    public @ResponseBody List<Games> getAllGames(){
        return gamesRepository.findAll();
    }
}
