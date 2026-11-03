package restaurantrush.controller;

import restaurantrush.model.Restaurant;
import restaurantrush.util.MoneyUtils;

public class GameResult {
    /*win condition default at least 4 paid customers AND revenue at least $45 AND average
    satisfaction of paid customers at least 60 in GameConfig*/

    private Restaurant restaurant;
    private boolean win;

    public GameResult(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.win = false;
    }

    public boolean checkWin() {
        int paid = restaurant.getRoster().paidCount();
        int revenue = restaurant.getTotalRevenueCents();
        double avgSatisfaction = restaurant.getRoster().averagePaidSatisfaction();
        win = paid >= GameConfig.getPaidCustomersGoal() && revenue >= GameConfig.getRevenueGoal() && avgSatisfaction >= GameConfig.getAvgSatisfactionGoal();
        return win;
    }

    public String resultMessage() {
        if (checkWin()) {
            return "Victory! Restaurant managed.";
        } else {
            String reason = "";
            if (restaurant.getRoster().paidCount() < GameConfig.getPaidCustomersGoal()) {
                reason += restaurant.getRoster().paidCount() + " paid customers out of " + GameConfig.getPaidCustomersGoal() + ". ";
            }
            if (restaurant.getTotalRevenueCents() < GameConfig.getRevenueGoal()) {
                reason += MoneyUtils.formatCents(restaurant.getTotalRevenueCents()) + " revenue out of " + MoneyUtils.formatCents(GameConfig.getRevenueGoal()) + ". ";
            }
            if (restaurant.getRoster().averagePaidSatisfaction() < GameConfig.getAvgSatisfactionGoal()) {
                reason += restaurant.getRoster().averagePaidSatisfactionText() + " average satisfaction out of " + GameConfig.getAvgSatisfactionGoal() + ".";
            }
            return "Defeat! Back to business school. " + reason;
        }
    }
}
