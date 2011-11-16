/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinuca;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author andrei
 */
public class Sinuca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Mesa mesa = new Mesa(2);


        frame.add(mesa);
        frame.setVisible(true);




    }
}
