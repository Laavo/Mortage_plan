# Mortage_plan
The code reads through the lines of prospects.txt and initializes the fields name, loan, interestRate and years with the constructor in the customer class.
CalculateMonthlyPayment() gets the values of the customer and according to the formula calculates the monthly payment:

E = Fixed monthly payment
b = Interest on a monthly basis
U = Total loan
p = Number of payments

E = U[b(1 + b)^p]/[(1 + b)^p - 1]

It then writes out how much each customer has to pay to the console.
