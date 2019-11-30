package function.root;

import static function.root.FunctionRoot.chosenDecimal;
import static function.root.FunctionRoot.f;
import static function.root.FunctionRoot.round;

public class secantMethod {
    
    public double secantMethod(double a, double b){
        
        //method starts measuring time taken from start time
        long startTime = System.currentTimeMillis();
        
        //creates new arraystack to store values for chart.
        Stack secantChartStack = new ArrayStack();
        Stack funcSecantChartStack = new ArrayStack();
        //creates new arraystack to store values for table.
        Stack secantTableStack = new ArrayStack();
        Stack funcSecantTableStack = new ArrayStack();
        
        double xnew;
        double fxnew;
        double fa, fb;
        double diff;
        double tolerance = .000000001;
        int num_iteration = 0;
        
        do {
          num_iteration++;
          
          //plug user entry into the function method
          fa = f(a);
          fb = f(b);
          
          //main part of secant method
          xnew = a - (fa * (a - b)) / (fa - fb);
          fxnew = f(xnew);
          
          //print out the steps the method takes into the console
          System.out.println("Step: " + num_iteration + " x: " + xnew + " f(x): " +  fxnew);
          diff = Math.abs(xnew - a);
          b = a;
          a = xnew;
          
          //push xnew and fxnew onto chart stack to be used for the graph
          secantChartStack.push(xnew);
          funcSecantChartStack.push(fxnew);
          
          //push xnew and fxnew onto table stack to be used for the table
          secantTableStack.push(xnew);
          funcSecantTableStack.push(fxnew);
          
        }  while (diff > tolerance);
            System.out.println("Root to " + chosenDecimal + 
                    " decimal places is " + round(xnew, chosenDecimal));
        
        //calls a new instance of the linechartbuilder class
        LineChartBuilder secantChart = new LineChartBuilder();
        secantChart.setVisible(true);
        //calls upon the chartData method which takes values from the stack
        //into the chart.
        secantChart.stackChartData(secantChartStack, funcSecantChartStack);
        //calls upon the tableData method which takes values from stack into table
        secantChart.stackTableData(secantTableStack, funcSecantTableStack);
        secantChart.chartPanel.repaint();
        
        //method ends measuring time with stopTime
        long stopTime = System.currentTimeMillis();
        //elapsed time = stop time - start time and is printed to console
        long elapsedTime = stopTime - startTime;
        System.out.println("Time taken: " + elapsedTime + "ms");

        return 0;
    }
    
}
