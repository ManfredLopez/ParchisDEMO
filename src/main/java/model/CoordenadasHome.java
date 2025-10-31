package model;

/**
 *
 * @author mjlop
 */
public class CoordenadasHome {

    private Coordenadas[] home = new Coordenadas[]{
        new Coordenadas(77, 66), //red
        new Coordenadas(401, 390), //yellow
        new Coordenadas(401, 66), //blue
        new Coordenadas(77, 390),};//green

//    private Coordenadas[] red_home ={
//    new Coordenadas(293,365)
//    };
//    private Coordenadas[] blue_home ={
//    new Coordenadas(293,365)
//    };
//    private Coordenadas[] yellow_home ={
//    new Coordenadas(293,365)
//    };
//    private Coordenadas[] green_home ={
//    new Coordenadas(293,365)
//    };
    
    public Coordenadas getHome(int colorId) {
        if (colorId < 0) {
            colorId = 0;
        }

        if (colorId > 3) {
            colorId = 3;
        }
        return home[colorId];
    }

}
