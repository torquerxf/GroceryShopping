import java.util.Scanner;

class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message){
        super(message);
    }
}

class InsufficientStockException extends Exception {
    public InsufficientStockException(String message){
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

    static void filterItemsBelowPrice(String[] item, float[] price, float threshold){
        System.out.println("Items below price " + threshold + ":");
        for (int i=0;i<price.length;i++){
            if (price[i]<threshold){
                System.out.println(item[i] + " : " + price[i]);
            }
        }
    }
    
    public static void main(String[] args) {
        String[] item = new String[15]; // Array to store item names
        float[] price = new float[15]; // Array to store item prices
        int[] stock = new int[15]; // Array to store item stock quantities

    // Initializing item names, their corresponding prices, and stock quantities
    item[0] = "biscuit";      price[0] = 10.0f;   stock[0] = 50;
    item[1] = "bread";        price[1] = 25.0f;   stock[1] = 30;
    item[2] = "milk";         price[2] = 30.5f;   stock[2] = 40;
    item[3] = "eggs";         price[3] = 60.0f;   stock[3] = 100;
    item[4] = "rice";         price[4] = 50.0f;   stock[4] = 60;
    item[5] = "sugar";        price[5] = 40.0f;   stock[5] = 45;
    item[6] = "salt";         price[6] = 20.0f;   stock[6] = 70;
    item[7] = "oil";          price[7] = 120.0f;  stock[7] = 25;
    item[8] = "tea";          price[8] = 80.0f;   stock[8] = 35;
    item[9] = "coffee";       price[9] = 150.0f;  stock[9] = 20;
    item[10] = "apple";       price[10] = 90.0f;  stock[10] = 55;
    item[11] = "banana";      price[11] = 40.0f;  stock[11] = 65;
    item[12] = "tomato";      price[12] = 30.0f;  stock[12] = 80;
    item[13] = "potato";      price[13] = 25.0f;  stock[13] = 90;
    item[14] = "onion";       price[14] = 35.0f;  stock[14] = 75;

        Scanner scanner = new Scanner(System.in);

        while (true){ // outer loop to keep the program running as long as user wants
            double totalBill = 0.0d; // Variable to store total bill amount
            while(true){ // loop which runs as longs the user wants to add items
                try {
                    System.out.println("Enter the item name to get its price (or type 'Finish' to quit or type 'Filter' to filter items below your threshold): ");
                    String inputItem = scanner.nextLine();

                    // Finish input to break the inner loop
                    if (inputItem.equalsIgnoreCase("Finish")){
                        System.out.println("Total Bill Amount: " + totalBill);
                        System.out.print("Thank you for shopping with us! Type 'exit' to quit or press Enter to continue shopping: ");
                        break;
                    }

                    // Filter items below a certain price threshold
                    if (inputItem.equalsIgnoreCase("filter")){
                        System.out.println("Enter the price threshold: ");
                        float threshold = scanner.nextFloat();
                        scanner.nextLine(); // Consume the newline character
                        filterItemsBelowPrice(item, price, threshold);
                        continue; // Continue to the next iteration of the loop
                    }
                    
                    // Search for the item in the list
                    int itemIndex = -1;
                    itemIndex = searchItem(item, inputItem);
                    if (itemIndex==-1){
                        throw new ItemNotFoundException("Item: " + inputItem + " not found. Please try again.");
                    }

                    // If item found, ask for quantity
                    System.out.println("Required quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Check if the requested quantity is available in stock
                    if (quantity > stock[itemIndex]){
                        throw new InsufficientStockException("Sorry, we only have " + stock[itemIndex] + " " + item[itemIndex] + "(s) in stock. Please adjust your quantity.");
                    }
                    stock[itemIndex] -= quantity; // Deduct the purchased quantity from stock

                    // Calculate the total cost for the item and add to total bill
                    float totalCost = price[itemIndex] * quantity;
                    totalBill += totalCost;

                    // Apply discount if total bill exceeds 300
                    if (totalBill > 300){
                        totalBill *= 0.9; // Apply a 10% discount
                        System.out.println("A 10% discount has been applied to your total bill!");
                    }

                    // Calculate and display the average price of all items
                    float averagePrice = calculateAveragePrice(price);

                    // Display the cost for the current item, the average price of all items and the running total bill
                    System.out.println("Cost for " + quantity + " " + item[itemIndex] + "(s): " + totalCost);
                    System.out.println("Average Price of all items: " + averagePrice);
                    System.out.println("Running Total Bill: " + totalBill);
                    
                } catch (ItemNotFoundException ife) {
                    System.out.println(ife.getMessage());
                } catch (InsufficientStockException ise){
                    System.out.println(ise.getMessage());
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