package com.match_tracker.match_tracker.dto;


public class ItemDto {
    private Long id;
    private String name;
    private Integer cost;
    private String description;
    private String logoUrl;
    private int quantity;

    // Конструктор
    public ItemDto(Long id, String name, Integer cost, String description, String logoUrl, int quantity) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.logoUrl = logoUrl;
        this.quantity = quantity;
    }

    // Геттеры
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    // Сеттеры
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}