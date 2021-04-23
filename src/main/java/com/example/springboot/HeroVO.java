package com.example.springboot;


public class HeroVO {

    String hero_id;
    String last_played;
    Integer games;
    Integer win;
    Integer with_games;
    Integer with_win;
    Integer against_games;
    Integer against_win;

    public String getHero_id() {
        return hero_id;
    }

    public void setHero_id(String hero_id) {
        this.hero_id = hero_id;
    }

    public String getLast_played() {
        return last_played;
    }

    public void setLast_played(String last_played) {
        this.last_played = last_played;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getWith_games() {
        return with_games;
    }

    public void setWith_games(Integer with_games) {
        this.with_games = with_games;
    }

    public Integer getWith_win() {
        return with_win;
    }

    public void setWith_win(Integer with_win) {
        this.with_win = with_win;
    }

    public Integer getAgainst_games() {
        return against_games;
    }

    public void setAgainst_games(Integer against_games) {
        this.against_games = against_games;
    }

    public Integer getAgainst_win() {
        return against_win;
    }

    public void setAgainst_win(Integer against_win) {
        this.against_win = against_win;
    }

    @Override
    public String toString() {
        return "herovo [hero_id=" + hero_id + ", games=" + games + " ]";
    }
}
