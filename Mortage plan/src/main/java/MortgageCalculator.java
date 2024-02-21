import java.io.*;
import java.util.*;

public class MortgageCalculator {
    public static void main(String[] args) {
        List<Customer> customers = readCustomersFromFile("prospects.txt");
        for (Customer customer : customers) {
            String monthlyPayment = String.format("%.2f", calculateMonthlyPayment(customer));
            System.out.println("Prospect: " + customer.getName() + " wants to borrow " + customer.getLoan() + "€ for a period of " + customer.getYears() + " years and pay " + monthlyPayment + "€ each month");
        }
    }

    private static List<Customer> readCustomersFromFile(String filename) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine())!= null || br.readLine() != "") {
                if (line.isBlank()) {
                    break;
                }
                if (line.startsWith("Customer")) {
                    continue;
                }
                String name;
                String[] sections;
                Double loan;
                Double interest;
                Integer years;
                if (line.startsWith("\"")) {
                    int lastQuote = line.indexOf("\",");
                    name = line.substring(1, lastQuote);
                    name = name.replace("\"","");
                    line = line.substring(lastQuote + 2);
                    sections = line.split(",");
                    loan = Double.parseDouble(sections[0]);
                    interest = Double.parseDouble(sections[1]);
                    years = Integer.parseInt(sections[2]);
                } else {
                    sections = line.split(",");
                    name = sections[0];
                    loan = Double.parseDouble(sections[1]);
                    interest = Double.parseDouble(sections[2]);
                    years = Integer.parseInt(sections[3]);
                }
                customers.add(new Customer(name, loan, interest, years));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    static double calculateMonthlyPayment(Customer customer) {
        double yearlyInterestRate = customer.getInterestRate();
        double monthlyInterestRate = yearlyInterestRate / 12 / 100;
        double totalLoan = customer.getLoan();
        double numberOfPayments = customer.getYears() * 12;
        if (numberOfPayments == 0) {
            throw new IllegalArgumentException("Number of years must be greater than zero");
        }

        return totalLoan * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments) / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
    }
}

class Customer {
    private String name;
    private double loan;
    private double interestRate;
    private int years;

    public Customer(String name, double loan, double interestRate, int years) {
        this.name = name;
        this.loan = loan;
        this.interestRate = interestRate;
        this.years = years;
    }

    public String getName() {
        return this.name;
    }

    public Double getLoan() {
        return this.loan;
    }

    public Integer getYears() {
        return  this.years;
    }

    public Double getInterestRate() {
        return this.interestRate;
    }

}
