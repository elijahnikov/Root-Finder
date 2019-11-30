package function.root;

import static function.root.FunctionRoot.chosenDecimal;
import static function.root.FunctionRoot.f;
import static function.root.FunctionRoot.round;

public class falsePositionMethod {
    
    public static double falsePositionMethod(double a, double b){
        
        //method starts measuring time taken from start time
        long startTime = System.currentTimeMillis();
        
        SLinkedList falsePositionList = new SLinkedList();
        
        int num_iteration = 1;
        int max_count = 400;
        double tolerance = .000000001;
        
        double fa, fb, fxnew;
        double xnew;
        
        do {
            //plugs user entry into function method
            fa = f(a);
            fb = f(b);
            
            //main part of false position algorithm
            xnew = b - fb * (a - b) / (fa - fb);
            fxnew = f(xnew);
            
            //prints the steps the method takes into the console
            System.out.println("Step: " + num_iteration + " x: " + xnew + " f(x): " + fxnew);
            
            //flip function signs
            if (fxnew * fa < 0){
                b = xnew;
            } else {
                a = xnew;
            }
            
            num_iteration++;
            //adds xnew and fxnew into the linked list
            falsePositionList.addLast(xnew, fxnew);
            
        } while (Math.abs(fxnew) >= tolerance && num_iteration <= max_count);
        System.out.println("Root to " + chosenDecimal + " places found at x: " + round(xnew, chosenDecimal));
        
        //sets head for the linkedlist so the default values are not 0/null
        falsePositionList.head.setXValue(xnew);
        falsePositionList.head.setFXValue(fxnew);
        
        //creates new instance of the chart builder class.
        LineChartBuilder falsePositionChart = new LineChartBuilder();
        falsePositionChart.setVisible(true);
        //calls chartData and tableData method to insert data from linked list into
        //chart and table
        falsePositionChart.linkedChartData(falsePositionList);
        falsePositionChart.linkedTableData(falsePositionList);
        falsePositionChart.chartPanel.repaint();
        
        //method ends measuring time with stopTime
        long stopTime = System.currentTimeMillis();
        //elapsed time = stop time - start time and is printed to console
        long elapsedTime = stopTime - startTime;
        System.out.println("Time taken: " + elapsedTime + "ms");
        
        return 0;
        
    }
    
}
