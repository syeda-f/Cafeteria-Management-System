
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @authors arwa, fager, syeda, zainab
*/


public class CSIIproject {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Administrator admin = new Administrator();
    
        while (true) {
            System.out.println();
            System.out.println("MAIN MENU");
            System.out.println();
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Enter your choice (1-3): ");
            int mainChoice = input.nextInt();
            input.nextLine();

            switch (mainChoice) {
                case 1:
                    adminMenu(input, admin);
                    break;
                case 2:
                    userMenu(input, admin);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    admin.emptyFile(); 
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }  
    }

    public static void adminMenu(Scanner input, Administrator admin) {
        while (true) {
            System.out.println();
            System.out.println("1. Drop down menu for Admin");
            System.out.println("2. Go back to Main Menu");
            System.out.println();
            System.out.print("Enter your choice (1 or 2): ");
            int adminChoice = input.nextInt();
            input.nextLine(); 

            switch (adminChoice) {
                case 1:
                    displayAdminMenu(input, admin);
                    break;
                case 2:
                    return; 
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void userMenu(Scanner input, Administrator admin) {
        User user = new User();
        while (true) {
            System.out.println();
            System.out.println("1. Drop down menu for User");
            System.out.println("2. Go back to Main Menu");
            System.out.println();
            System.out.print("Enter your choice (1 or 2): ");
            int userChoice = input.nextInt();
            input.nextLine(); 

            switch (userChoice) {
                case 1:
                    displayUserMenu(input, user, admin); 
                    break;
                case 2:
                    return; 
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void displayAdminMenu(Scanner input, Administrator admin) {
        while (true) {
            System.out.println();
            System.out.println("Admin Menu");
            System.out.println();
            System.out.println("a. Add new food and its information");
            System.out.println("b. Delete the information of the food");
            System.out.println("c. Search for a particular food variety");
            System.out.println("d. Check the number of food varieties");
            System.out.println("e. Generate reports of food");
            System.out.println("f. Go back to menu");
            System.out.println();
            System.out.print("Enter your choice (a-f): ");
            String adminChoice = input.nextLine();
            System.out.println();

            switch (adminChoice.toLowerCase()) {
                case "a":
                System.out.println();
                System.out.println("Add new food and its information: ");
                System.out.println("1. Add new food");
                System.out.println("2. Display pending requests");
                System.out.println("3. Add pending requests to menu");
                System.out.println();
                System.out.print("enter choice (1-3): ");
                int addChoice = input.nextInt();
                input.nextLine(); 
                switch (addChoice) {
                    case 1:
                        admin.displayFoodDetails();

                        System.out.print("Enter unique food code: ");
                        String code = input.nextLine(); 
                        System.out.print("Enter food title: ");
                        String title = input.nextLine();
                        System.out.print("Enter country of origin: ");
                        String origin = input.nextLine();
                        System.out.print("Enter variety type: ");
                        String variety = input.nextLine();  
                        System.out.print("Enter calories: ");
                        int calories = input.nextInt();
                        System.out.print("Enter price: ");
                        double price = input.nextDouble();
                        input.nextLine();
                        System.out.println();
                        admin.addFood(code, title, origin, variety, calories, price);
                        break;
                    case 2:
                        admin.displayUserPendingRequests();
                        break;
                    case 3:
                        admin.choosePendingRequestToAddToMenu(input);
                        break;
                    default:
                        System.out.println("invalid choice.");
                    }
                    break;
                case "b":
                    listnode cur = admin.head; 
                    while (cur != null) { 
                        listnode pre_code = admin.head; 
                        boolean found_code = false; 
                        while (pre_code != cur) { 
                            if (pre_code.data.getCode().equals(cur.data.getCode())) {
                                found_code = true;
                                break;
                            }
                            pre_code = pre_code.link;
                        }
                        if (!found_code) { 
                            admin.displayFoodDetails();
                        }
                        cur = cur.link; 
                    }
                    System.out.println();
                    System.out.print("Enter code of food to be deleted: ");
                    String food_code = input.nextLine();
                    admin.deleteFood(food_code);
                    break;
                case "c":
                System.out.println("Varieties:");
                    listnode current = admin.head; 
                    while (current != null) {
                        listnode pre_var = admin.head; 
                        boolean found_var = false; 
                        while (pre_var != current) { 
                            if(pre_var.data.getVariety().equalsIgnoreCase(current.data.getVariety())) {
                                found_var = true;
                                break;
                            }
                            pre_var = pre_var.link;
                        }
                        if (!found_var) { 
                            System.out.println("• " + current.data.getVariety());
                        }
                        current = current.link; 
                    }
                    System.out.println();
                    System.out.print("Enter food variety to search: ");
                    String food_var = input.nextLine();
                    if (admin.searchVariety(food_var)) {
                        System.out.println("Variety type " + food_var + " found");
                    } else {
                        System.out.println("Variety type " + food_var + " not found");
                    }
                    System.out.println();
                    break;
                case "d":
                    admin.displayTotalVariety();
                    break;
                case "e":
                    System.out.println();
                    System.out.println("Generate reports based on:");
                    System.out.println("1. Food Title");
                    System.out.println("2. Food Origin");
                    System.out.println("3. Food Variety type");
                    System.out.println("4. Food Calorie content");
                    System.out.println("5. Food Price");
                    System.out.println();

                    System.out.print("enter choice (1-5): ");
                    int repChoice = input.nextInt();
                    input.nextLine(); 
                    switch (repChoice) {
                        case 1:
                            admin.generateReport("title");
                            break;
                        case 2:
                            admin.generateReport("origin");
                            break;
                        case 3:
                            admin.generateReport("variety");
                            break;
                        case 4:
                            admin.generateReport("calories");
                            break;
                        case 5:
                            admin.generateReport("price");
                            break;
                        default:
                            System.out.println("invalid choice.");
                        }
                    break;
                case "f":
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            } 
        }

    public static void displayUserMenu(Scanner input, User user, Administrator admin) {
        while (true) {
            System.out.println();
            System.out.println("User Menu");
            System.out.println();
            System.out.println("a. Inquire about food");
            System.out.println("b. Add request");
            System.out.println("c. Add Complaint");
            System.out.println("d. Go back to menu");
            System.out.println();
            System.out.print("Enter your choice (a-d): ");
            String userChoice = input.nextLine();
            System.out.println();
        
            switch (userChoice.toLowerCase()) {
                case "a":
                    admin.displayFoodDetails();
                    user.inquireFood(input, admin);
                    break;
                case "b":
                    admin.requestNewFood(input);     
                    System.out.println("Do you want something else: (yes / no)");
                    String req = input.next();
                    if(req.equalsIgnoreCase("yes")) {
                        Scanner input2 = new Scanner(System.in);
                        admin.requestNewFood(input2);
                    } 
                    break;
                case "c":
                    System.out.println("Complain");
                    System.out.println("Food Title --> ");
                    String title = input.nextLine();
                    boolean found = false; 
                    listnode current = admin.head; 
                    while (current != null) { 
                        if (current.data.getTitle().equalsIgnoreCase(title)) {
                            found = true; 
                            break; 
                        }
                        current = current.link; 
                    }
                    if (found) {
                        System.out.println("Food item exists");
                    } else {
                        System.out.println("Sorry, the food title is not available. Please check your spelling or inquire about the food first.");
                        System.out.println("Food item Complain title --> ");
                        String complaint = input.nextLine();
                        System.out.println("Complaint Added Successfully");
                    }
                    break;
                case "d":
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}


class food {
    private String code; 
    private String title; 
    private String origin; 
    private String variety; 
    private int calories; 
    private double price; 

    public food(String code, String title, String origin, String variety, int calories, double price) {
        this.code = code;
        this.title = title;
        this.origin = origin;
        this.variety = variety;
        this.calories = calories;
        this.price = price;
    }

    //setters for data members
    void setCode(String code) {
        this.code = code;
    }
    void setTitle(String title) {
        this.title = title;
    }
    void setOrigin(String origin) {
        this.origin = origin;
    }
    void setVariety(String variety) {
        this.variety = variety;
    }
    void setCal(int calories) {
        this.calories = calories;
    }
    void setPrice(double price) {
        this.price = price;
    }

    //getters for data members
    String getCode() {
        return code;
    }
    String getTitle() {
        return title;
    }
    String getOrigin() {
        return origin;
    }
    String getVariety() {
        return variety;
    }
    int getCal() {
        return calories;
    }
    double getPrice() {
        return price;
    }

    public String toString() {
        return "Code: " + code + ", Title: " + title + ", Country of Origin: " + origin + ", Variety Type: " + variety +", Calories: " + calories + ", Price: SR " + price;
    }
}


class listnode {
    food data; 
    listnode link; 

    public listnode(food data, listnode link) {
        this.data = data;
        this.link = link;
    }
}


class Administrator {

    listnode head;
    public final String fileName = "food_details.txt"; 

    //Method to add new food details
    public void addFood(String code, String title, String origin, String variety, int calories, double price) {
        
        food newFood = new food(code, title, origin, variety, calories, price); 
        listnode newNode = new listnode(newFood, null); 

        if (head == null) {
            head = newNode; 
        } else {
            listnode cur = head; 
            while (cur.link != null) { 
                cur = cur.link;
            }
            cur.link = newNode; 
        }

        try {
            FileWriter fw = new FileWriter(fileName, true); 
            BufferedWriter bw = new BufferedWriter(fw); 
            bw.write(newFood.getCode() + ", " + newFood.getTitle() + ", " + newFood.getOrigin() + ", " + newFood.getVariety() + ", " +newFood.getCal() + ", " + newFood.getPrice()); // write the information of the new food
            bw.newLine(); 
            bw.close(); 
            System.out.println("New food added: " + newFood);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }     

    //Method to delete food according to code enetered 
    public void deleteFood(String foodCode) {
        foodCode = foodCode.toLowerCase(); 
        listnode cur = head;
        listnode prev = null; 
        boolean found = false; 

        while (cur != null) { 
            if (cur.data.getCode().equalsIgnoreCase(foodCode)) { 
                found = true; 
                break;
            }
            prev = cur; 
            cur = cur.link; 
        }

        if (found) {
            if (prev == null) { 
                head = cur.link; 
            } else { 
                prev.link = cur.link; 
            }
            System.out.println(foodCode + " - This Food Is Deleted From the Menu successfully.");
        } else {
            System.out.println(foodCode + " - This Food is not found.");
        }

        try {
            FileWriter fw = new FileWriter(fileName, false); 
            BufferedWriter bw = new BufferedWriter(fw);
    
            listnode current = head;
            while (current != null) {
                bw.write(current.data.getCode() + ", " + current.data.getTitle() + ", " + current.data.getOrigin() + ", " + current.data.getVariety() + ", " + current.data.getCal() + ", " + current.data.getPrice());
                bw.newLine();
                current = current.link;
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        } 
    }

    //Method to search for a specific food variety type
    public boolean searchVariety(String food_var) {
        food_var = food_var.toLowerCase();   
        System.out.println("Food variety type: " + food_var);
        listnode current = head; 
        boolean found_var = false;
        while (current != null) { 
            if (food_var.equalsIgnoreCase(current.data.getVariety())) {
                System.out.println("• " + current.data); 
                found_var = true;
            }
            current = current.link; 
        }
        return found_var;
    }
    

    //Method to calculate and display total number of different food varieties
    public void displayTotalVariety() {
        int total = 0; 
        listnode cur = head; 
        while (cur.link != null) {  
            listnode pre_var = head; 
            boolean found_var = false; 
            while (pre_var != cur) { 
                if (cur.data.getVariety().equalsIgnoreCase(pre_var.data.getVariety())) {
                    found_var = true; 
                    break; 
                }
                pre_var = pre_var.link; 
            }
            if (!found_var) { 
                total++;
            }
            cur = cur.link; 
        }
        System.out.println("Total number of different food varieties: " + total);
    }

    //Method to generate reports based on FOOD Title, Origin, Variety, Calories, Price
    public void generateReport(String choice) {
        switch (choice.toLowerCase()) {
            case "title":
                System.out.println("Report based on title:");
                repTitle();
                break;
            case "origin":
                System.out.println("Report based on origin:");
                repOrg();
                break;
            case "variety":
                System.out.println("Report based on variety:");
                repVar();
                break;
            case "calories":
                System.out.println("Report based on calorie content:");
                repCal();
                break;
            case "price":
                System.out.println("Report based on price:");
                repPri();
                break;
            default:
                System.out.println("invalid choice.");
        }
    }

    //helper method for generateReport(String choice)
    private void repTitle() {
    listnode cur = head; 
        while (cur != null) { 
            System.out.println("• " + cur.data.getTitle() + " ---> " + cur.data);
            cur = cur.link; 
        }
    }

    //helper method for generateReport(String choice)
    private void repOrg() {
        listnode cur = head;
        while (cur != null) {
            System.out.println("• " + cur.data.getOrigin() + " -->> Title: " + cur.data.getTitle());
            cur = cur.link;
        }
    }

    //helper method for generateReport(String choice)
    private void repVar() {
        listnode cur = head;
        while (cur != null) {
            System.out.println("• " + cur.data.getVariety() + " -->> Title: " + cur.data.getTitle());
            cur = cur.link;
        }
    }

    //helper method for generateReport(String choice)
    private void repCal() {
        listnode cur = head;
        while (cur != null) {
            System.out.println("• " + cur.data.getCal() + " -->> Title: " + cur.data.getTitle());
            cur = cur.link;
        }
    }

    //helper method for generateReport(String choice)
    private void repPri() {
        listnode cur = head;
        while (cur != null) {
            System.out.println("• " + cur.data.getPrice() + " SR -->> Title: " + cur.data.getTitle());
            cur = cur.link;
        }
    }
    
    //Method to empty food details from txt file when exiting application
    public void emptyFile() {
        try {
            FileWriter fw = new FileWriter(fileName, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(" "); 
            bw.close();
            System.out.println("food details file emptied.");
            
        } catch (IOException e) {
            System.out.println("error emptying file: " + e.getMessage());
        }
    }

    public void displayFoodDetails() {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr); 
            String line;
    
            System.out.println("Food Details:");
            while ((line = br.readLine()) != null) { 
                if (!line.trim().isEmpty()) { 
                    String[] food_data = line.split(", "); 
                    System.out.print("Code: " + food_data[0].trim() + ", Title: " + food_data[1].trim());
                    System.out.println();
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //Method to request new variety of food
    public void requestNewFood(Scanner input) {
        System.out.println("Please enter details of the new food variety:");
        System.out.print("Variety: ");
        String variety = input.nextLine(); 

        boolean existsInMenu = checkFoodExistenceByVariety(variety);
        if (existsInMenu) {
            System.out.println("The requested food variety already exists in the menu.");
        } else {
            addPendingRequest(variety); 
            System.out.println("Request for new food variety '" + variety + "' submitted successfully.");
        }
    }

    public listnode addPendingRequest(String variety) {
        listnode newNode = new listnode(new food("----", "----", "----", variety, 0, 0.0), null);
        if (head == null) {
            head = newNode;
        } else {
            listnode current = head;
            while (current.link != null) {
                current = current.link;
            }
            listnode previous=current;
            previous=current.link;
            newNode.link=previous;
            current.link=newNode;
        }
        return head;
    }

    public void choosePendingRequestToAddToMenu(Scanner input) {
        System.out.println("Choose a pending request to add to the menu (enter the corresponding number):");

        int count = 0;
        listnode current = head;
        while (current != null) {
            if (current.data.getCode().equals("----")) {
                System.out.println(++count + ". " + current.data);
            }
            current = current.link;
        }

        if (count == 0) {
            System.out.println("No pending requests found.");
            return;
        }

        int choice;
        do {
            System.out.print("Enter your choice (1-" + count + "): ");
            choice = input.nextInt();
            input.nextLine(); 
        } while (choice < 1 || choice > count);

        current = head;
        int userChoice = 0;
        while (current != null && userChoice < choice) {
            if (current.data.getCode().equals("----")) {
                userChoice++;
            }
            if (userChoice != choice) {
                current = current.link;
            }
        }

        if (current == null) {
            System.out.println("Invalid choice. No pending request found.");
            return;
        }

        System.out.println("Enter the details for '" + current.data.getVariety() + "':");
        System.out.print("Enter unique food code: ");
        String code = input.nextLine();
        System.out.print("Enter food title: ");
        String title = input.nextLine();
        System.out.print("Enter country of origin: ");
        String origin = input.nextLine();
        String varietyType = current.data.getVariety(); 
        System.out.print("Enter calories: ");
        int calories = input.nextInt();
        System.out.print("Enter price: ");
        double price = input.nextDouble();
        input.nextLine(); 

        food newFood = new food(code, title, origin, varietyType, calories, price);
        addFood(newFood.getCode(), newFood.getTitle(), newFood.getOrigin(),
                newFood.getVariety(), newFood.getCal(), newFood.getPrice());
        System.out.println("Added '" + newFood.getTitle() + "' to the menu.");

        removePendingRequest(current);
    }

   
    public int displayUserPendingRequests() {
        System.out.println("Pending Requests:");
        int counter = 0;
        listnode current = head;
        while (current != null) {
            if (current.data.getCode().equals("----")) {
                counter++;
                System.out.println(counter + ". " + current.data);
            }
            current = current.link;
        }
        if (counter == 0) {
            System.out.println("No pending requests found.");
        }
        return counter;
    }
    
    
    public void removePendingRequest(listnode nodeToRemove) {
        if (nodeToRemove == head) {
            head = head.link;
        } else {
            listnode current = head;
            while (current.link != null && current.link.data != nodeToRemove.data) {
                current = current.link;
            }
            if (current.link != null) {
                current.link = current.link.link;
            }
        }
    }

    public boolean checkFoodExistenceByVariety(String Variety) {
        listnode current = head;
        while (current != null) {
            if (current.data.getVariety().equalsIgnoreCase(Variety)) {
                return true; 
            }
            current = current.link;
        }
        return false; 
    }
}


class User {

    listnode head;

    //Method to inquire about food 
    public void inquireFood(Scanner input, Administrator admin) {
        System.out.print("Enter code of food: ");
        String foodCode = input.nextLine();

        boolean found = false; 
        listnode current = admin.head; 
        
        while (current != null) {
            if (current.data.getCode().equalsIgnoreCase(foodCode)) {            
                System.out.println("Food Item Details:");
                System.out.println(current.data.toString());
                found = true; 
                break;
            }
            current = current.link; 
        }

        if (!found) { 
            System.out.println("The food is not in the menu.");
        }
    }
}
