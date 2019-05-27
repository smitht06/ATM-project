import javax.swing.*;

public class Account {
    private double balance;
    private int count;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public class CheckingAccount extends Account{
        public CheckingAccount(){
        }
    }
    public class SavingsAccount extends Account{
        public SavingsAccount(){
        }
    }
    public void withdraw(double withdrawFunds) throws InsufficientFunds {
        if(this.balance-withdrawFunds < 0){
            throw new InsufficientFunds();
        }
        this.balance = this.balance - withdrawFunds;
        count++;
        if(count>=4) {
            JOptionPane feeCharged = new JOptionPane();
            JOptionPane.showMessageDialog(feeCharged, "You have exceeded 3 transaction limit, a $1.50 fee will be charged.");
            this.balance = this.balance - 1.50;
        }
    }
    public void deposit(double depositFunds){
        this.balance = this.balance + depositFunds;
    }
    // Method for Transfer To action listener. Adds requested transfer amount.
    public void transferTo(double transferAmount)
    {
        this.balance = this.balance + transferAmount;
    }
    public void transfer(double transferFunds)throws InsufficientFunds{
        if(this.balance - transferFunds < 0){
            throw new InsufficientFunds();
        }
        this.balance = this.balance - transferFunds;
    }

}






