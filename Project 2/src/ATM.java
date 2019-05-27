import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.*;



public class ATM extends JFrame{
    private JButton withdraw;
    private JButton deposit;
    private JButton transferTo;
    private JButton balance;
    private JRadioButton checkingButton;
    private JRadioButton savingsButton;
    private ButtonGroup radioButtons;
    private JTextField amount;
    private JOptionPane window;
    private Account savingsAccount;
    private Account checkingAccount;

    public ATM(double checkingBalance, double savingsBalance){
        super("ATM Machine");
        setLayout(new GridLayout(4,2));

        withdraw = new JButton("Withdraw");
        add(withdraw);
        withdraw.addActionListener(new WithdrawHandler());

        deposit = new JButton("Deposit");
        add(deposit);
        deposit.addActionListener(new DepositHandler());

        transferTo = new JButton("Transfer to");
        add(transferTo);
        transferTo.addActionListener(new TransferHandler());

        balance = new JButton("Balance");
        add(balance);
        balance.addActionListener(new BalanceHandler());

        checkingButton = new JRadioButton("Checking");
        add(checkingButton);

        savingsButton = new JRadioButton("Savings");
        add(savingsButton);

        radioButtons = new ButtonGroup();
        radioButtons.add(checkingButton);
        radioButtons.add(savingsButton);

        amount = new JTextField(25);
        add(amount);

        window = new JOptionPane();

        savingsAccount = new Account().new SavingsAccount();
        checkingAccount = new Account().new CheckingAccount();

        makeAccount(checkingBalance, savingsBalance);
        }

    private static DecimalFormat df = new DecimalFormat("$0.00");



    public void makeAccount(double checkingBalance, double savingsBalance){
        checkingAccount.setBalance(checkingBalance);
        savingsAccount.setBalance(savingsBalance);
    }

    public double getAmountEntered() {
        try{
            return Double.parseDouble(amount.getText());
        }catch(NumberFormatException e){
            System.out.println("caught in get amount entered");
            amount.setText("");
            return 0;
        }

    }

    private class WithdrawHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                if(getAmountEntered() > 0&& getAmountEntered()%20 == 0) {
                    if (checkingButton.isSelected()){
                        checkingAccount.withdraw(getAmountEntered());
                        JOptionPane.showMessageDialog(window,"Withdraw complete\n Balance:" + df.format(checkingAccount.getBalance()));

                }else if(savingsButton.isSelected()){
                        savingsAccount.withdraw(getAmountEntered());
                        JOptionPane.showMessageDialog(window,"Withdraw complete \n Balance:" + df.format(checkingAccount.getBalance()));                    }
                amount.setText("");
                }else JOptionPane.showMessageDialog(window, "Invalid entry, please enter increments of 20.");
                amount.setText("");
            }catch(InsufficientFunds insufficientFunds){
                System.out.println("Error");
                amount.setText("");
            }
        }
    }
    class DepositHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

                if(getAmountEntered() > 0) {
                    if (checkingButton.isSelected()){
                        checkingAccount.deposit(getAmountEntered());
                        JOptionPane.showMessageDialog(window,"deposit complete\n Balance:" + df.format(checkingAccount.getBalance()));

                    }else if(savingsButton.isSelected()){
                        savingsAccount.deposit(getAmountEntered());
                        JOptionPane.showMessageDialog(window,"deposit complete\n Balance: " + df.format(savingsAccount.getBalance()));
                    }else {
                        JOptionPane.showMessageDialog(window, "Please select account type");
                        amount.setText("");
                    }
                }else JOptionPane.showMessageDialog(window,"Please enter a number greater than zero");
                amount.setText("");
            }

            }

    private class TransferHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                if(getAmountEntered() > 0) {
                    if (checkingButton.isSelected()){
                        checkingAccount.transfer(getAmountEntered());
                        savingsAccount.transferTo(getAmountEntered());
                        JOptionPane.showMessageDialog(window,"transfer complete");

                    }else if(savingsButton.isSelected()){
                        savingsAccount.transfer(getAmountEntered());
                        checkingAccount.transferTo(getAmountEntered());
                        JOptionPane.showMessageDialog(window,"transfer complete");
                    }
                    amount.setText("");
                }else JOptionPane.showMessageDialog(window, "Enter a number greater than zero.");
                amount.setText("");
            }catch(InsufficientFunds insufficientFunds){
                System.out.println("Error");
                amount.setText("");
            }
        }
    }
    private class BalanceHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(checkingButton.isSelected()){
                JOptionPane.showMessageDialog(window, "Balance: "
                        + df.format(checkingAccount.getBalance()));
            }
            else if(savingsButton.isSelected()){
                JOptionPane.showMessageDialog(window,"Balance: "
                        + df.format(savingsAccount.getBalance()));
            }
            else JOptionPane.showMessageDialog(window,"Please select account type");
            amount.setText("");
        }
    }
    public static void main(String args[]){
        ATM atm = new ATM(150,150);
        atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        atm.setSize(350,250);
        atm.setVisible(true);
    }
}






