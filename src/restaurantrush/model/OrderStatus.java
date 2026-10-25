package restaurantrush.model;

/** The stages an order moves through. */
public enum OrderStatus {
    IN_PREP,    // taken by the Waiter, Chef is cooking it
    READY,      // all prep units done, waiting for the Waiter to serve
    SERVED,     // delivered to the table, customer is eating
    PAID,       // Cashier has charged the bill
    CANCELLED   // customer left before paying
}
