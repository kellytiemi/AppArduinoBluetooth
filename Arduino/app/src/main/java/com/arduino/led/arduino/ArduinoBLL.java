package com.arduino.led.arduino;

public class ArduinoBLL {

    private static int display = 0;
    private static boolean flag;

    public static void setDisplay(int _display) {display = _display;}
    public static int getDisplay() {return display;}

    public static void setFlag(boolean _flag) {flag = _flag; }
    public static boolean getFlag() { return flag; }

    public static String mostraBits(int _x)
    {
        String retorno = "";
        int aux = 128;

        for (int i=0; i<8; ++i)
        {
            if ( (_x&aux) != 0)
                retorno += "1";
            else
                retorno += "0";
            aux = aux >> 1;
        }
        return retorno;
    }

    public static void ligaDispositivo(String _n)
    {
        int aux = 1 << (Integer.parseInt(_n)-1);
        display = display | aux;
    }

    public static void desligaDispositivo(String _n)
    {
        int aux = 1 << (Integer.parseInt(_n)-1);
        display = display & ~aux;
    }
}
