package com.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role {
    private Integer id;
    private int user_id;
    private String name;
    private String resource;

    public Role() {
    }

    public Role(Integer id, int user_id, String name, String resource) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.resource = resource;
    }

}
