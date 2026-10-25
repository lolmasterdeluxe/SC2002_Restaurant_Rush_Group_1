# SC2002_Restaurant_Rush_Group_1
Your group builds a playable restaurant management game in Java. The player is the manager. Staff time is limited, so the manager decides which customers and orders come first. You build the game in three stages, and each stage must show how your object-oriented design supports the next set of requirements.

## CUSTOMER RULES 
We use the following deterministic rules for customer orders:

1. Customers pick their own food based on the order they arrive in.

   (The 1st customer orders the 1st item on the menu, the 2nd customer orders the 2nd item, and so on. )

2. After the last item it cycles back to the first one. e.g. With the items Salad, Burger and Pasta, customers 1 to 6 would order Salad, Burger, Pasta, Salad, Burger, Pasta.

3. The distinct order preference for the Critic is that they ignore Rule 1, and instead always order the most expensive thing on the menu (e.g. Pasta in Stage 2 and the Pasta + Salad Combo in Stage 3.)

4. For combos (added in Stage 3), since they're newly added to the BACK of the menu, following rule 1. , customer 4 would order the Burger + Salad Combo and customer 5 would order the Pasta + Salad Combo.

5. A customer arrives on turns 1, 4, 7, 10, 13 and 16. In Stage 1 they are all Regular. In Stages 2 and 3 the second customer is a VIP and the third is a Critic.


just FYI can delete this later just overall things to note ig:
1. Game isn't random, basically as long as menu is the same the run will be the same (good for reproducible wins/losses)
2. Currently we've made it such that the slowest food to cook is also for the most impatient customer (aka the critic who always orders the most ex food), so to win in Stage 3 (cos critic will pay 52 at most which is less than the 60 average needed to win the game) you either gotta do better for other customers or use the Happy Hour +15 bonus
3. VIP is like the blessing customer cos they're the easiest customer to keep happy even if their 10% discount costs some revenue
   
