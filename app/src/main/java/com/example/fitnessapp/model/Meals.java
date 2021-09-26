package com.example.fitnessapp.model;

public class Meals {

    String category,mealName,calorieAmount,cookingTime,ingredients;

    public  Meals(){

    }

    public Meals(String category,String mealName,String calorieAmount,String cookingTime,String ingredients,String method){
        this.category = category;
        this.mealName = mealName;
        this.calorieAmount = calorieAmount;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;

    }

    public String getCalorieAmount() {
        return calorieAmount;
    }

    public void setCalorieAmount(String calorieAmount) {
        this.calorieAmount = calorieAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }


}
