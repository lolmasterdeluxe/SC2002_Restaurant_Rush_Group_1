package restaurantrush.model;

import restaurantrush.model.customer.Customer;

public class Table {
    private final int tableNumber;
    private Customer currentCustomer;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.currentCustomer = null;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public boolean isOccupied() {
        return currentCustomer != null;
    }

    /**
     * Binds a customer to this table reference.
     * State transition (e.g., customer.seat(turn)) is handled by Customer/Waiter.
     */
    public void seatCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    public Customer clearTable() {
        Customer temp = this.currentCustomer;
        this.currentCustomer = null;
        return temp;
    }
}