package com.cs160.joleary.catnip;

/**
 * Created by gabrielarreola on 3/3/16.
 */
public class MoveDataProvider {
    private int move_poster_resource;
    private String move_title;
    private  String move_rating;
    //help
    private String email;
    private String tweet;
    private String party;

    public String getMove_rating() {
        return move_rating;

    }
    public MoveDataProvider(int move_poster_resource,String move_title,String move_rating, String email, String tweet, String party){


        this.setMove_poster_resource(move_poster_resource);
        this.setMove_title(move_title);
        this.setMove_rating(move_rating);
        this.setEmail(email);
        this.setTweet(tweet);
        this.setParty(party);
    }

    public void setParty(String party) {this.party = party;}

    public String getParty() { return party; }
    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTweet(){
        return tweet;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMove_rating(String move_rating) {
        this.move_rating = move_rating;
    }

    public String getMove_title() {
        return move_title;
    }

    public void setMove_title(String move_title) {
        this.move_title = move_title;
    }

    public int getMove_poster_resource() {
        return move_poster_resource;
    }

    public void setMove_poster_resource(int move_poster_resource) {
        this.move_poster_resource = move_poster_resource;
    }
}