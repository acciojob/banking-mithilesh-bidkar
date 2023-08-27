package com.driver;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name,balance,0);
        this.maxWithdrawalLimit = maxWithdrawalLimit;
        this.rate = rate;

    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMaxWithdrawalLimit() {
        return maxWithdrawalLimit;
    }

    public void setMaxWithdrawalLimit(double maxWithdrawalLimit) {
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }

    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance

        if(amount > this.maxWithdrawalLimit()){
            throw  new Exception("Maximum Withdraw Limit Exceed");
        }

        if(amount > super.getBalance()){
            throw new Exception("Insufficient Balance");
        }
        else {
            super.withdraw(amount);
        }



    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount

//        return ((double)years*getBalance()*rate)/100.0;
        return (super.getBalance()*years*this.rate/100);
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year

        double cinterest = super.getBalance()*Math.pow((1+this.rate/(times*100)),times*years);


        return cinterest;
    }

}
