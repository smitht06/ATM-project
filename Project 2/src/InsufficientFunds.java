import javax.swing.*;

public class InsufficientFunds extends Exception
{
    public InsufficientFunds()
    {
        JOptionPane frame = new JOptionPane();
        JOptionPane.showMessageDialog(frame,
                "Insufficient Funds, to check your balance click the balance button.");
    }
}

