//package com.restaurantmanagement.entity;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.solr.core.mapping.Indexed;
//import org.springframework.data.solr.core.mapping.SolrDocument;
//
//@SolrDocument(collection = "restaurants")
//public class RestaurantSolr {
//    @Id
//    @Indexed(name = "id", type = "string")
//    private String id;
//
//    @Indexed(name = "name", type = "string")
//    private String name;
//
//    @Indexed(name = "latitude", type = "double")
//    private double latitude;
//
//    @Indexed(name = "longitude", type = "double")
//    private double longitude;
//}
