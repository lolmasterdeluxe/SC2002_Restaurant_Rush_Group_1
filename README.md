# SC2002 Restaurant Rush — Group 1

**Restaurant Rush** is a command-line, turn-based restaurant management game written in Java. The player acts as the restaurant manager, making strategic decisions each turn to optimize staff productivity, seating capacity, kitchen output, and customer satisfaction across an 18-turn game sequence.

---

## TEAM MEMBER ROLES & TASK DIVISION

Our codebase follows a clear domain-driven 3-tier architecture (`controller`, `ui`, `model`). Responsibilities and file ownership are divided as follows:

### Member 1: Game Engine & System Control (`controller`)
* **Primary Files:** `Main.java`, `GameController.java`, `GameConfig.java`, `GameResult.java`
* **Responsibilities:** Implements the primary 18-turn game loop and coordinates the 6 turn steps (Arrivals, Choices, Kitchen/Serving, Eating/Payment, Abandonment, Result Check). Enforces game-ending victory and defeat rules.
* **UML Contribution:** Sequence Diagram (Message routing, loop control, and state transitions).

### Member 2: Front-of-House & Presentation (`ui` & Core `model`)
* **Primary Files:** `ConsoleUI.java`, `MoneyUtils.java`, `Restaurant.java`, `Table.java`, `WaitingQueue.java`
* **Responsibilities:** Manages the text presentation layer and core spatial/entity containers. Displays turn summaries, revenue, satisfaction, waiting queues, and occupied tables without embedding game rules into the UI.

### Member 3: Staff Management & Execution (`model/staff`)
* **Primary Files:** `Staff.java`, `Waiter.java`, `Chef.java`, `Cashier.java`, `Host.java`, `ActionResult.java`
* **Responsibilities:** Enforces the "one action per turn" rule for all workers. Handles seating, order-taking, cooking progress, serving, and automated cashier processing, utilizing `ActionResult` to handle invalid player commands safely.

### Member 4: Customer Lifecycle & Behaviors (`model/customer`)
* **Primary Files:** `Customer.java`, `RegularCustomer.java`, `VIPCustomer.java`, `CriticCustomer.java`, `CustomerFactory.java`, `CustomerRoster.java`, `CustomerStatus.java`
* **Responsibilities:** Models customer lifecycle states, satisfaction decay rates, abandonment thresholds, and polymorphic food choices. Manages scheduled arrivals (turns 1, 4, 7, 10, 13, and 16).

### Member 5: Food, Orders & Financial Logic (`model/menu`)
* **Primary Files:** `MenuItem.java`, `Dish.java`, `ComboMeal.java`, `Menu.java`, `Order.java`, `OrderStatus.java`, `HappyHour.java`, `PricingService.java`
* **Responsibilities:** Manages food items, preparation units, order tracking, and financial rules. Implements integer-cent arithmetic, VIP discounts, Combo pricing (90% combined), and Happy Hour activations.
* **UML Contribution:** Class Diagram (Inheritance, associations, and stage progressions).

---

## GAME MECHANICS, DYNAMICS & AESTHETICS (MDA)

### 1. Core Mechanics (Rules & Systems)
* **Turn Sequence:** Exactly 18 turns.
* **Capacity Constraints:** 2 Tables, 1 Waiter, 1 Chef, 1 Cashier (and 1 Host in Stage 3).
* **Staff Limits:**
  * **Waiter:** Executes at most 1 task per turn (Seat, Order, or Serve).
  * **Chef:** Advances 1 preparation unit of food per turn.
  * **Cashier:** Automatically processes 1 `READY_TO_PAY` customer per turn (oldest first).
  * **Host (Stage 3):** Seats 1 additional customer per turn alongside the Waiter's action.
* **Patience Decay:** Customers lose satisfaction every turn they wait for seating or food:
  * **Regular:** -8 satisfaction / turn.
  * **VIP:** -5 satisfaction / turn.
  * **Critic:** -12 satisfaction / turn.
* **Abandonment:** If a customer's satisfaction reaches 0, they leave immediately without paying. Any in-prep order is canceled, and their table is cleared.

### 2. Dynamics (Player Strategy & Behavior)
* **Staff Bottlenecking:** Because staff can only act once per turn, players must constantly prioritize between onboarding new customers (seating/ordering) versus finishing existing ones (serving/payment).
* **Kitchen Pipelining:** Players can assign the Chef to work on an order taken by the Waiter on the very same turn, reducing turnaround time.
* **Happy Hour Timing (Stage 3):** Happy Hour boosts waiting customer satisfaction by +15 (max 100) and grants 20% off orders for 2 turns. Players must time its single activation to save impatient Critics or boost total satisfaction for the win threshold.

