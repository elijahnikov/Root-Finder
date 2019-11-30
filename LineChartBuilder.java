package function.root;

import static function.root.FunctionRoot.chosenFunction;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChartBuilder extends JFrame {

    public XYSeries series;
    public JPanel panel;
    public JPanel panel2;
    public ChartPanel chartPanel;
    public JFreeChart chart;
    public JTable rootTable;
    public String[] columnNames;
    public Double[][] data;
    public DefaultTableModel model;
    public Object[][] rowData = {
        {null, null, null}
    };
    public String[] column = {"", "",""};
    public String countString;
    //strings for newtonraphson method
    public String xString;
    public String fxString;
    //strings for bisection method
    public String stackString;
    public String funcStackString;
      
    public LineChartBuilder() {
        //initialise line chart GUI
        initUI();
    }
    
    //GUI for line chart
    private void initUI() {
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        
        JPanel containerPanelOne = new JPanel(new GridLayout(1, 1));
        
        panel = new JPanel();
        panel2 = new JPanel();
         
        model = new DefaultTableModel(rowData, column);
        rootTable = new JTable(model);
        
        JScrollPane scroll = new JScrollPane(rootTable);
        scroll.setPreferredSize(new Dimension(700, 200));
        
        rootTable.setFillsViewportHeight(true);

        panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(chartPanel);
        containerPanelOne.add(panel);

        add(scroll, BorderLayout.SOUTH);
        add(containerPanelOne, BorderLayout.NORTH);
          
        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
    }  
    
    //method to create new dataset for graph
    private XYDataset createDataset() {
        series = new XYSeries(chosenFunction);
   
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;    
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                chosenFunction, 
                "x", 
                "f(x)", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        //change graph colour to white
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        
        //sets grid lines to visible and colour black
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        
        //sets x,y = 0 to visible and colour red:
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);    
        plot.setDomainZeroBaselinePaint(Color.RED);
        plot.setRangeZeroBaselinePaint(Color.RED);
        
        //adds a stroke to x,y = 0 line
        plot.setDomainZeroBaselineStroke(new BasicStroke(4.0f));
        plot.setRangeZeroBaselineStroke(new BasicStroke(4.0f));

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Function Root"));

        return chart;
    }
    
    //method to add Linked List data to the chart series
    public void linkedChartData(SLinkedList thelist){
        StringNode temp;
        if (thelist.isEmpty()){
            System.out.println("List is empty");
        } else {
            temp = thelist.head;
            while (temp != null) {
                series.add(temp.getXValue(), temp.getFXValue());
                temp = temp.getNext();
            }
        }
    }
    
    //method to add data from linked list to a table
    public void linkedTableData(SLinkedList thelist){
        
        //change table headings to correct column names
        JTableHeader header = rootTable.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();
        TableColumn tableColumn0 = columnModel.getColumn(0);
        TableColumn tableColumn1 = columnModel.getColumn(1);
        TableColumn tableColumn2 = columnModel.getColumn(2);
        tableColumn0.setHeaderValue("Step");
        tableColumn1.setHeaderValue("x");
        tableColumn2.setHeaderValue("f(x)");
        header.repaint();

        int count = 0;
        StringNode temp;
        if (thelist.isEmpty()) {
            System.out.println("List is empty");
        } else {
            temp = thelist.head;
            while (temp != null){
                count++;  
                //change values to string
                xString = Double.toString(temp.getXValue());
                fxString = Double.toString(temp.getFXValue());
                countString = Integer.toString(count);
                temp = temp.getNext();
                //create new record and add to DefaultTableModel
                Object[] newRecord = {countString, xString, fxString};
                model.addRow(newRecord);
            }            
            
            int lastRow = rootTable.getRowCount()-1;
            rootTable.setCellSelectionEnabled(true);
            rootTable.changeSelection(lastRow, 1, false, false);
            
        }  
    }    
    
    //method to add arraystack data to the chart series and to the table
    //because of the pop method, it was necessary to use two separate methods
    //for table and chart, which is why two separate stacks are also created
    public void stackChartData(Stack stack, Stack funcstack){   
        while(!stack.isEmpty() && !funcstack.isEmpty()){
            double stackDouble = new Double(stack.pop().toString());
            double funcStackDouble = new Double(funcstack.pop().toString());
            series.add(stackDouble, funcStackDouble);
        }        
        
    }
    
    public void stackTableData(Stack stack, Stack funcstack){
        
        //change table headings to correct column names
        JTableHeader header = rootTable.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();
        TableColumn tableColumn0 = columnModel.getColumn(0);
        TableColumn tableColumn1 = columnModel.getColumn(1);
        TableColumn tableColumn2 = columnModel.getColumn(2);
        tableColumn0.setHeaderValue("Step");
        tableColumn1.setHeaderValue("x");
        tableColumn2.setHeaderValue("f(x)");
        header.repaint();
        
        int count = 0;
        if(stack.isEmpty() && funcstack.isEmpty()){
            System.out.println("Empty");
        }
        
        while(!stack.isEmpty() && !funcstack.isEmpty()){
            count++;
            double stackDouble = new Double(stack.pop().toString());
            double funcStackDouble = new Double(funcstack.pop().toString());
            //change values to string
            stackString = Double.toString(stackDouble);
            funcStackString = Double.toString(funcStackDouble);
            countString = Integer.toString(count);
            //put string data into array
            Object[] newRecord = {countString, stackString, funcStackString};
            //add array to default table model
            model.addRow(newRecord);
        }
        rootTable.setCellSelectionEnabled(true);
        rootTable.changeSelection(1, 1, false, false);
    }
    
}
