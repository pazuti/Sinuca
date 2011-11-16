/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinuca;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author andrei
 */
class Bola implements Runnable {

    private Point posicao = new Point();
    private Color cor;
    private ArrayList<Bola> listaBolas;
    private boolean ativo = true;
    private int velocidade;
    private Double angulo;
    private Point quemBateu;
    private JPanel tela;

    public Bola(Color cor, JPanel tela) {
        this.cor = cor;
        this.tela = tela;
        Random random = new Random();
        posicao.setLocation(random.nextInt(484) + 8, random.nextInt(484) + 8);
    }

    public Bola(Color cor, int x, int y, JPanel tela) {
        this.cor = cor;
        this.tela = tela;
        posicao.setLocation(x, y);
    }

    public void setListaBolas(ArrayList<Bola> listaBolas) {
        this.listaBolas = listaBolas;
    }

    public Color getCor() {
        return cor;
    }

    public Point getPosicao() {
        return posicao;
    }

    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }

    public void setAngulo(Double angulo) {
        this.angulo = angulo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setQuemBateu(Point quemBateu) {
        this.quemBateu = quemBateu;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public void run() {
        this.setPosicao(new Point(250, 250));
        while(true){
            try {
                move();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void move() throws InterruptedException {
        //while (true) {
            //System.out.println(velocidade);
            if (this.velocidade > 0) {
                System.out.println(velocidade);
                Double x = 0.;
                Double y = 0.;

                if (this.quemBateu.x <= this.getPosicao().x && this.quemBateu.y <= this.getPosicao().y) {
                    x = this.getPosicao().x + Math.cos(angulo) * 3;
                    y = this.getPosicao().y + Math.sin(angulo) * 3;
                    //System.out.println("1");
                }
                if (this.quemBateu.x <= this.getPosicao().x && this.quemBateu.y >= this.getPosicao().y) {
                    x = this.getPosicao().x + Math.cos(angulo) * 3;
                    y = this.getPosicao().y - Math.sin(angulo) * 3;
                    //System.out.println("2");
                }
                if (this.quemBateu.x >= this.getPosicao().x && this.quemBateu.y <= this.getPosicao().y) {
                    x = this.getPosicao().x - Math.cos(angulo) * 3;
                    y = this.getPosicao().y + Math.sin(angulo) * 3;
                    //System.out.println("3");
                }
                if (this.quemBateu.x >= this.getPosicao().x && this.quemBateu.y >= this.getPosicao().y) {
                    x = this.getPosicao().x - Math.cos(angulo) * 3;
                    y = this.getPosicao().y - Math.sin(angulo) * 3;
                    //System.out.println("4");
                }
                this.setPosicao(new Point(x.intValue(), y.intValue()));
                this.velocidade -= 10;
                tela.repaint();
                Thread.sleep(43);
            }
        }
    //}
}