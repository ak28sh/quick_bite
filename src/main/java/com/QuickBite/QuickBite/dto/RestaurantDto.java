package com.QuickBite.QuickBite.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDto {

    private Long id;
    private String title;
    private List<String> images;
    private String description;

}
