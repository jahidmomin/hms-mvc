package com.hms.app.dto;

import lombok.Data;

@Data
public class Cake {
    private Long id;
    private String name;
    private String description;
    private String imageURL;

    // constructor
    public Cake(Long id, String name, String description, String imageURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }
}
