/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sinuca;

import java.awt.Point;

/**
 *
 * @author andrei
 */
class Taco {
    private double forca;
    private double angulo;
    private boolean emMovimento = false;
    private Point pontaTaco;

    public Double getAngulo() {
        return angulo;
    }

    public Double getForca() {
        return forca;
    }

    public Point getPontaTaco() {
        return pontaTaco;
    }
    
    public void tacada(Point localImpacto, Point pontaTaco){
        this.pontaTaco = pontaTaco;
        double distancia = localImpacto.distance(pontaTaco);
        
        if(distancia > 200){
            this.forca = 200;
        }else{
            this.forca = distancia;
        }
        this.angulo = (Math.asin(pontaTaco.distance(pontaTaco.x, localImpacto.y)/distancia));
    }

    public boolean isEmMovimento() {
        return emMovimento;
    }

    public void setEmMovimento(boolean emMovimento) {
        this.emMovimento = emMovimento;
    }
}
