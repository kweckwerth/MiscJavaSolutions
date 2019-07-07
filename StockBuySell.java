package com.test;


// Program to find best buying and selling days

import java.util.ArrayList;

// Solution structure 
class Interval {
    int buy;
    int sell;
    int profit;

    @Override
    public String toString() {
        return "buy=" + buy + ":sell=" + sell + ":profit=" + profit;
    }


}

class StockBuySell {
    // This function finds the buy sell schedule for maximum profit 
    public String profitLoss(int[] price) {
        int n = price.length;
        // Prices must be given for at least two days 
        if (n == 1)
            return "";

        int count = 0;

        // solution array 
        ArrayList<Interval> sol = new ArrayList<Interval>();

        // Traverse through given price array 
        int i = 0;
        while (i < n - 1) {
            // Find Local Minima. Note that the limit is (n-2) as we are 
            // comparing present element to the next element.  
            while ((i < n - 1) && (price[i + 1] <= price[i]))
                i++;

            // If we reached the end, break as no further solution possible 
            if (i == n - 1)
                break;

            Interval e = new Interval();
            e.buy = i++;

            // Find Local Maxima.  Note that the limit is (n-1) as we are 
            // comparing to previous element 
            while ((i < n) && (price[i] >= price[i - 1]))
                i++;

            // Store the index of maxima 
            e.sell = i - 1;
            e.profit = price[e.sell] - price[e.buy];
            sol.add(e);
            count++;
        }

        // print solution 
        if (count == 0)
            System.out.println("There is no day when buying the stock " + "will make profit");
        else {
            int totalProfit = 0;
            for (int j = 0; j < count; j++) {
                totalProfit += sol.get(j).profit;
            }
            for (int j = 0; j < count; j++)
                System.out.println("Buy on day: " + sol.get(j).buy + " " + "Sell on day : " + sol.get(j).sell + ":profit=" + sol.get(j).profit + ":Total Profit=" + totalProfit);
        }
        return "";
    }


} 
  