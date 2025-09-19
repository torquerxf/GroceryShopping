import java.util.Scanner;

class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message){
        super(message);
    }
}

public class GroceryShopping {

    static int searchItem(String[] item, String inputItem){
        int itemIndex = -1;
        for (int i=0;i<item.length;i++){
            if (item[i].equalsIgnoreCase(inputItem)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    static float calculateAveragePrice(float[] price){
        float sum = 0.0f;
        for (float p : price){
            sum += p;
        }
        return sum/price.length;
    }
    
    public static void main(String[] args) {
        String[] item = new String[15]; // Array to store item names
        float[] price = new float[15]; // Array to store item prices

        // Initializing item names and their corresponding prices
        item[0] = "biscuit";      price[0] = 10.0f;
        item[1] = "bread";        price[1] = 25.0f;
        item[2] = "milk";         price[2] = 30.5f;
        item[3] = "eggs";         price[3] = 60.0f;
        item[4] = "rice";         price[4] = 50.0f;
        item[5] = "sugar";        price[5] = 40.0f;
        item[6] = "salt";         price[6] = 20.0f;
        item[7] = "oil";          price[7] = 120.0f;
        item[8] = "tea";          price[8] = 80.0f;
        item[9] = "coffee";       price[9] = 150.0f;
        item[10] = "apple";       price[10] = 90.0f;
        item[11] = "banana";      price[11] = 40.0f;
        item[12] = "tomato";      price[12] = 30.0f;
        item[13] = "potato";      price[13] = 25.0f;
        item[14] = "onion";       price[14] = 35.0f;

        Scanner scanner = new Scanner(System.in);

        while (true){ // outer loop to keep the program running as long as user wants
            double totalBill = 0.0d; // Variable to store total bill amount
            while(true){ // loop which runs as longs the user wants to add items
                try {
                    System.out.println("Enter the item name to get its price (or type 'Finish' to quit): ");
                    String inputItem = scanner.nextLine();

                    // Finish input to break the inner loop
                    if (inputItem.equalsIgnoreCase("Finish")){
                        System.out.println("Total Bill Amount: " + totalBill);
                        System.out.print("Thank you for shopping with us! Type 'exit' to quit or press Enter to continue shopping: ");
                        break;
                    }

                    int itemIndex = -1;
                    itemIndex = searchItem(item, inputItem);
                    if (itemIndex==-1){
                        throw new ItemNotFoundException("Item: " + inputItem + " not found. Please try again.");
                    }

                    // If item found, ask for quantity
                    System.out.println("Required quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Calculate the total cost for the item and add to total bill
                    float totalCost = price[itemIndex] * quantity;
                    totalBill += totalCost;

                    // Calculate and display the average price of all items
                    float averagePrice = calculateAveragePrice(price);

                    // Display the cost for the current item, the average price of all items and the running total bill
                    System.out.println("Cost for " + quantity + " " + item[itemIndex] + "(s): " + totalCost);
                    System.out.println("Average Price of all items: " + averagePrice);
                    System.out.println("Running Total Bill: " + totalBill);
                    
                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again. ");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")){
                System.out.println("Exiting the program. Have a great day!");
                break;
            }
        }
        scanner.close();
    }
}