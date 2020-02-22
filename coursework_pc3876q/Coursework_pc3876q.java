/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework_pc3876q;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author paul
 */
public class Coursework_pc3876q extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    
    // Creating the String for the comboBoxes
    //------------------------------------------------------------------------------
    String[] functionString = {"x-x^2", "ln(x+1)+1", "e^x-3x"};
    String[] methodsString = {"Newton-Raphson", "Secant", "Bisection", "Steffensen"};
    String[] decimalString = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    Integer[] sIterations = {10, 20, 50, 100, 150, 200};

    //------------------------------------------------------------------------------
    Object[][] data = new Object[100][];

    JTable table;

    String[] columnNames = {"Iteration", "x", "f(x)", "f'(x)"};

    //Creating the comboBoxes
    //------------------------------------------------------------------------------
    JComboBox functionList = new JComboBox(functionString);
    JComboBox methodsList = new JComboBox(methodsString);
    JComboBox decimalList = new JComboBox(decimalString);
    JComboBox<Integer> itList = new JComboBox<>(sIterations);

    // Creating the TextFields for inputing the starting points
    //------------------------------------------------------------------------------
    JTextField startPoint1 = new JTextField();
    JTextField startPoint2 = new JTextField();

    // Creating the TextArea to display the results
    //------------------------------------------------------------------------------
    JTextArea textArea = new JTextArea();

    IFunction f = null;
    IFunction fPrime = null;

    Canvas canvas;

    double initialPoint1, initialPoint2;
    
    MyLinkedList list = new MyLinkedList();

    static int BN = 0;
    static Result bArray[] = new Result[500];

    static int SN = 0;
    static Result sArray[] = new Result[500];

    public static void addToBArray(Double x, Double f, Double fPrime) {
        Result result = new Result(x, f, fPrime);
        bArray[BN] = result;
        BN++;
    }

    public static void addToSArray(Double x, Double f, Double fPrime) {
        Result result = new Result(x, f, fPrime);
        sArray[SN] = result;
        SN++;
    }

    public static void main(String[] args) {
        // TODO code application logic here

        Coursework_pc3876q prg = new Coursework_pc3876q();
    }

    // Using MVC
    //------------------------------------------------------------------------------
    public Coursework_pc3876q() {
        model();
        view();
        controller();
    }

    private void model() {
        System.out.println("model not coded yet.");
    }

    // the view class
    //------------------------------------------------------------------------------
    private void view() {

        Font fnt = new Font("Georgia", Font.PLAIN, 20);
        setTitle("Calculator for Function Roots");
        setLayout(null);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Adding the text for the function combobox
        //------------------------------------------------------------------------------        
        JLabel label1 = new JLabel("Select the function you want to calculate:");
        label1.setLocation(20, 20);
        label1.setSize(300, 20);
        add(label1);

        // Adding the comboBox for the funtions
        //------------------------------------------------------------------------------        
        functionList.setFont(fnt);
        functionList.setLocation(340, 20);
        functionList.setSize(300, 20);
        add(functionList);

        //Adding the text for the method combobox
        //------------------------------------------------------------------------------
        JLabel label2 = new JLabel("Select the method of calculation:");
        label2.setLocation(20, 60);
        label2.setSize(300, 20);
        add(label2);

        // Adding the comboBox for the methods
        //------------------------------------------------------------------------------        
        methodsList.setFont(fnt);
        methodsList.setLocation(340, 60);
        methodsList.setSize(300, 20);
        add(methodsList);

        // Adding the text for the decimal combobox
        //------------------------------------------------------------------------------        
        JLabel label3 = new JLabel("Select the number of decimal:");
        label3.setLocation(20, 100);
        label3.setSize(300, 20);
        add(label3);

        // Adding the comboBox for the decimals
        //------------------------------------------------------------------------------        
        decimalList.setFont(fnt);
        decimalList.setLocation(340, 100);
        decimalList.setSize(300, 20);
        add(decimalList);

        // Adding the text for start point
        //------------------------------------------------------------------------------
        JLabel label4 = new JLabel("Enter the first starting point:");
        label4.setLocation(20, 140);
        label4.setSize(300, 20);
        add(label4);

        //Adding the text field for the starting point
        //------------------------------------------------------------------------------
        startPoint1.setFont(fnt);
        startPoint1.setLocation(340, 140);
        startPoint1.setSize(300, 20);
        add(startPoint1);

        //Adding the label for the text field
        //------------------------------------------------------------------------------
        JLabel label5 = new JLabel("Enter the second starting point:");
        label5.setLocation(20, 180);
        label5.setSize(300, 20);
        add(label5);

        //Adding the text field for the second starting point
        //------------------------------------------------------------------------------
        startPoint2.setFont(fnt);
        startPoint2.setLocation(340, 180);
        startPoint2.setSize(300, 20);
        add(startPoint2);

        //Adding the button for calculation
        //------------------------------------------------------------------------------
        JButton button = makeButton("Calculate",
                "Calculate the roots",
                "Calculate");
        button.setLocation(240, 280);
        button.setSize(150, 40);
        add(button);

        //Adding the graph
        //------------------------------------------------------------------------------
        canvas = new Canvas();
        canvas.setLocation(650, 20);
        canvas.setSize(510, 510);
        add(canvas);

        // Adding the text for the iterations comboBox
        //------------------------------------------------------------------------------
        JLabel label6 = new JLabel("Select maximum number of iterations:");
        label6.setLocation(20, 220);
        label6.setSize(300, 20);
        add(label6);

        //Adding the comboBox for the iterations
        //------------------------------------------------------------------------------
        itList.setFont(fnt);
        itList.setLocation(340, 220);
        itList.setSize(300, 20);
        add(itList);

        //Adding the table for the results
        //------------------------------------------------------------------------------
        data = new Object[1][4];
        data[0][0] = "";
        data[0][1] = "";
        data[0][2] = "";
        data[0][3] = "";

        JPanel panelTable = new JPanel();
        table = new JTable(data, columnNames);

        panelTable.setLocation(20, 350);
        panelTable.setSize(600, 600);

        table.setPreferredScrollableViewportSize(new Dimension(600, 70));

        panelTable.add(table);
        panelTable.setLayout(new BorderLayout());
        panelTable.add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(panelTable);

        JScrollPane scrollPane = new JScrollPane(table);
        panelTable.add(scrollPane);

        //------------------------------------------------------------------------------    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void controller() {
        System.out.println("controller not coded yet.");
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (!startPoint1.getText().trim().equals("")) {
            initialPoint1 = Double.parseDouble(startPoint1.getText());
        } else {
            initialPoint1 = 0;
        }

        if (!startPoint2.getText().trim().equals("")) {
            initialPoint2 = Double.parseDouble(startPoint2.getText());
        } else {
            initialPoint2 = 0;
        }
        canvas.setValues(initialPoint1,initialPoint2);

        // method index
        int decimals = decimalList.getSelectedIndex() + 1;

        // function index
        // Pressing the button
        //------------------------------------------------------------------------------
        if ("Calculate".equals(ae.getActionCommand())) {

            // Switch method used for the function comboBox
            //------------------------------------------------------------------------------
            int fi = functionList.getSelectedIndex();

            switch (fi) {

                case 0:
                    f = new Function1();
                    fPrime = new Function1Prime();
                    break;

                case 1:
                    f = new Function2();
                    fPrime = new Function2Prime();
                    break;

                case 2:
                    f = new Function3();
                    fPrime = new Function3Prime();
                    break;
            }

            //------------------------------------------------------------------------------
            Double result = null;
            Double[] vresult = new Double[1];

            int mi = methodsList.getSelectedIndex();

            // Switch method used for the 
            int nrIterations;
            switch (mi) {

                case 0:

                    nrIterations = ((Integer) itList.getSelectedItem()).intValue();
                    result = Newton_Raphson.Newton_Raphson(initialPoint1, f, fPrime, decimals, nrIterations);
                    vresult[0] = result; 
                    
                    
                    list = Newton_Raphson.list;
                    canvas.setXAsList(list);;
                    
                    int i = 0;
                    data = new Object[list.size()+1][4];
                    Result r;
                    list.reset();
                    while ((r = list.get())!=null) {
                        data[i][0] = String.format("%d", (i + 1));
                        data[i][1] = String.format("%." + decimals + "f", (double) (r.getX().doubleValue()));
                        data[i][2] = String.format("%." + decimals + "f", (double) (r.getF().doubleValue()));
                        data[i][3] = String.format("%." + decimals + "f", (double) (r.getfPrime().doubleValue()));
                        i++;
                    }

                    table.setModel(new javax.swing.table.DefaultTableModel(
                            data, columnNames));
                    repaint();

                    canvas.setFunction(f);
                    canvas.setFunctionPrime(fPrime);
                    canvas.repaint();
                    break;

                case 1:

                    nrIterations = ((Integer) itList.getSelectedItem()).intValue();
                    result = Secant.Secant(initialPoint1, initialPoint2, f, decimals, nrIterations);
                    
                    vresult[0] = result;

                    SN = Secant.SN;
                    sArray = Secant.sArray;
                    canvas.setXAsArray(sArray, SN);
                    data = new Object[SN][4];

                    for (i = 0; i < SN; i++) {

                        data[i][0] = String.format("%d", (i + 1));
                        data[i][1] = String.format("%." + decimals + "f", sArray[i].getX());
                        data[i][2] = String.format("%." + decimals + "f", sArray[i].getF());
                        data[0][3] = String.format("Not Applicable");

                    }
                    table.setModel(new javax.swing.table.DefaultTableModel(
                            data, columnNames));
                    repaint();
                    canvas.setFunction(f);
                    canvas.setFunctionPrime(fPrime);
                    canvas.repaint();
                    break;

                case 2:

                    nrIterations = ((Integer) itList.getSelectedItem()).intValue();
                    result = Bisection.Bisection(initialPoint1, initialPoint2, f, decimals, nrIterations);
                    
                    vresult[0] = result;
                    BN = Bisection.getBN();
                    bArray = Bisection.getbArray();
                    canvas.setXAsArray(bArray, BN);

                    data = new Object[BN][4];

                    for (i = 0; i < BN; i++) {

                        data[i][0] = String.format("%d", (i + 1));
                        data[i][1] = String.format("%." + decimals + "f", bArray[i].getX());
                        data[i][2] = String.format("%." + decimals + "f", bArray[i].getF());
                        data[0][3] = String.format("Not Applicable");

                    }
                    table.setModel(new javax.swing.table.DefaultTableModel(
                            data, columnNames));
                    repaint();
                    canvas.setFunction(f);
                    canvas.setFunctionPrime(fPrime);
                    canvas.repaint();
                    break;

                case 3:

                    nrIterations = ((Integer) itList.getSelectedItem()).intValue();
                    result = Steff.Steffensen(initialPoint1, f, decimals, nrIterations);
                    
                    vresult[0] = result;

                    list = Steff.list;
                    canvas.setXAsList(list);
                    data = new Object[list.size()+1][4];

                    
                    i = 0;
                    list.reset();
                    while ((r = list.get()) != null) {
                        data[i][0] = String.format("%d", (i + 1));
                        data[i][1] = String.format("%." + decimals + "f", (double) (r.getX().doubleValue()));
                        data[i][2] = String.format("%." + decimals + "f", (double) (r.getfPrime().doubleValue()));
                        data[0][3] = String.format("Not Applicable");
                        i++;
                    }
                    table.setModel(new javax.swing.table.DefaultTableModel(
                            data, columnNames));
                    repaint();
                    canvas.setFunction(f);
                    canvas.setFunctionPrime(fPrime);
                    canvas.repaint();
                    break;
            }
        }
    }

    //Method for the button
    //------------------------------------------------------------------------------
    private JButton makeButton(
            String actionCommand,
            String toolTipText,
            String altText) {

        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setText(altText); // Setting the text in the button     
        return button;

    }
}