package com.xy.entity;

import lombok.Data;

@Data
public class Score {
    private String name;
    private int socrevalue;

    public Score(String name, int socre) {
        this.name=name;
        this.socrevalue=socre;
    }

}
