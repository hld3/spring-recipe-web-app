package com.dodson.spring_web_recipe.domain;

public enum Difficulty {
    
    EASY("Easy"),
    MODERATE("Moderate"),
    HARD("Hard");

    private String friendlyName;

    private Difficulty(String theFriendlyName) {
        this.friendlyName = theFriendlyName;
    }

    @Override
    public String toString() {
        return this.friendlyName;
    }
}
