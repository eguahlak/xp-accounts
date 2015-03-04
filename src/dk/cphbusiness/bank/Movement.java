package dk.cphbusiness.bank;

public class Movement {
  private final Account source;
  private final Account target;
  private final int amount;

  public Movement(Account source, Account target, int amount) {
    this.source = source;
    this.target = target;
    this.amount = amount;
    }

  public Account getSource() {
    return source;
    }

  public Account getTarget() {
    return target;
    }

  public int getAmount() {
    return amount;
    }
  
  }
