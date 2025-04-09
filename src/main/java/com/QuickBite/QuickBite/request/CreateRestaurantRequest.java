package com.QuickBite.QuickBite.request;

import com.QuickBite.QuickBite.model.Address;
import com.QuickBite.QuickBite.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {

    private long id;
    private String name;
    private String description;
    private Address address;
    private String cuisineType;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;

}
