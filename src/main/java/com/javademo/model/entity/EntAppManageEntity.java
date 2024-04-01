package com.javademo.model.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class EntAppManageEntity {

    @Id
    @Column(name = "id" )
    private long id;

    @Column(name = "name" )
    private String name;

    @Column(name = "link" )
    private String link;

    @Column(name = "parent" )
    private long parent;

    @Column(name = "order" )
    private long order;

    @Column(name = "description" )
    private String description;

    @Column(name = "icon_name" )
    private String iconName;

    @Column(name = "level" )
    private long level;

    @Column(name = "app_type" )
    private String appType;

    @Column(name = "app_id" )
    private String appId;

}
