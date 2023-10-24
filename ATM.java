import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
	private double balance;

	public BankAccount(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public boolean withdraw(double amount) {
		if (balance >= amount) {
			balance -= amount;
			return true;
		} else {
			return false;
		}
	}
}

public class ATM extends JFrame {
	private BankAccount account;

	public ATM(BankAccount account) {
		this.account = account;
		setTitle("ATM Interface");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLookAndFeel();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel amountLabel = new JLabel("Amount:");
		JTextField amountField = new JTextField(10);
		JButton depositButton = new JButton("Deposit");
		JButton withdrawButton = new JButton("Withdraw");
		JButton balanceButton = new JButton("Check Balance");
		JTextArea resultArea = new JTextArea(5, 20);

		panel.add(amountLabel);
		panel.add(amountField);
		panel.add(depositButton);
		panel.add(withdrawButton);
		panel.add(balanceButton);
		panel.add(resultArea);

		depositButton.addActionListener(e -> {
			double amount = Double.parseDouble(amountField.getText());
			account.deposit(amount);
			JOptionPane.showMessageDialog(null, "Deposit successful. Current balance: " + account.getBalance());
			amountField.setText("");
		});

		withdrawButton.addActionListener(e -> {
			double amount = Double.parseDouble(amountField.getText());
			if (account.withdraw(amount)) {
				JOptionPane.showMessageDialog(null, "Withdrawal successful. Current balance: " + account.getBalance());
			} else {
				JOptionPane.showMessageDialog(null, "Insufficient balance. Withdrawal failed.");
			}
			amountField.setText("");
		});

		balanceButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Your current balance is: " + account.getBalance());
		});

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(panel);
		setVisible(true);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccount(1000); // initial balance set to 1000
		new ATM(bankAccount);
	}
}
