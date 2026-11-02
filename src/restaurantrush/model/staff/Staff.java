package restaurantrush.model.staff;

import java.util.function.Supplier;

import restaurantrush.model.Table;
import restaurantrush.model.WaitingQueue;
import restaurantrush.model.customer.Customer;

public abstract class Staff {

    private final String name;
    private boolean actedThisTurn;
    private int currentTurn;

    protected Staff(String name) {
        this.name = name;
    }

    /** Called by the controller at the beginning of each turn. */
    public void startTurn(int turn) {
        this.currentTurn = turn;
        this.actedThisTurn = false;
    }

    public String getName() {
        return name;
    }

    public abstract String getRole();

    public boolean hasActedThisTurn() {
        return actedThisTurn;
    }

    public boolean isAvailable() {
        return !actedThisTurn;
    }

    protected int getCurrentTurn() {
        return currentTurn;
    }

    protected final ActionResult perform(Supplier<ActionResult> action) {
        if (actedThisTurn) {
            return ActionResult.failure(getRole() + " " + name
                    + " has already acted this turn.");
        }
        ActionResult result = action.get();
        if (result.isSuccess()) {
            actedThisTurn = true;
        }
        return result;
    }

    protected ActionResult seat(Customer customer, Table table, WaitingQueue queue) {
        if (customer == null) {
            return ActionResult.failure("No customer selected.");
        }
        if (table == null) {
            return ActionResult.failure("No table selected.");
        }
        String why = customer.whyNotSeat();
        if (why != null) {
            return ActionResult.failure(why);
        }
        if (!queue.contains(customer)) {
            return ActionResult.failure(customer.label() + " is not in the waiting queue.");
        }
        if (table.isOccupied()) {
            return ActionResult.failure("Table " + table.getTableNumber() + " is occupied.");
        }
        queue.remove(customer);
        table.seatCustomer(customer);
        customer.seat(currentTurn);
        return ActionResult.success(customer.label() + " seated at table "
                + table.getTableNumber() + ".");
    }
}
