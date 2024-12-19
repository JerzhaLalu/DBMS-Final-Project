import java.sql.*;
import java.util.*;
import java.time.LocalDate;

class DBConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/finalprojectdatabase";
        String username = "root";
        String password = "INSERTPASSWORDHERE";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Bakery.menu(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Bakery {

    private static Map<String, String> productCatalog = new HashMap<>();

    static {
        productCatalog.put("Sourdough Bread", "P101");
        productCatalog.put("Baguette", "P102");
        productCatalog.put("Focaccia", "P103");
        productCatalog.put("Banana Bread", "P104");
        productCatalog.put("Whole Wheat Bread", "P105");

        productCatalog.put("Plain Croissant", "P106");
        productCatalog.put("Apple Pie Croissant", "P107");
        productCatalog.put("Chocolate Croissant", "P108");
        productCatalog.put("Pain au Chocolat", "P109");
        productCatalog.put("Cinnamon Roll", "P110");

        productCatalog.put("Classic Vanilla Cake 6 inch", "P115");
        productCatalog.put("Classic Vanilla Cake 7 inch", "P116");
        productCatalog.put("Classic Vanilla Cake 8 inch", "P117");

        productCatalog.put("Chocolate Fudge Cake 6 inch", "P118");
        productCatalog.put("Chocolate Fudge Cake 7 inch", "P119");
        productCatalog.put("Chocolate Fudge Cake 8 inch", "P120");

        productCatalog.put("Carrot Cake 6 inch", "P121");
        productCatalog.put("Carrot Cake 7 inch", "P122");
        productCatalog.put("Carrot Cake 8 inch", "P123");

        productCatalog.put("Matcha Green Tea Cake 6 inch", "P124");
        productCatalog.put("Matcha Green Tea Cake 7 inch", "P125");
        productCatalog.put("Matcha Green Tea Cake 8 inch", "P126");

        productCatalog.put("Tiramisu Cake 6 inch", "P127");
        productCatalog.put("Tiramisu Cake 7 inch", "P128");
        productCatalog.put("Tiramisu Cake 8 inch", "P129");

        productCatalog.put("Ube Oreo Cheesecake 5 inch", "P130");
        productCatalog.put("Ube Oreo Cheesecake 6 inch", "P131");
        productCatalog.put("Ube Oreo Cheesecake 8 inch", "P132");

        productCatalog.put("Matcha Oreo Cheesecake 5 inch", "P133");
        productCatalog.put("Matcha Oreo Cheesecake 6 inch", "P134");
        productCatalog.put("Matcha Oreo Cheesecake 8 inch", "P135");

        productCatalog.put("Blueberry Cheesecake 5 inch", "P136");
        productCatalog.put("Blueberry Cheesecake 6 inch", "P137");
        productCatalog.put("Blueberry Cheesecake 8 inch", "P139");

        productCatalog.put("Dark Chocolate Cheesecake 5 inch", "P140");
        productCatalog.put("Dark Chocolate Cheesecake 6 inch", "P141");
        productCatalog.put("Dark Chocolate Cheesecake 8 inch", "P142");

        productCatalog.put("Biscoff Cheesecake 5 inch", "P143");
        productCatalog.put("Biscoff Cheesecake 6 inch", "P144");
        productCatalog.put("Biscoff Cheesecake 8 inch", "P145");
    }

    public static void logo() {
        System.out.println("\n================================================================");

        System.out.println("");
        System.out.println("    \r\n" + //
                            "  ░█▀▀▀█ █▀▀█ ░█▀▀▀█ █──█ █▀▀█ 　 ░█▀▀█ █▀▀█ █─█ █▀▀ █▀▀█ █──█ \r\n" + //
                            "  ─▄▄▄▀▀ █▄▄█ ─▄▄▄▀▀ █▀▀█ █▄▄█ 　 ░█▀▀▄ █▄▄█ █▀▄ █▀▀ █▄▄▀ █▄▄█ \r\n" + //
                            "  ░█▄▄▄█ ▀──▀ ░█▄▄▄█ ▀──▀ ▀──▀ 　 ░█▄▄█ ▀──▀ ▀─▀ ▀▀▀ ▀─▀▀ ▄▄▄█");
        System.out.println("");
        System.out.println("\n================================================================\n");

    }

    public static void menu(Connection connection) {
        Scanner input = new Scanner(System.in);


        logo();

        System.out.println("\t=================== Order Form ===================");
        System.out.print("\tCustomer Name: ");
        String customerName = input.nextLine();

        String contactNumber;
        while (true) {
            System.out.print("\tContact Number (11 digits): ");
            contactNumber = input.nextLine();
            if (contactNumber.length() == 11 && contactNumber.matches("\\d+")) {
                break;
            } else {
                System.out.println("\tInvalid number. Please enter an 11-digit number.");
            }
        }

        String orderType;
    String shippingAddress = null;
    System.out.print("\t[1] Dine In [2] To Go [3] Delivery: ");
    int dineOrGo = input.nextInt();
    input.nextLine();
    if (dineOrGo == 3) {
        orderType = "Delivery";
        System.out.print("\tShipping Address: ");
        shippingAddress = input.nextLine();
    } else {
        orderType = (dineOrGo == 1) ? "Dine In" : "To Go";
    }

    int customerId = saveCustomer(connection, customerName, contactNumber, shippingAddress);

    if (customerId > 0) {
        System.out.println("\n\tCustomer saved. You can now place an order.");
        menuItems();
        orderMenu(connection, customerId, customerName, orderType);
    } else {
        System.out.println("Failed to save customer.");
    }

    input.close();
}
    
    private static int saveCustomer(Connection connection, String name, String contact, String address) {
        String query = "INSERT INTO Customer (CustomerName, ContactNumber, ShippingAddress) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, contact);
            pstmt.setString(3, address);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = pstmt.getGeneratedKeys()) {
                    if (keys.next()) return keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void menuItems() {
        System.out.println("\n================================================================\n");
        System.out.println("\n                                           Prices\n" +
                "\t    -- Breads --\n" +
                "\t[1]    Sourdough Bread                250.00\n" +
                "\t[2]    Baguette                       200.00\n" +
                "\t[3]    Focaccia                       250.00\n" +
                "\t[4]    Banana Bread                   180.00\n" +
                "\t[5]    Whole Wheat Bread              200.00\n" +
                "\n" +
                "\t    -- Sweet Pastries --\n" +
                "\t[6]    Plain Croissant                100.00\n" +
                "\t[7]    Apple Pie Croissant            150.00\n" +
                "\t[8]    Chocolate Croissant            120.00\n" +
                "\t[9]    Pain au Chocolat               120.00\n" +
                "\t[10]   Cinnamon Roll                  130.00\n" +
                "\n" +
                "\t    -- Cakes --\n" +
                "                                      6\"      7\"      8\"\n" +
                "[11]   Classic Vanilla              500.00   600.00  700.00\n" +
                "[12]   Chocolate Fudge              500.00   600.00  700.00\n" +
                "[13]   Carrot Cake                  550.00   650.00  750.00\n" +
                "[14]   Matcha Green Tea             600.00   700.00  800.00\n" +
                "[15]   Tiramisu Cake                600.00   700.00  800.00\n" +
                "\n" +
                "\t    -- Cheesecake --\n" +
                "                                      5\"      6\"      8\"\n" +
                "[16]   Ube Oreo Cheesecake          469.00   519.00  950.00\n" +
                "[17]   Matcha Oreo Cheesecake       469.00   519.00  950.00\n" +
                "[18]   Blueberry Cheesecake         469.00   519.00  1199.00\n" +
                "[19]   Dark Chocolate Cheesecake    469.00   519.00  950.00\n" +
                "[20]   Biscoff Cheesecake           419.00   559.00  1199.00\n");
    }

    public static void orderMenu(Connection connection, int customerId, String customerName, String orderType) {
        Scanner input = new Scanner(System.in);
        LocalDate orderDate = LocalDate.now();

        double grandTotal = 0.0;

        while (true) {
            System.out.print("Enter item number (or 0 to finish): ");
            int item = input.nextInt();
            if (item == 0) break;

            if (item < 1 || item > 20) {
                System.out.println("Invalid item number. Please try again.");
                continue;
            }

            double price = 0.0;
            String itemName = "";
            String productId = "";
            String size = "";

            switch (item) {
                case 1:
                    itemName = "Sourdough Bread";
                    price = 250.00;
                    productId = productCatalog.get(itemName);
                    break;
                case 2:
                    itemName = "Baguette";
                    price = 200.00;
                    productId = productCatalog.get(itemName);
                    break;
                case 3:
                    itemName = "Focaccia";
                    price = 250.00;
                    productId = productCatalog.get(itemName);
                    break;
                case 4:
                    itemName = "Banana Bread";
                    price = 180.00;
                    productId = productCatalog.get(itemName);
                    break;
                case 5:
                    itemName = "Whole Wheat Bread";
                    price = 200.00;
                    productId = productCatalog.get(itemName);
                    break;
                case 6:
                    itemName = "Plain Croissant";
                    price = 100.00;
                    productId = productCatalog.get(itemName);
                    break;
                case 7:
                    itemName = "Apple Pie Croissant";
                    price = 150.00;
                    productId = productCatalog.get(itemName);
                    break;
                case 8:
                    itemName = "Chocolate Croissant";
                    price = 120.00;
                    productId = productCatalog.get(itemName);
                    break;
                case 9:
                    itemName = "Pain au Chocolat";
                    price = 120.00;
                    productId = productCatalog.get(itemName);
                    break;
                case 10:
                    itemName = "Cinnamon Roll";
                    price = 130.00;
                    productId = productCatalog.get(itemName);
                    break;
                    case 11:  // Classic Vanilla Cake
                    case 12:  // Chocolate Fudge Cake
                    case 13:  // Carrot Cake
                    case 14:  // Matcha Green Tea Cake
                    case 15:  // Tiramisu Cake
                        System.out.println("Please select the size for the cake:");
                        System.out.println("1. 6 inch");
                        System.out.println("2. 7 inch");
                        System.out.println("3. 8 inch");
                        int sizeChoice = input.nextInt();
                        switch (sizeChoice) {
                            case 1:
                                size = "6 inch";
                                break;
                            case 2:
                                size = "7 inch";
                                break;
                            case 3:
                                size = "8 inch";
                                break;
                            default:
                                System.out.println("Invalid size. Defaulting to 6 inch.");
                                size = "6 inch";
                        }
    
                        if (item == 11) {
                            itemName = "Classic Vanilla Cake " + size;
                        } else if (item == 12) {
                            itemName = "Chocolate Fudge Cake " + size;
                        } else if (item == 13) {
                            itemName = "Carrot Cake " + size;
                        } else if (item == 14) {
                            itemName = "Matcha Green Tea Cake " + size;
                        } else if (item == 15) {
                            itemName = "Tiramisu Cake " + size;
                        }
    
                        productId = productCatalog.get(itemName); 
    

                        if (size.equals("6 inch")) {
                            price = 500.00;
                        } else if (size.equals("7 inch")) {
                            price = 600.00;
                        } else if (size.equals("8 inch")) {
                            price = 700.00;
                        }
                        break;
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        System.out.println("Please select the size for the Cheesecake:");
                        System.out.println("1. 6 inch");
                        System.out.println("2. 7 inch");
                        System.out.println("3. 8 inch");
                        
                        int sizeCHoice = input.nextInt();
                        switch (sizeCHoice) {
                            case 1:
                                size = "5 inch";
                                break;
                            case 2:
                                size = "6 inch";
                                break;
                            case 3:
                                size = "8 inch";
                                break;
                            default:
                                System.out.println("Invalid size. Defaulting to 6 inch.");
                                size = "6 inch";
                        }


                    if (item == 16) {
                        itemName = "Ube Oreo Cheesecake " + size;
                        if (size.equals("5 inch")) {
                            price = 469.00;
                        }else if (size.equals("6 inch")){
                            price = 519.00;
                        }else if (size.equals("8 inch")){
                            price = 950.00;
                        }
                    } else if (item == 17) {
                        itemName = "Matcha Oreo Cheesecake " + size;
                        if (size.equals("5 inch")) {
                            price = 469.00;
                        }else if (size.equals("6 inch")){
                            price = 519.00;
                        }else if (size.equals("8 inch")){
                            price = 950.00;
                        }
                    } else if (item == 18) {
                        itemName = "Blueberry Cheesecake " + size;
                        if (size.equals("5 inch")) {
                            price = 469.00;
                        }else if (size.equals("6 inch")){
                            price = 519.00;
                        }else if (size.equals("8 inch")){
                            price = 1119.00;
                        }
                    } else if (item == 19) {
                        itemName = "Dark Chocolate Cheesecake " + size;
                        if (size.equals("5 inch")) {
                            price = 469.00;
                        }else if (size.equals("6 inch")){
                            price = 519.00;
                        }else if (size.equals("8 inch")){
                            price = 950.00;
                        }
                    } else if (item == 20) {
                        itemName = "Biscoff Cheesecake " + size;
                        if (size.equals("5 inch")) {
                            price = 419.00;
                        }else if (size.equals("6 inch")){
                            price = 559.00;
                        }else if (size.equals("8 inch")){
                            price = 1119.00;
                        }
                    }

                    productId = productCatalog.get(itemName);
            
                    default:
                    System.out.println("Invalid order number. Please try again.");
                    continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();
            double totalPrice = price * quantity;
            grandTotal += totalPrice;

            

            saveOrder(connection, customerId, productId, price, quantity, totalPrice, orderDate, orderType);
            System.out.println("Added " + quantity + "x " + itemName + " to your order.");
        }


        System.out.println("\n================================================================");

        System.out.printf("\n\t%-30s %-10s %-10.2f\n", "Total:", "", grandTotal);

        System.out.println("\n\t\t Thank you for your order, " + customerName + "!");

        System.out.println("\n================================================================");



    }


    private static void saveOrder(Connection connection, int customerId, String productId, double price, int quantity, double total, LocalDate date, String orderType) {
        String query = "INSERT INTO OrderInfo (CustomerID, ProductID, Price, OrderQuantity, TotalPrice, OrderDate, OrderType) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, productId);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, quantity);
            pstmt.setDouble(5, total);
            pstmt.setDate(6, java.sql.Date.valueOf(date));
            pstmt.setString(7, orderType);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Product {
    private String name;
    private double price;
    private double price2;
    private double price3;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.price2 = price;
        this.price3 = price;
    }

    public Product(String name, double price, double price2, double price3) {
        this.name = name;
        this.price = price;
        this.price2 = price2;
        this.price3 = price3;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getPrice2() {
        return price2;
    }

    public double getPrice3() {
        return price3;
    }

    public boolean isCake() {
        return price != price2 && price != price3;
    }
}
