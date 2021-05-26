package com.example.android.workoutgenerator;

import java.io.Serializable;

public class Complex implements Serializable {

    String metcon;
    String okay;
    String howToDo;
    int liked;



    public Complex(String metcon, String howToDo, int liked) {
        this.metcon = metcon;
        this.howToDo = howToDo;
        this.liked = liked;

    }

    public Complex(String metcon, String rx, String howToDo, int saved){
        this.metcon = metcon;
        this.okay = rx;
        this.howToDo = howToDo;
        this.liked = saved;
    }

    public String getMetcon() {
        return metcon;
    }

    public void setMetcon(String metcon) {
        this.metcon = metcon;
    }

    public String getOkey() {
        return okay;
    }

    public void setOkey(String okay) {
        this.okay = okay;
    }

    public String getHowToDo() {
        return howToDo;
    }


    public void setHowToDo(String howToDo) {
        this.howToDo = howToDo;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }
}
