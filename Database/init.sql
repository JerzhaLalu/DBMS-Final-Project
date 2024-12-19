--  DATABASE FinalProjectDataBase;

/*
CREATE TABLE Product (
    ProductID VARCHAR(5) PRIMARY KEY,
    ProductCategory VARCHAR(20),
    ProductName VARCHAR(30),
    ProductSize VARCHAR(30) DEFAULT NULL,
    Price DECIMAL(6,2)
);

INSERT INTO Product (ProductCategory, ProductID, ProductName, ProductSize, Price)
VALUES 
    -- Breads
    ('Bread', 'P101', 'Sourdough Bread', NULL, 250.00),
    ('Bread', 'P102', 'Baguette', NULL, 200.00),
    ('Bread', 'P103', 'Focaccia', NULL, 250.00),
    ('Bread', 'P104', 'Banana Bread', NULL, 180.00),
    ('Bread', 'P105', 'Whole Wheat Bread', NULL, 200.00),
    -- Sweet Pastries
    ('Sweet Pastries', 'P106', 'Plain Croissant', NULL, 100.00),
    ('Sweet Pastries', 'P107', 'Apple Pie Croissant', NULL, 150.00),
    ('Sweet Pastries', 'P108', 'Chocolate Croissant', NULL, 120.00),
    ('Sweet Pastries', 'P109', 'Pain au Chocolat', NULL, 120.00),
    ('Sweet Pastries', 'P110', 'Cinnamon Roll', NULL, 130.00),
    ('Cake', 'P115', 'Classic Vanilla', '6 inch', 500.00),
    ('Cake', 'P116', 'Classic Vanilla', '7 inch', 600.00),
    ('Cake', 'P117', 'Classic Vanilla', '8 inch', 700.00),
    ('Cake', 'P118', 'Chocolate Fudge', '6 inch', 500.00),
    ('Cake', 'P119', 'Chocolate Fudge', '7 inch', 600.00),
    ('Cake', 'P120', 'Chocolate Fudge', '8 inch', 700.00),
    ('Cake', 'P121', 'Carrot Cake', '6 inch', 550.00),
    ('Cake', 'P122', 'Carrot Cake', '7 inch', 650.00),
    ('Cake', 'P123', 'Carrot Cake', '8 inch', 750.00),
    ('Cake', 'P124', 'Matcha Green Tea Cake', '6 inch', 600.00),
    ('Cake', 'P125', 'Matcha Green Tea Cake', '7 inch', 700.00),
    ('Cake', 'P126', 'Matcha Green Tea Cake', '8 inch', 800.00),
    ('Cake', 'P127', 'Tiramisu Cake', '6 inch', 600.00),
    ('Cake', 'P128', 'Tiramisu Cake', '7 inch', 700.00),
    ('Cake', 'P129', 'Tiramisu Cake', '8 inch', 800.00),
    -- Cheesecake
    ('Cheesecake', 'P130', 'Ube Oreo Cheesecake', '5 inch', 469.00),
    ('Cheesecake', 'P131', 'Ube Oreo Cheesecake', '6 inch', 519.00),
    ('Cheesecake', 'P132', 'Ube Oreo Cheesecake', '8 inch', 950.00),
    ('Cheesecake', 'P133', 'Matcha Oreo Cheesecake', '5 inch', 469.00),
    ('Cheesecake', 'P134', 'Matcha Oreo Cheesecake', '6 inch', 519.00),
    ('Cheesecake', 'P135', 'Matcha Oreo Cheesecake', '8 inch', 950.00),
    ('Cheesecake', 'P136', 'Blueberry Cheesecake', '5 inch', 469.00),
    ('Cheesecake', 'P137', 'Blueberry Cheesecake', '6 inch', 519.00),
    ('Cheesecake', 'P139', 'Blueberry Cheesecake', '8 inch', 1199.00),
    ('Cheesecake', 'P140', 'Dark Chocolate Cheesecake', '5 inch', 419.00),
    ('Cheesecake', 'P141', 'Dark Chocolate Cheesecake', '6 inch', 469.00),
    ('Cheesecake', 'P142', 'Dark Chocolate Cheesecake', '8 inch', 950.00),
    ('Cheesecake', 'P143', 'Biscoff Cheesecake', '5 inch', 419.00),
    ('Cheesecake', 'P144', 'Biscoff Cheesecake', '6 inch', 569.00),
    ('Cheesecake', 'P145', 'Biscoff Cheesecake', '8 inch', 1199.00);

CREATE TABLE Customer (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,  
    CustomerName VARCHAR(100) NOT NULL,         
    ContactNumber VARCHAR(15) NOT NULL,         
    ShippingAddress VARCHAR(255) DEFAULT NULL   
);

CREATE TABLE OrderInfo (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,    
    CustomerID INT NOT NULL,                   
    ProductID VARCHAR(5) NOT NULL,             
    Price DECIMAL (6,2),
    OrderQuantity INT NOT NULL,
    TotalPrice DECIMAL(10,2),
    OrderType VARCHAR(20) NOT NULL,
    OrderDate DATE NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);
*/

Select * from Customer;
select * from OrderInfo;


