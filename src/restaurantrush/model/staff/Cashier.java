package restaurantrush.model.staff;

import java.util.List;

import restaurantrush.model.Restaurant;
import restaurantrush.model.Table;
import restaurantrush.model.customer.Customer;
import restaurantrush.util.MoneyUtils;

public class Cashier extends Staff {

    public Cashier(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        return "Cashier";
    }

    /** @return failure (not an error) when nobody can be charged this turn */
    public ActionResult processPayment(Restaurant restaurant) {
        return perform(() -> {
            List<Customer> ready = restaurant.getRoster().readyToPay(getCurrentTurn());
            if (ready.isEmpty()) {
                return ActionResult.failure("No customer is ready to pay.");
            }
            Customer next = ready.get(0);
            Table table = restaurant.findTableWithCustomer(next);
            int bill = next.pay(getCurrentTurn()); // order -> PAID, satisfaction recorded
            restaurant.addRevenue(bill);
            if (table != null) {
                table.clearTable();
            }
            return ActionResult.success(next.label() + " paid " + MoneyUtils.formatCents(bill)
                    + " (satisfaction " + next.getRecordedSatisfaction() + ").");
        });
    }
}
