package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame {
    private JTextField taskTxtField;
    private JTextField serverTxtField;
    private JTextField tLimitTxtField;
    private JTextField minArrivalTxtField;
    private JTextField maxArrivalTxtField;
    private JTextField minProcessingTxtField;
    private JTextField maxProcessingTxtField;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    private JRadioButton timeRdBtn;
    private JRadioButton queueRdBtn;
    private JTextArea textArea;
    Controller controller = new Controller(this);
    public View() {
        this.initialize();
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JRadioButton getTimeRdBtn() {
        return timeRdBtn;
    }


    public JTextField getTaskTxtField() {
        return taskTxtField;
    }


    public JTextField getServerTxtField() {
        return serverTxtField;
    }

    public JTextField gettLimitTxtField() {
        return tLimitTxtField;
    }

    public JTextField getMinArrivalTxtField() {
        return minArrivalTxtField;
    }

    public JTextField getMaxArrivalTxtField() {
        return maxArrivalTxtField;
    }

    public JTextField getMinProcessingTxtField() {
        return minProcessingTxtField;
    }

    public JTextField getMaxProcessingTxtField() {
        return maxProcessingTxtField;
    }

    private void initialize() {

        this.getContentPane().setBackground(new Color(208, 117, 232));
        this.getContentPane().setLayout(null);
        this.setFont(new Font("Cambria", Font.BOLD, 12));
        this.setTitle("QUEUES MANAGEMENT");
        this.setBounds(100, 100, 1079, 582);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        txtField();
        rdBtn();
        btn();
        txtArea();

    }
    void txtArea(){
        JPanel panel = new JPanel();
        panel.setBounds(359, 72, 676, 463);
        //panel.setBackground(new Color(230, 181, 242));
        this.getContentPane().add(panel);

        textArea= new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(new Color(208, 117, 232));
        textArea.setFont(new Font("Cambria", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(670, 455));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);
    }
    void btn(){
        JButton startBtn = new JButton("START");
        startBtn.setBackground(new Color(230, 181, 242));
        startBtn.setForeground(new Color(0, 0, 0));
        startBtn.setFont(new Font("Cambria", Font.BOLD, 18));
        startBtn.setBounds(29, 423, 292, 50);
        this.getContentPane().add(startBtn);

        startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                controller.startBtnPressed();
            }
        });

        JButton restartBtn = new JButton("RESTART");
        restartBtn.setForeground(Color.BLACK);
        restartBtn.setFont(new Font("Cambria", Font.BOLD, 18));
        restartBtn.setBackground(new Color(230, 181, 242));
        restartBtn.setBounds(29, 485, 292, 50);
        this.getContentPane().add(restartBtn);

        restartBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                controller.restartBtnPressed();
            }
        });
    }
    void rdBtn(){
        timeRdBtn = new JRadioButton("Time Strategy");
        timeRdBtn.setSelected(true);
        timeRdBtn.setBounds(29, 366, 292, 21);
        buttonGroup.add(timeRdBtn);
        timeRdBtn.setBackground(new Color(208, 117, 232));
        timeRdBtn.setFont(new Font("Cambria", Font.BOLD, 16));
        timeRdBtn.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(timeRdBtn);

        queueRdBtn = new JRadioButton("Queue Strategy");
        queueRdBtn.setBounds(29, 396, 292, 21);
        buttonGroup.add(queueRdBtn);
        queueRdBtn.setHorizontalAlignment(SwingConstants.CENTER);
        queueRdBtn.setFont(new Font("Cambria", Font.BOLD, 16));
        queueRdBtn.setBackground(new Color(208, 117, 232));
        this.getContentPane().add(queueRdBtn);
    }
    void txtField(){
        taskTxtField = new JTextField();
        taskTxtField.setBounds(225, 73, 96, 28);
        taskTxtField.setBackground(new Color(230, 181, 242));
        this.getContentPane().add(taskTxtField);
        taskTxtField.setColumns(10);

        serverTxtField = new JTextField();
        serverTxtField.setBounds(225, 117, 96, 28);
        serverTxtField.setColumns(10);
        serverTxtField.setBackground(new Color(230, 181, 242));
        this.getContentPane().add(serverTxtField);

        tLimitTxtField = new JTextField();
        tLimitTxtField.setBounds(225, 160, 96, 28);
        tLimitTxtField.setColumns(10);
        tLimitTxtField.setBackground(new Color(230, 181, 242));
        this.getContentPane().add(tLimitTxtField);

        minArrivalTxtField = new JTextField();
        minArrivalTxtField.setBounds(225, 203, 96, 28);
        minArrivalTxtField.setColumns(10);
        minArrivalTxtField.setBackground(new Color(230, 181, 242));
        this.getContentPane().add(minArrivalTxtField);

        maxArrivalTxtField = new JTextField();
        maxArrivalTxtField.setBounds(225, 246, 96, 28);
        maxArrivalTxtField.setColumns(10);
        maxArrivalTxtField.setBackground(new Color(230, 181, 242));
        this.getContentPane().add(maxArrivalTxtField);

        minProcessingTxtField = new JTextField();
        minProcessingTxtField.setBounds(225, 289, 96, 28);
        minProcessingTxtField.setColumns(10);
        minProcessingTxtField.setBackground(new Color(230, 181, 242));
        this.getContentPane().add(minProcessingTxtField);

        maxProcessingTxtField = new JTextField();
        maxProcessingTxtField.setBounds(225, 332, 96, 28);
        maxProcessingTxtField.setColumns(10);
        maxProcessingTxtField.setBackground(new Color(230, 181, 242));
        this.getContentPane().add(maxProcessingTxtField);

    }
    void init() {
        JLabel lbl1 = new JLabel("Number of clients:");
        lbl1.setBounds(29, 68, 186, 33);
        lbl1.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl1.setFont(new Font("Cambria", Font.BOLD, 16));
        this.getContentPane().add(lbl1);

        JLabel lbl2 = new JLabel("Number of queues:");
        lbl2.setBounds(29, 112, 186, 33);
        lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl2.setFont(new Font("Cambria", Font.BOLD, 16));
        this.getContentPane().add(lbl2);

        JLabel lbl3 = new JLabel("Simulation interval:");
        lbl3.setBounds(29, 155, 186, 33);
        lbl3.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl3.setFont(new Font("Cambria", Font.BOLD, 16));
        this.getContentPane().add(lbl3);

        JLabel lbl4 = new JLabel("Minimum arrival time:");
        lbl4.setBounds(29, 198, 186, 33);
        lbl4.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl4.setFont(new Font("Cambria", Font.BOLD, 16));
        this.getContentPane().add(lbl4);

        JLabel lbl5 = new JLabel("Maximum arrival time:");
        lbl5.setBounds(29, 241, 186, 33);
        lbl5.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl5.setFont(new Font("Cambria", Font.BOLD, 16));
        this.getContentPane().add(lbl5);

        JLabel lbl7 = new JLabel("Maximum service time:");
        lbl7.setBounds(29, 327, 186, 33);
        lbl7.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl7.setFont(new Font("Cambria", Font.BOLD, 16));
        this.getContentPane().add(lbl7);

        JLabel lbl6 = new JLabel("Minimum service time:");
        lbl6.setBounds(29, 284, 186, 33);
        lbl6.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl6.setFont(new Font("Cambria", Font.BOLD, 16));
        this.getContentPane().add(lbl6);

        JLabel lbl8 = new JLabel("QUEUES MANAGEMENT");
        lbl8.setBounds(0, 0, 1065, 62);
        lbl8.setHorizontalAlignment(SwingConstants.CENTER);
        lbl8.setFont(new Font("Cambria", Font.BOLD, 24));
        this.getContentPane().add(lbl8);
    }

}
