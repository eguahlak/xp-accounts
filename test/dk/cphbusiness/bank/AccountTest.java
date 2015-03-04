package dk.cphbusiness.bank;

import static org.hamcrest.CoreMatchers.is;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
  public final Mockery context = new JUnitRuleMockery();
  
  @Test
  public void testCreateInternalAccount() throws Exception {
    Bank bank = new BankStub();
    // Bank bank = new Bank() {};
    String number = "#4711";
    
    Account account = new Account(bank, number);
    
    assertTrue(account.isInternal());
    assertThat(account.getBank(), is(bank));
    assertThat(account.getNumber(), is(number));
    assertThat(account.getBalance(), is(0));
    }
  
  @Test
  public void testCreateCustomerAccount() throws Exception {
    Bank bank = context.mock(Bank.class);
    Customer customer = context.mock(Customer.class);
    String number = "#4712";
    
    Account account = new Account(bank, customer, number);
    
    assertFalse(account.isInternal());
    assertThat(account.getBank(), is(bank));
    assertThat(account.getCustomer(), is(customer));
    assertThat(account.getNumber(), is(number));
    assertThat(account.getBalance(), is(0));
    }
  
  @Test
  public void testTransferAmount() throws Exception {
    final Customer customer = context.mock(Customer.class);
    final Bank bank = context.mock(Bank.class);
    final String sourceNumber = "#4711";
    final String targetNumber = "#4712";
    final Account source = new Account(bank, customer, sourceNumber);
    final Account target = new Account(bank, customer, targetNumber);
    int amount = 100;
    
    context.checking(
        new Expectations() {
          { 
            oneOf(bank).findAccount(targetNumber);
            will(returnValue(target));
            }
          }
      );
    
    source.transfer(targetNumber, amount);
  
    assertThat(target.getBalance(), is(amount));
    assertThat(source.getBalance(), is(-amount));
    }

  }
