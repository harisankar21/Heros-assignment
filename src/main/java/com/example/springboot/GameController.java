package com.example.springboot;


import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    @Autowired
    GameService gameService;

    @RequestMapping(value = "/gettopherosdetails/{account_id}", method = RequestMethod.GET, produces = "application/json")
    public JSONArray index(@PathVariable("account_id") Integer accountID)
    {
        return gameService.getTopHerosData(accountID);
    }

}
