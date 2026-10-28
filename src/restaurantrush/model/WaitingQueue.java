package restaurantrush.model;

import java.util.ArrayList;
import java.util.List;

import restaurantrush.model.customer.Customer;

public class WaitingQueue {
    private final List<Customer> waiting = new ArrayList<>();

    public void join(Customer customer) {
        if (customer != null && !waiting.contains(customer)) {
            waiting.add(customer);
        }
    }

    public Customer get(int position) {
        if (position < 1 || position > waiting.size()) {
            return null;
        }
        return waiting.get(position - 1);
    }

    public boolean remove(Customer customer) {
        return waiting.remove(customer);
    }

    public List<Customer> removeDeparted() {
        List<Customer> gone = new ArrayList<>();
        for (Customer c : waiting) {
            if (c.hasLeftUnhappy()) {
                gone.add(c);
            }
        }
        waiting.removeAll(gone);
        return gone;
    }

    public boolean contains(Customer customer) {
        return waiting.contains(customer);
    }

    public int size() {
        return waiting.size();
    }

    public boolean isEmpty() {
        return waiting.isEmpty();
    }

    public List<Customer> asList() {
        return new ArrayList<>(waiting);
    }
}