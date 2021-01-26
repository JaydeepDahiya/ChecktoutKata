# Checkout Kata

This solution implements the code and the test cases for a checkout system that handles pricing schemes such as "pineapples cost 50, three pineapples cost 130."

The provided code calculates the total price of a number of items and an error if an invalid item is entered. In a normal supermarket, things are identified using Stock
Keeping Units, or SKUs. In our store, we値l use individual letters of the alphabet (A, B, C, and so on). Our goods are priced individually. In addition, some items are multipriced:
buy n of them, and they値l cost you y pence. For example, item A might cost 50 individually, but this week we have a special offer: buy three As and they値l cost
you 130. In fact the prices are:

    Item  Price   Offer
    --------------------------
    A     50       3 for 130
    B     30       2 for 45
    C     20
    D     15

Our checkout accepts items in any order, so that if we scan a B, an A, and another B, we値l recognize the two Bs and price them at 45 (for a total price so far of 95).

### Running the tests

Ensure that Maven is installed and then run the following from the project directory.

`mvn test`

### To clean target, create test report and package

Ensure that Maven is installed and then run the following from the project directory.

`mvn clean package`

NOTE: Jacoco test reports can be found in \target\site\jacoco\index.html