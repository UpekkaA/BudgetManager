package com.project.sep.budgetmanager.databasemodels;

/**
 * Created by Upekka on 8/12/2015.
 */
public class Transaction {
    private String date;
    private String incomeorexpense;
    private String category;
    private float amount;
    private String note;

    public Transaction() {
    }

    public Transaction(String date, String incomeorexpense, String category, float amount, String note) {
        this.date = date;
        this.incomeorexpense = incomeorexpense;
        this.category = category;
        this.amount = amount;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
