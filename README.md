# Case study - myRetailService

It contains 3 projects:
- **product-Service** - It runs on port ```9090``` and supports below operations:
  - Retrive product details : Here is the ```GET``` url to retrieve product details ```http://localhost:9090/products/{productid}```, and below is the screenshot from postman
  
  ![image](https://user-images.githubusercontent.com/92587371/138080915-4dbf1587-35ac-4cdc-b282-c8bd2678318a.png)

  - Update product pricing : Here is the ```PUT``` url to update pricing ```http://localhost:9090/products/{product}```, and below is the sample request payload & screenshot from postman
  
  ``` json
  {
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": 2.99,
    "currency_code": "USD"
  }
  ````
  ![image](https://user-images.githubusercontent.com/92587371/138080993-9611e24c-1a0a-42e4-9a02-0bc6e39b7624.png)
  
  _Note_: ```Returns 304 Not Modified``` status if given product doesn't exist in the DB for an update 
  
- **service-registry** - It's a service registry app rus on a default port of ```8761``` to monitor the statuses of registered client services, and also uses it to resolve its own host. Please refer the screenshot below of running service registry app with connect client instances

  ![image](https://user-images.githubusercontent.com/92587371/138080775-d384be14-4b73-4250-a071-37e83cb3ec11.png)

- **api-gateway** - It's a gateway app runs on a port ```9191``` to route incoming requests to the appropriate destination using Gateway Handler Mapping configured in the xml file. It uses the app names from service registry to perform routing. Since above end points (```GET``` & ```PUT```)  are configured in this gateway therefore clients can consume them through gateway end points rather consuming them directly.

  ![image](https://user-images.githubusercontent.com/92587371/138081117-57a33f08-1c25-4b3c-aac4-1364c8a75df3.png)
  
  ![image](https://user-images.githubusercontent.com/92587371/138081900-cb34cf88-cf2e-4b48-8e05-3ea41fe627d2.png)
  
  
 ## Cassandra DB 
 
 Results shown below are **Before** and **After** price update for ```product_id``` - 54456119:

 ![image](https://user-images.githubusercontent.com/92587371/138082028-431def46-4bb1-4000-9125-66886726a89a.png)
 
 Scripts to create Keyspace and table:
 
 ``` sql
 CREATE KEYSPACE redsky with replication = {'class':'SimpleStrategy', 'replication_factor':'1'};
 ```
 
 ``` sql
 CREATE TABLE price (product_id bigint PRIMARY KEY, price float, currency varchar);
 ```
