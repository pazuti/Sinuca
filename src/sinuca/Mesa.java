/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinuca;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author andrei
 */
public class Mesa extends JPanel {

    private ArrayList<Bola> listaBolas = new ArrayList();
    private int nBolas;
    private Taco taco = new Taco();

    Mesa(int nBolas) {
        this.nBolas = nBolas;
        Bola bola = new Bola(Color.WHITE, 300, 200, this);
        listaBolas.add(bola);
        Thread bolaBranca = new Thread(bola);
        bolaBranca.start();
        
        for (int i = 1; i < this.nBolas; i++) {
            listaBolas.add(new Bola(Color.BLACK, this));
        }
        this.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent event) {

                if (event.getPoint().distance(listaBolas.get(0).getPosicao()) <= 8) {
                    taco.setEmMovimento(true);
                }
            }
        });
        
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent event) {
                Random r = new Random();
                if (taco.isEmMovimento()) {
                    taco.setEmMovimento(false);
                    taco.tacada(listaBolas.get(0).getPosicao(), event.getPoint());
                    listaBolas.get(0).setQuemBateu(taco.getPontaTaco());
                    listaBolas.get(0).setAngulo(taco.getAngulo());
                    listaBolas.get(0).setVelocidade(taco.getForca().intValue());
                    try {
                        listaBolas.get(0).move();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    repaint();
                }
            }
        });

        setDoubleBuffered(true);

        System.out.println(bolaBranca.getState());

        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        g.setColor(Color.green.darker());
        g.fillRect(0, 0, 500, 500);

        for (Bola bola : listaBolas) {
            g.setColor(bola.getCor());
            g.fillOval(bola.getPosicao().x - 8, bola.getPosicao().y - 8, 16, 16);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
}
