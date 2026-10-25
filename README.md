# SC2002_Restaurant_Rush_Group_1
Your group builds a playable restaurant management game in Java. The player is the manager. Staff time is limited, so the manager decides which customers and orders come first. You build the game in three stages, and each stage must show how your object-oriented design supports the next set of requirements.

## CUSTOMER RULES

1. Customers pick their own food based on the order they arrive in.

   (The 1st customer orders the 1st item on the menu, the 2nd customer orders the 2nd item, and so on. )

2. After the last item it goes back to the first one. e.g. With the items Salad, Burger and Pasta, customers 1 to 6 would order Salad, Burger, Pasta, Salad, Burger, Pasta.

3. Critic doesn't follow this and instead always order the most expensive thing on the menu (which is Pasta in Stage 2 and the Pasta + Salad Combo in Stage 3.)

4. In Stage 3 the two combos are added to the end of the menu, so customer 4 orders the Burger + Salad Combo and customer 5 orders the Pasta + Salad Combo.

5. A customer arrives on turns 1, 4, 7, 10, 13 and 16. In Stage 1 they are all Regular. In Stages 2 and 3 the second customer is a VIP and the third is a Critic.
