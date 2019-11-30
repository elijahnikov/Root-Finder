package function.root;

import static function.root.FunctionRoot.f;
import static function.root.FunctionRoot.chosenDecimal;
import static function.root.FunctionRoot.round;
import javax.swing.JOptionPane;

public class bisectionMethod {
    
    public double bisectionMethod(double a, double b){
        
        //method starts measuring time taken from start time
        long startTime = System.currentTimeMillis();
        
        //creates new arraystack to store values for chart.
        Stack bisectionChartStack = new ArrayStack();
        Stack funcBisectionChartStack = new ArrayStack();
        //creates new arraystack to store values for table.
        Stack bisectionTableStack = new ArrayStack();
        Stack funcBisectionTableStack = new ArrayStack();
        
        int num_iteration = 1;
        double tolerance = .000000001;
        int max_count = 200;
        
        double middle = 0;
        double func1, func2, funcmid;
        
        do {
            
            //takes user entry and plugs into the function method found in main
            func1 = f(a);
            func2 = f(b);
            
            if (func1 * func2 > 0){
                System.out.println("Values do not give a root, please try another");
                JOptionPane.showMessageDialog(null, "Values do not give a root, try other value(s)");
                break;
            }
            
            //main part of bisection method
            middle = (a + b) / 2;
            funcmid = f(middle);
            System.out.println("Step: " + num_iteration + "  " + middle + " " + funcmid);
            
            //add middle and fmiddle to arraystack used for chart data.
            bisectionChartStack.push(middle);
            funcBisectionChartStack.push(funcmid);
            
            //add middle and fmiddle to arraystack used for table data.
            bisectionTableStack.push(middle);
            funcBisectionTableStack.push(funcmid);
            
            
            if (funcmid * func1 < 0){
                b = middle;
            } else {
                a = middle;
            }
            num_iteration++;            
        } while (Math.abs(a - b) / 2 >= tolerance && Math.abs(funcmid) > tolerance && num_iteration <= max_count);
            System.out.println("Root to " + chosenDecimal + " decimal places is: " + round(middle, chosenDecimal));
        
        //creates a new instance of linechartbuilder class
        LineChartBuilder bisectionChart = new LineChartBuilder();
        bisectionChart.setVisible(true);
        //calls upon the chartData method which takes values from the stack
        //into the chart.
        bisectionChart.stackChartData(bisectionChartStack, funcBisectionChartStack);
        //calls upon the tableData method which takes values from stack into table
        bisectionChart.stackTableData(bisectionTableStack, funcBisectionTableStack);
        bisectionChart.chartPanel.repaint();
        
        //method ends measuring time with stopTime
        long stopTime = System.currentTimeMillis();
        //elapsed time = stop time - start time and is printed to console
        long elapsedTime = stopTime - startTime;
        System.out.println("Time taken: " + elapsedTime + "ms");
        
        //prints the stack to console.
        /*while(!bisectionStack.isEmpty()){
                System.out.println(bisectionStack.pop());
        }*/
        
        return middle; 
    }
    
}
