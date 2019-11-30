package function.root;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class FunctionRoot implements ActionListener {
    
    JTextArea txtNewNote = new JTextArea();
    String[] functionString = { "x-x^2", "ln(x+1)+1", "e^x-3x" };
    String[] methodString = { "Newton-Raphson", "Secant", "Bisection", "False Position*" };
    String[] decimalPlaces = { "0", "1", "2", "3", "4", "5", "6" , "7", "8", "9", "10" };
    String decimalToString;
    static int chosenDecimal;
    public int count;
    public static String chosenFunction;
    JLabel functionLabel;
    JLabel methodLabel;
    JLabel decimalLabel;
    JLabel startingPointLabel;
    JLabel lowerBisectLabel;
    JLabel upperBisectLabel;
    JLabel firstEstimateLabel;
    JLabel secondEstimateLabel;
    static JComboBox functionSelection;
    JComboBox methodSelection;
    JComboBox decimalSelection;
    //newton-raphson field
    JTextField startingEntry;
    //secant fields
    JTextField firstEstimate;
    JTextField secondEstimate;
    //bisection fields
    JTextField chosenLower;
    JTextField chosenUpper;
    public double function;
    public double derivFunction;
    static public double x;
    public static double e;
    public static double value;
    Font fnt = new Font("Helvetica", Font.PLAIN, 20);
    public JButton run;
    public String initialTolerance = "0";
    JPanel containerPanelTwo;
    JFrame frame;
    
    JPanel panel1;      
    JPanel panel2;      
    JPanel panel3;          
    JPanel panel4;
    JPanel panel5;
    JPanel panel6;

    public FunctionRoot() throws FileNotFoundException, IOException {
        view();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //newton-raphson
        double chosenStartingPoint = 0;
        //secant
        double firstEstimateValue;
        double secondEstimateValue;
        //bisection and false-position
        double chosenLowerValue = 0;
        double chosenUpperValue = 0;
        
        if("Newton-Raphson".equals(methodSelection.getSelectedItem())){
                
                //set other method field(s) to not visible.
                lowerBisectLabel.setVisible(false);
                upperBisectLabel.setVisible(false);
                chosenLower.setVisible(false);
                chosenUpper.setVisible(false);
                
                firstEstimate.setVisible(false);
                secondEstimate.setVisible(false);
                firstEstimateLabel.setVisible(false);
                secondEstimateLabel.setVisible(false);
                
                //set text field to visible so user can choose starting point for Newton-Raphson.
                startingEntry.setVisible(true);
                startingPointLabel.setVisible(true);
                
        }  
        
        if("Secant".equals(methodSelection.getSelectedItem())){

                //set other method field(s) to not visible.
                lowerBisectLabel.setVisible(false);
                upperBisectLabel.setVisible(false);
                chosenLower.setVisible(false);
                chosenUpper.setVisible(false);
                
                startingEntry.setVisible(false);
                startingPointLabel.setVisible(false);
                
                //set text fields to visible so user can select first and second estimates for Secant.
                firstEstimate.setVisible(true);
                secondEstimate.setVisible(true);
                firstEstimateLabel.setVisible(true);
                secondEstimateLabel.setVisible(true);
                             
        }
        
        if("Bisection".equals(methodSelection.getSelectedItem())){
                
                //set other method field(s) to not visible.
                startingEntry.setVisible(false);
                startingPointLabel.setVisible(false);
                
                firstEstimate.setVisible(false);
                secondEstimate.setVisible(false);
                firstEstimateLabel.setVisible(false);
                secondEstimateLabel.setVisible(false);
                
                //set text fields to visible so user can choose left and right assumptions for Bisection.
                lowerBisectLabel.setVisible(true);
                upperBisectLabel.setVisible(true);
                chosenLower.setVisible(true);
                chosenUpper.setVisible(true);

        }      
        
        if("False Position*".equals(methodSelection.getSelectedItem())){
                
                //set other method field(s) to not visible.
                startingEntry.setVisible(false);
                startingPointLabel.setVisible(false);
                
                firstEstimate.setVisible(false);
                secondEstimate.setVisible(false);
                firstEstimateLabel.setVisible(false);
                secondEstimateLabel.setVisible(false);
                
                //set text fields to visible so user can choose left and right assumptions for False Position.
                lowerBisectLabel.setVisible(true);
                upperBisectLabel.setVisible(true);
                chosenLower.setVisible(true);
                chosenUpper.setVisible(true);
                
        } 
         
        if ("run".equals(ae.getActionCommand())) {
            
            chosenFunction = (String) functionSelection.getSelectedItem();
            
            //validation to make sure user has selected a function
            if (functionSelection.getSelectedIndex() < 0){
                JOptionPane.showMessageDialog(null, "Please choose a function");
            }
            
            //validation to make sure user is forced to choose an option
            if(methodSelection.getSelectedIndex() < 0){
                JOptionPane.showMessageDialog(null, "Please choose a root finding method."); 
            }
            
            //validation check for decimal selection.
            if(decimalSelection.getSelectedIndex() < 0){
                JOptionPane.showMessageDialog(null, "Please choose decimal places");
            } else {
                chosenDecimal = Integer.parseInt((String)decimalSelection.getSelectedItem());
            }  
                      
            //method selection
            if("Newton-Raphson".equals(methodSelection.getSelectedItem())){
                //validation check, if newtonraphson field is visible and empty, show dialog warning.
                if (startingEntry.isVisible() == true && startingEntry.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter a starting point.");
                } else if(startingEntry.isVisible() == true && !startingEntry.getText().equals("")){
                    try {
                        chosenStartingPoint = Double.parseDouble(startingEntry.getText());
                        System.out.println("Chosen starting point is " + chosenStartingPoint);
                        newtonMethod newt = new newtonMethod();
                        newt.newtonMethod(chosenStartingPoint);
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Please enter numbers and/or points only");
                    }
                }   
            }  
        
            if("Secant".equals(methodSelection.getSelectedItem())){
                //validation check, if first and second estimate fields are visible and empty, show dialog warning.
                if (firstEstimate.isVisible() == true && firstEstimate.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter your first estimate for Secant");
                } else if (secondEstimate.isVisible() == true && secondEstimate.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter your second estimate for Secant");
                } else {
                    try {
                        firstEstimateValue = Double.parseDouble(firstEstimate.getText());
                        secondEstimateValue = Double.parseDouble(secondEstimate.getText());
                        System.out.println("Chosen first estimate is " + firstEstimateValue);
                        System.out.println("Chosen seocnd estiamte is " + secondEstimateValue);
                        secantMethod secant = new secantMethod();
                        secant.secantMethod(firstEstimateValue, secondEstimateValue);
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Please enter numbers and/or points only");
                    }                    
                }
            }
        
            if("Bisection".equals(methodSelection.getSelectedItem())){
                //validation check, if lower and upper field is visible and empty, show dialog warning.
                if (chosenLower.isVisible() == true && chosenLower.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter a lower value for Bisection");
                } else if (chosenUpper.isVisible() == true && chosenUpper.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter an upper value for Bisection");
                } else {
                    try {
                        chosenLowerValue = Double.parseDouble(chosenLower.getText());                   
                        chosenUpperValue = Double.parseDouble(chosenUpper.getText());
                        System.out.println("Chosen lower value is " + chosenLowerValue);
                        System.out.println("Chosen upper value is " + chosenUpperValue);
                        bisectionMethod bisect = new bisectionMethod();
                        bisect.bisectionMethod(chosenLowerValue, chosenUpperValue);
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Please enter numbers and/or points only");
                    }                   
                }
            }
            
            if("False Position*".equals(methodSelection.getSelectedItem())){
                //validation check, if lower and upper field is visible and empty, show dialog warning.
                if (chosenLower.isVisible() == true && chosenLower.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter a lower value for False Position Method");
                } else if (chosenUpper.isVisible() == true && chosenUpper.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter an upper value for False Position Method");
                } else {
                    try {       
                        chosenLowerValue = Double.parseDouble(chosenLower.getText());                   
                        chosenUpperValue = Double.parseDouble(chosenUpper.getText());
                        System.out.println("Chosen lower value is " + chosenLowerValue);
                        System.out.println("Chosen upper value is " + chosenUpperValue);
                        falsePositionMethod falseMethod = new falsePositionMethod();
                        falseMethod.falsePositionMethod(chosenLowerValue, chosenUpperValue);
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Please enter numbers and/or points only");
                    }
                    
                }
            }
        }            
    }
    
    //if statements to return chosen f function
    public static double f(double x){
        if ("x-x^2".equals(functionSelection.getSelectedItem())){
            return x-x*x;
        }
        if ("ln(x+1)+1".equals(functionSelection.getSelectedItem())){
            return Math.log((x+1)+1);
        }
        if ("e^x-3x".equals(functionSelection.getSelectedItem())){
            return Math.pow(2.718281, x)-3*x;
        } else {
            return 0;
        }
        
    }
    
    //if statements to return the derivative of the chosen funciton 
    //(only used for newton-raphson)
    public static double fprime(double x){
        if ("x-x^2".equals(functionSelection.getSelectedItem())){
            return 1-2*x;
        }
        if ("ln(x+1)+1".equals(functionSelection.getSelectedItem())){
            return 1/x+1;
        }
        if ("e^x-3x".equals(functionSelection.getSelectedItem())){
            return Math.pow(2.718281, x);
        } else {
            return 0;
        }
    }
          
    public void view() {
        
        frame = new JFrame();
        panel1 = new JPanel();        
        panel2 = new JPanel();      
        panel3 = new JPanel();             
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        
        run = new JButton("Run");
        
        run.addActionListener(this);
        run.setActionCommand("run");
        panel6.add(run);
        
        //labels for combo boxes and text fields
        functionLabel = new JLabel("Select function:");
        methodLabel = new JLabel("Select root finding method:");
        decimalLabel = new JLabel("Select decimal places:");
        startingPointLabel = new JLabel("Enter a starting point:");
        lowerBisectLabel = new JLabel("Enter a lower assumption:");
        upperBisectLabel = new JLabel("Enter an upper assumption:");
        firstEstimateLabel = new JLabel("Enter first estimate:");
        secondEstimateLabel = new JLabel("Enter second estimate:");
        
        //Combo Box that allows the user to select function
        functionSelection = new JComboBox(functionString);
        functionSelection.setRenderer(new MyComboBoxRenderer("Function"));
        functionSelection.setPreferredSize(new Dimension(85, 30));
        functionSelection.setSelectedIndex(-1);
        functionSelection.addActionListener(this);
        panel1.add(functionLabel);
        panel1.add(functionSelection);
        
        //Combo Box that allows the user to select root finding method
        methodSelection = new JComboBox(methodString);
        methodSelection.setRenderer(new MyComboBoxRenderer("Method"));
        methodSelection.setPreferredSize(new Dimension(130, 30));
        methodSelection.setSelectedIndex(-1);
        methodSelection.addActionListener(this);
        panel2.add(methodLabel);
        panel2.add(methodSelection);
        
        decimalSelection = new JComboBox(decimalPlaces);
        decimalSelection.setRenderer(new MyComboBoxRenderer("Decimal Places"));
        decimalSelection.setPreferredSize(new Dimension(130, 30));
        decimalSelection.setSelectedIndex(-1);
        decimalSelection.addActionListener(this);
        panel3.add(decimalLabel);
        panel3.add(decimalSelection);
        
        //text field and label for Newton-Raphson method.
        startingEntry = new JTextField(10);
        startingEntry.setPreferredSize(new Dimension(130, 30));
        startingEntry.addActionListener(this);
        startingEntry.setVisible(false);
        startingPointLabel.setVisible(false);
        panel4.add(startingPointLabel);
        panel4.add(startingEntry);
        
        //text fields and labels for Secant Method.
        firstEstimate = new JTextField(10);
        firstEstimate.setPreferredSize(new Dimension(130, 30));
        firstEstimate.addActionListener(this);
        secondEstimate = new JTextField(10);
        secondEstimate.setPreferredSize(new Dimension(130, 30));
        secondEstimate.addActionListener(this);
        panel4.add(firstEstimateLabel);
        panel5.add(secondEstimateLabel);
        panel4.add(firstEstimate);
        panel5.add(secondEstimate);
        firstEstimate.setVisible(false);
        secondEstimate.setVisible(false);
        firstEstimateLabel.setVisible(false);
        secondEstimateLabel.setVisible(false);
        
        //text fields and labels for Bisection Method.
        chosenLower = new JTextField(10);
        chosenLower.setPreferredSize(new Dimension(130, 30));
        chosenLower.addActionListener(this);
        chosenUpper = new JTextField(10);
        chosenUpper.setPreferredSize(new Dimension(130, 30));
        chosenUpper.addActionListener(this);
        panel4.add(lowerBisectLabel);
        panel5.add(upperBisectLabel);
        panel4.add(chosenLower);
        panel5.add(chosenUpper);
        chosenLower.setVisible(false);
        chosenUpper.setVisible(false);
        lowerBisectLabel.setVisible(false);
        upperBisectLabel.setVisible(false);
        
        JPanel containerPanelOne = new JPanel(new GridLayout(4, 1));
        containerPanelTwo = new JPanel(new GridLayout(2, 1));
        containerPanelOne.add(panel1);
        containerPanelOne.add(panel2);
        containerPanelOne.add(panel3);
        containerPanelOne.add(panel4);
        containerPanelTwo.add(panel5);
        containerPanelTwo.add(panel6);
        
        frame.add(containerPanelOne, BorderLayout.NORTH);
        frame.add(containerPanelTwo, BorderLayout.SOUTH);

        frame.setSize(550, 300);
        frame.setTitle("Find root of function f(x)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        frame.setVisible(true);
       
    }  
       
    //class to add a title to JComboBox(s) without having it as a selection
    class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
        
        private String tempTitle;
        
        public MyComboBoxRenderer(String title){
            tempTitle = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (index == -1 && value == null) {
                setText(tempTitle);
            } else {
                setText(value.toString());
            }
            return this;
        }
        
    }
    
    //method to return a the result rounded to a specific decimal place chosen by user
    //only known problem is negative numbers are not rounded properly and instead returns 0
    public static double round(double value, int places){
        if (places <= 0) {
            return value;
        } else {
            return Double.parseDouble(String.format("%." + places + "f", value));
        }
        
    }  
    
    public static void main(String[] args) throws IOException {
        try { 
            //sets the theme for the gui
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");             
        } catch (Exception ignored){}     
        //call new FunctionRoot to run the program
        FunctionRoot prg = new FunctionRoot();
    }
    
}
