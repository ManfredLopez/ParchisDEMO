package model;

/**
 *
 * @author mjlop
 */
public class TableroCoordenadas {

    private Coordenadas[] recorrido = new Coordenadas[]{
        new Coordenadas(293, 460), //Casilla 1
        new Coordenadas(293, 435), //Casilla 2
        new Coordenadas(293, 413), //Casilla 3
        new Coordenadas(293, 389), //Casilla 4
        new Coordenadas(293, 365), //Casilla 5 ❇️❇️❇️
        new Coordenadas(293, 342), //Casilla 6
        new Coordenadas(293, 320), //Casilla 7
        new Coordenadas(293, 297), //Casilla 8
        new Coordenadas(307, 280), //Casilla 9
        new Coordenadas(331, 280), //Casilla 10
        new Coordenadas(354, 280), //Casilla 11
        new Coordenadas(377, 280), //Casilla 12 ❇️❇️❇️
        new Coordenadas(400, 280), //Casilla 13
        new Coordenadas(424, 280), //Casilla 14
        new Coordenadas(448, 280), //Casilla 15
        new Coordenadas(471, 280), //Casilla 16
        new Coordenadas(471, 227), //Casilla 17 ❇️❇️❇️
        new Coordenadas(471, 175), //Casilla 18
        new Coordenadas(448, 175), //Casilla 19
        new Coordenadas(424, 175), //Casilla 20
        new Coordenadas(400, 175), //Casilla 21
        new Coordenadas(377, 175), //Casilla 22 ❇️❇️❇
        new Coordenadas(354, 175), //Casilla 23
        new Coordenadas(331, 175), //Casilla 24
        new Coordenadas(305, 190), //Casilla 25
        new Coordenadas(293, 157), //Casilla 26
        new Coordenadas(293, 136), //Casilla 27
        new Coordenadas(293, 113), //Casilla 28 ️
        new Coordenadas(293, 90),  //Casilla 29 ❇️❇️❇
        new Coordenadas(293, 67),  //Casilla 30
        new Coordenadas(293, 42),  //Casilla 31
        new Coordenadas(293, 20),  //Casilla 32
        new Coordenadas(293, 0),   //Casilla 33
        new Coordenadas(239, 0),   //Casilla 34 ❇️❇️❇
        new Coordenadas(186, 0),   //Casilla 35
        new Coordenadas(186, 20),  //Casilla 36
        new Coordenadas(186, 42),  //Casilla 37 ️
        new Coordenadas(186, 67),  //Casilla 38
        new Coordenadas(186, 90),  //Casilla 39 ❇️❇️❇️
        new Coordenadas(186, 113), //Casilla 40
        new Coordenadas(186, 136), //Casilla 41
        new Coordenadas(186, 160), //Casilla 42
        new Coordenadas(172, 175), //Casilla 43
        new Coordenadas(148, 175), //Casilla 44 
        new Coordenadas(125, 175), //Casilla 45
        new Coordenadas(101, 175), //Casilla 46 ❇️❇️❇
        new Coordenadas(78, 175),  //Casilla 47
        new Coordenadas(54, 175),  //Casilla 48
        new Coordenadas(31, 175),  //Casilla 49 ️
        new Coordenadas(7, 175),   //Casilla 50
        new Coordenadas(7, 227),   //Casilla 51 ❇️❇️❇
        new Coordenadas(7, 280),   //Casilla 52
        new Coordenadas(31, 280),  //Casilla 53
        new Coordenadas(54, 280),  //Casilla 54 
        new Coordenadas(78, 280),  //Casilla 55
        new Coordenadas(101, 280), //Casilla 56 ❇️❇️❇
        new Coordenadas(125, 280), //Casilla 57
        new Coordenadas(148, 280), //Casilla 58
        new Coordenadas(172, 280), //Casilla 59
        new Coordenadas(185, 297), //Casilla 60 ️
        new Coordenadas(185, 320), //Casilla 61 
        new Coordenadas(185, 340), //Casilla 62
        new Coordenadas(185, 365), //Casilla 63 ❇️❇️❇
        new Coordenadas(185, 390), //Casilla 64
        new Coordenadas(185, 412), //Casilla 65 
        new Coordenadas(185, 436), //Casilla 66
        new Coordenadas(185, 460), //Casilla 67
        new Coordenadas(239, 460), //Casilla 68 ❇️❇️❇
            
    };

    private int salida_amarillo = 5;
    private int salida_azul = 22;
    private int salida_rojo = 39;
    private int salida_verde = 56;
    private int indice;

//coords camino win
//arreglo 4 filas 7 columnas
    private Coordenadas[][] caminoWin = new Coordenadas[][]{
        { //rojo
            new Coordenadas(239, 20),
            new Coordenadas(239, 42),
            new Coordenadas(239, 67),
            new Coordenadas(239, 90),
            new Coordenadas(239, 113),
            new Coordenadas(239, 136),
            new Coordenadas(239, 160),},
        //amarillo
        {
            new Coordenadas(239, 436),
            new Coordenadas(239, 412),
            new Coordenadas(239, 390),
            new Coordenadas(239, 365),
            new Coordenadas(239, 340),
            new Coordenadas(239, 320),
            new Coordenadas(239, 297),},
        //azul
        {
            new Coordenadas(448, 230),
            new Coordenadas(424, 230),
            new Coordenadas(400, 230),
            new Coordenadas(377, 230),
            new Coordenadas(354, 230),
            new Coordenadas(331, 230),
            new Coordenadas(305, 230),},
        //verde
        {
            new Coordenadas(31, 230),
            new Coordenadas(54, 230),
            new Coordenadas(78, 230),
            new Coordenadas(101, 230),
            new Coordenadas(125, 230),
            new Coordenadas(148, 230),
            new Coordenadas(172, 230),}
    };
// COORDENADAS WIN 4, casillas de en medio
    private Coordenadas[] meta = new Coordenadas[]{
        new Coordenadas(239, 200), //rojo
        new Coordenadas(239, 267), //amarillo
        new Coordenadas(276, 230), //azul
        new Coordenadas(204, 230),}; //verde

    ////
    
    public Coordenadas getPosition(int indiceGeneral) {
        return recorrido[indiceGeneral];
    }

    public int getCasillas() {
        return recorrido.length;
    }

    public int getAvanzarFichaWin(int colorId) {
        return caminoWin[colorId].length;
    }
    
    public Coordenadas getMeta(int colorId) {
        return meta[colorId];
    }

    public Coordenadas getCaminoWin(int colorId, int step) {
        return caminoWin[colorId][step];
    }
    
    public int getSalida(int colorId) {
    switch (colorId) {
        case 0: return salida_rojo;
        case 1: return salida_amarillo;
        case 2: return salida_azul;
        case 3: return salida_verde;
    }
    return -1;
}
    
}
