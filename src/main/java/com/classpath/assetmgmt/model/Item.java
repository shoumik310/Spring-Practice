package com.classpath.assetmgmt.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Item {

    private long id;
    private String name;
    private double price;

}