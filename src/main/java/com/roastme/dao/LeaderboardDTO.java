package com.roastme.dao;

public class LeaderboardDTO {

    private String name;
    private Long count;

    public LeaderboardDTO(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Long getCount() {
        return count;
    }
}