### 3. Aesthetics (Player Experience)
* **Challenge & Pressure:** Balancing limited worker actions against dropping customer satisfaction meters creates a tense, fast-paced puzzle dynamic.
* **Tactical Satisfaction:** Efficiently chaining actions—such as seating with a Host, ordering with a Waiter, and cooking with a Chef—gives a strong sense of operational mastery.

---

## HOW TO PLAY THE GAME

### Win / Loss Conditions (End of Turn 18)
To achieve **VICTORY**, you must meet all 3 base targets at the end of Turn 18:
1. **Paid Customers:** At least **4** paid customers.
2. **Total Revenue:** At least **$45.00** ($4,500 cents).
3. **Average Satisfaction:** Paid customers must average at least **60.0** satisfaction.

Otherwise, the outcome is **DEFEAT**.

### Turn Step Sequence
Every turn executes the following 6 steps automatically:
1. **Arrivals:** Scheduled customers join the waiting queue with 100 satisfaction.
2. **Manager Choices:** You command the Waiter (Seat, Order, Serve, or Wait) and Chef (Cook Order or Wait). In Stage 3, you can also command the Host or activate Happy Hour.
3. **Kitchen & Serving:** The Waiter acts first, then the Chef cooks. A dish completed this turn becomes `READY` and can be served on a subsequent turn at the earliest.
4. **Eating & Payment:** Customers served on prior turns finish eating (`READY_TO_PAY`). The Cashier automatically collects payment from the oldest eligible customer, adding funds to revenue and clearing the table.
5. **Waiting & Abandonment:** All unserved customers lose satisfaction. Customers reaching 0 abandon the restaurant.
6. **Result Check:** Displays turn results and checks if Turn 18 has finished.

---

## CUSTOMER RULES & DETERMINISTIC ORDERING

1. **Deterministic Ordering:** Customers choose food based on their arrival index:
   * Customer 1 $\rightarrow$ Item 1 (Salad)
   * Customer 2 $\rightarrow$ Item 2 (Burger)
   * Customer 3 $\rightarrow$ Item 3 (Pasta)
   * Customer 4 $\rightarrow$ Item 4 (Burger + Salad Combo)
   * Customer 5 $\rightarrow$ Item 5 (Pasta + Salad Combo)
   * Customer 6 $\rightarrow$ Item 1 (Salad)
2. **Critic Preference:** The Critic ignores arrival order rules and always orders the most expensive available menu item (Pasta in Stage 2; Pasta + Salad Combo in Stage 3).
3. **Arrival Schedule:** Customers arrive at the start of turns **1, 4, 7, 10, 13, and 16**.
   * **Stage 1:** 6 Regular Customers.
   * **Stages 2 & 3:** Customer 1 (Regular), Customer 2 (VIP), Customer 3 (Critic), Customers 4–6 (Regular).

---

## BUNDLING CODEBASE FOR AI ASSISTANCE (SINGLE FILE GENERATION)

When submitting the codebase to an AI assistant (e.g., Gemini/ChatGPT) to bypass attachment limits or review multiple files across sub-packages simultaneously, follow these steps to generate a consolidated `CombinedCode.txt` file.

### Step 1: Open Terminal in Project Root
Navigate to the root directory of your project (where the `src` folder is located) in your terminal or IDE (VS Code / IntelliJ).

### Step 2: Run the Bundling Command

* **On Windows (PowerShell):**
  ```powershell
  Get-ChildItem -Recurse -Filter *.java | ForEach-Object { "=== FILE: $($_.FullName) ==="; Get-Content -LiteralPath $_.FullName; "`n" } | Out-File -FilePath CombinedCode.txt -Encoding utf8
  ```

* **On macOS / Linux (Bash Terminal):**
  ```bash
  find . -name "*.java" -exec echo "=== FILE: {} ===" \; -exec cat {} \; -exec echo -e "\n" \; > CombinedCode.txt
  ```

### Step 3: Attach `CombinedCode.txt`
Upload the generated `CombinedCode.txt` file directly into your AI prompt. The AI can now inspect all files, sub-packages, and logic across the entire repository simultaneously.