package com.example.springboot;

import com.fasterxml.jackson.databind.util.JSONPObject;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GameService {

    public JSONArray getTopHerosData(Integer accountID){
        JSONArray herosData=  getHeroswithAccountID(accountID);
        return herosData;
    }

    //exteral api to get all the heros with account id
    public JSONArray getHeroswithAccountID(Integer accountID){
        final String uri = "https://api.opendota.com/api/players/"+accountID.toString()+"/heroes";
       RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<List<HeroVO>> response = restTemplate.exchange(uri, HttpMethod.GET,entity, new ParameterizedTypeReference<List<HeroVO>>() {
        });
        List<HeroVO> heroArray = response.getBody();
//        System.out.println(heroArray.toString());
        Collections.sort(heroArray, new Comparator<HeroVO>() {
            public int compare(HeroVO h1, HeroVO h2) {
                return ((Integer)h1.getGames()).compareTo(h2.getGames());
            }
        });
        Collections.reverse(heroArray);
        List<HeroDataVO>  heroDataList= getmoredetailsforHeros(heroArray);
        JSONArray heroResultArray=getEssentialHeroData(heroDataList,heroArray);
        return  heroResultArray;
    }

    //function to get top 3 heros details
    public JSONArray getEssentialHeroData(List<HeroDataVO> heroDataArray,List<HeroVO> heroArray){
        JSONArray herodataArray=new JSONArray();
        for (int i = 0; i < 3 && i < heroArray.size(); ++i) {
            HeroVO heroVO = heroArray.get(i);
            if(heroVO!=null) {
                HeroDataVO heroDataVO = heroDataArray.get(Integer.parseInt(heroVO.getHero_id()));
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("hero_id", heroDataVO.getId());
                jsonObject.put("name", heroDataVO.getName());
                jsonObject.put("localized_name", heroDataVO.getLocalized_name());
                herodataArray.add(jsonObject);
            }
        }
        return herodataArray;
    }
    //exteral api to get all the heros details
    public List<HeroDataVO>  getmoredetailsforHeros(List<HeroVO> heroArray) {
        final String uri = "https://api.opendota.com/api/heroes/";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<List<HeroDataVO>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<HeroDataVO>>() {
        });
        List<HeroDataVO> heroDataArray = response.getBody();
        return  heroDataArray;
    }

}
