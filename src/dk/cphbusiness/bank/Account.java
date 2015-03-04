package dk.cphbusiness.bank;

import java.util.ArrayList;
import java.util.List;

public class Account {
  private Bank bank;
  private Customer customer;
  private String number;
  private int balance = 0;
  private List<Movement> movements = new ArrayList();
  
  public Account(Bank bank, String number) {
    this.bank = bank;
    this.number = number;
    }
  
  public Account(Bank bank, Customer customer, String number) {
    this(bank, number);
    this.customer = customer;
    }
  
  public boolean isInternal() {
    return customer == null;
    }
  
  public Bank getBank() {
    return bank;
    }

  public Customer getCustomer() {
    return customer;
    }
  
  public int getBalance() {
//    return balance;
    int balance = 0;
    for (Movement movement : movements) {
      if(movement.getSource() == this) balance -= movement.getAmount();
      else balance += movement.getAmount();
      }
    return balance;
    }

  public String getNumber() {
    return number;
    }

  public void transfer(String targetNumber, int amount) {
    Account target = bank.findAccount(targetNumber);
//    this.balance -= amount;
//    target.balance += amount;
    Movement movement = new Movement(this, target, amount);
    this.movements.add(movement);
    target.movements.add(movement);
    }
  
  
  
  }
