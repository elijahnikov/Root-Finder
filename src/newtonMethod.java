package function.root;

import static function.root.FunctionRoot.chosenDecimal;
import static function.root.FunctionRoot.f;
import static function.root.FunctionRoot.fprime;
import static function.root.FunctionRoot.round;
import javax.swing.JOptionPane;

public class newtonMethod {
    
    public double newtonMethod(double startingEntry) throws IllegalStateException {
        
        //method starts measuring time taken from start time
        long startTime = System.currentTimeMillis();
        
        //creates new linked list to store the results
        SLinkedList newtonList = new SLinkedList();
        newtonList.head.setXValue(startingEntry);
        newtonList.head.setFXValue(f(startingEntry));
        
        double tolerance = .000000001;
        //how many iterations the function goes through:
        int max_count = 400;
        double x = startingEntry;
        double value;
        double h;

        for( int count=1; (Math.abs(f(x)) > tolerance) && (count < max_count); count ++)  {
            
            //main part of newton-raphson method
            h = f(x)/fprime(x);
            x = x - h;
            value = f(x);
            
            //adds x and fx to the linked list.
            newtonList.addLast(x, value);
            
            System.out.println("Step " + count + " x:" + x + " Value:"+ value);
            
        }
        
        if( Math.abs(f(x)) <= tolerance) {
            //prints out root found at x rounded to chosen decimal
            System.out.println("Root found at x= "+ round(x, chosenDecimal));
            
            //creates new instance of the chart builder class
            LineChartBuilder newtonChart = new LineChartBuilder();
            newtonChart.setVisible(true);
            //calls upon the chartData and tableData method which takes values from the linked list
            //into the chart and table, respectively,
            newtonChart.linkedChartData(newtonList);
            newtonChart.linkedTableData(newtonList);
            newtonChart.chartPanel.repaint();
            
        } else {
            System.out.println("Failed to find a root");
            JOptionPane.showMessageDialog(null, "Failed to find a root");
        }
        
        //method ends measuring time with stopTime
        long stopTime = System.currentTimeMillis();
        //elapsed time = stop time - start time and is printed to console
        long elapsedTime = stopTime - startTime;
        System.out.println("Time taken: " + elapsedTime + "ms");
        
        //prints the list where the x and fx values are stored
        //printList(newtonList);

        return 0;
        
    }   
       
}
