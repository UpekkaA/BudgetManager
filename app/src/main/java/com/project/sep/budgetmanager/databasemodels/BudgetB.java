package com.project.sep.budgetmanager.databasemodels;

/**
 * Created by Upekka on 9/9/2015.
 */
public class BudgetB {

        private int year;
        private String month;
        private float income;
        private float expense;

        public BudgetB() {
        }

        public BudgetB(int year, String month, float income, float expense) {
            this.year = year;
            this.month = month;
            this.income = income;
            this.expense = expense;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public float getIncome() {
            return income;
        }

        public void setIncome(float income) {
            this.income = income;
        }

        public float getExpense() {
            return expense;
        }

        public void setExpense(float expense) {
            this.expense = expense;
        }


}
