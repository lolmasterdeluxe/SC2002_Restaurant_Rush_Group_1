package restaurantrush.model.staff;

import restaurantrush.model.Order;

public class Chef extends Staff {

    public Chef(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        return "Chef";
    }

    public ActionResult prepare(Order order) {
        return perform(() -> {
            if (order == null) {
                return ActionResult.failure("No order selected.");
            }
            if (!order.isInPrep()) {
                return ActionResult.failure("Cannot cook order #" + order.getId()
                        + " (" + order.getItem().getName() + "): it is " + order.getStatus() + ".");
            }
            order.cookOneUnit();
            if (order.isReady()) {
                return ActionResult.success(order.getItem().getName() + " (order #"
                        + order.getId() + ") is READY.");
            }
            return ActionResult.success(order.getItem().getName() + " (order #" + order.getId()
                    + "): " + order.getRemainingUnits() + " preparation unit(s) left.");
        });
    }
}
