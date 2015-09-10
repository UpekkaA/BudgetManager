package com.project.sep.budgetmanager.databasemodels;

/**
 * Created by Upekka on 8/12/2015.
 */
public class Category {
    private String incomeorexpense;
    private String category;

    public Category() {
    }

    public Category(String incomeorexpense, String category) {
        this.incomeorexpense = incomeorexpense;
        this.category = category;
    }

    public String getIncomeorexpense() {
        return incomeorexpense;
    }

    public void setIncomeorexpense(String incomeorexpense) {
        this.incomeorexpense = incomeorexpense;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
