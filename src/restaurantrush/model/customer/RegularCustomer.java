package restaurantrush.model.customer;

public class RegularCustomer extends Customer {

    public RegularCustomer(int id, int arrivalTurn) {
        super(id, arrivalTurn);
    }

    @Override
    public String getTypeName() {
        return "Regular";
    }

    @Override
    public int getSatisfactionLossPerTurn() {
        return 8;
    }
}
