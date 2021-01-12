package com.charlie.currency;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;




public class Currency extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Currency() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

    }

    JLabel poundsLabel = new JLabel("Ammount in GBP:");
    JTextField pounds = new JTextField("100.00",10);
    JLabel AUDLabel = new JLabel("Ammount in AUD:");
    JTextField aud = new JTextField("",10);
    JLabel USDLabel = new JLabel("Ammount in USD:");
    JTextField usd = new JTextField("",10);
    JLabel EURLabel = new JLabel("Ammount in EUR:");
    JTextField eur = new JTextField("",10);

    JButton go = new JButton("Convert");
    JFrame jfrm = new JFrame("Currency Converter");
    static Currency pe;





    class PaintDemo{

        PaintDemo(){
            Properties prop = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();           
            InputStream stream = loader.getResourceAsStream("app.properties");
            try {
                prop.load(stream);
            } catch (IOException e1) {
                throw new RuntimeException("Couldn't load app.properties file");
            }
            jfrm.setSize(1000, 200);
            jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            jfrm.setLayout(new BorderLayout());

            JPanel temp = new JPanel();
            temp.add(poundsLabel);		
            temp.add(pounds);
            temp.add(go);
            go.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        aud.setText(""+Math.round(100*Double.parseDouble(pounds.getText()) *
                                Double.parseDouble(prop.getProperty("GBPtoAUD","1.0").toString())/100.0));
                        usd.setText(""+Math.round(100*Double.parseDouble(pounds.getText()) *
                                Double.parseDouble(prop.getProperty("GBPtoUSD","1.0").toString()))/100.0);
                        eur.setText(""+Math.round(100*Double.parseDouble(pounds.getText()) *
                                Double.parseDouble(prop.getProperty("GBPtoEUR","1.0").toString()))/100.0);
                    } catch (Exception e1) {
                        throw new RuntimeException("One or more of the doubles used were invalid:"+
                                "pounds="+pounds.getText()+
                                " GBPtoAUD="+prop.getProperty("GBPtoAUD","1.0").toString()+
                                " GBPtoUSD="+prop.getProperty("GBPtoUSD","1.0").toString()+
                                " GBPtoEUR="+prop.getProperty("GBPtoEUR","1.0").toString());
                    }
                }
            });
            pe.add(AUDLabel);
            pe.add(aud);
            pe.add(USDLabel);
            pe.add(usd);
            pe.add(EURLabel);
            pe.add(eur);
            jfrm.add(temp, BorderLayout.NORTH);
            jfrm.add(pe, BorderLayout.CENTER);

            jfrm.setVisible(true);
        }

    }
    public static void main(String args[]) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (args.length != 0) {
                    System.out.println("usage: java Currency");
                    System.exit(1);
                }
                pe = new Currency();
                pe.new PaintDemo();
            }
        });
    }

}

