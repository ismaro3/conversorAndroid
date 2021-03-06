package com.ismaro3.conversor;

import java.util.Locale;

import android.widget.EditText;
import android.widget.Spinner;

public class Aux {

	/*Cambiar seǵun usuario*/
	public static int decimales = 4;
	
	public static double[] factores;
	
	public static final  double[] equivLongitud = new double[]
			{1000,1,0.1,0.01,0.001,0.0254,0.3048,0.914400007,1609.347088,1851.660014,
			4828.04126,201.168,20.1168,5.0292,5554.980043,182.8800014,1.8288,1.6719};
	public static final  double[] equivSuperficie = new double[]
			{4046.8564224,100,0.000506707479,10000,485000,1011.7141056,0.0001,
			0.09290304,0.00064516,1000000,2589988.110336,0.000001,9.290394,
			25.29285264,0.83612736,93239571.972};
	public static final double[] equivVolumen = new double[]
			{1000,1,0.001,0.000001,1,0.001,0.029573529563,0.0284130625,0.473176473,
		0.56826125,3.785411784,4.54609,0.014786764781,0.017758164062,0.005,0.05, 0.2365882365, 0.250,0.0049289215938,0.0059193880208, 764.55485798, 28.316846592,0.016387064,0.11829411825,
		0.1420653125,158.98729493,35.239070167,36.36872,8.8097675417, 9.09218};
	
	public static final double[] equivPeso = new double[]
			{1,0.001,0.00001,0.000001,0.000000001, 10.0E-13,10.0E-16,0.4535923704,
			0.0283495232,0.0311034768,11.5002325,1000,0.101971621,0.02,0.2,0.2,6.4798909802E-5,0.0002,
			0.0015551738,6.3502931968,0.0017718452,0.6060606061,0.0378787879,0.0037878788,3.7878787879E-4
			};
	
	public static final double[] equivTiempo = new double[]
			{1.1574074074E-14,1.1574074074E-8,1.1574074074E-5,6.9444444444E-4,
		0.0416666667,1.0,7.0,30.436849537,365.2421990741,1826.210995371,3652.4219907407,36524.2199074074,365242.199074074,
		1.6326326299E12, 5.0396118628E12};

	public static final double[] equivPresion = new double[]
			{1.0E-5,0.01,1.0E-5,1.0132499997,0.0013332237,1,0.0689475728,0.9803921569,
		4.7880259056E-4,0.0338638867,9.80665E-5,0.0024875622};
	
	public static final double[] equivVelocidad = new double[]
			{1,0.2777777778,16.6666666667,1000,0.44704,26.8223999464,1609.3439987125,4.2333333333E-4,0.0254,
			0.00508,0.3047999993,0.5144444444,2.9979245824E8};
		
	public static final double[] equivEnergia =  new double[]
			{1,1000,1000000,3599999.99712,3599.99999712,1,4.18400000241, 4184.00000241, 4184.00000241,
			4184000.00240999,4184000.00240999,1.6021764632E-19,1.0E-7, 1055.0558529688};
		
	public static final double[] equivPotencia = new double[]
			{1,1000,1000000,0.0012589254,0.00116222222,4.1840000024, 1.1622222224,4184.00000241,
			735.4987500021,745.6998713571,2.7777777778E-4,1,1.3557483731};
			
	public static final double[] equivAngulos = new double[]
			{1,0.0166666667,2.7777777778E-4,57.2957795786,0.9,0.05625
			};
	
	public static final double[] equivBits = new double[]
			{0.125,128,131072,1.34217728E8,1.3743895353E11,1.407374883E14,1,1024,1048576,
			1.0737418284E9,1.0995116236E12, 1.1258999052E15, 7.340032E8,4.7029892084E9,
			2.684354571E10,1509949.44};
	
	public static double valor1 = 0.0;
	public static double valor2 = 0.0;
	/*Convierte el valor expresado en la unidad1 a la unidad2*/
	public static double calcularConversion(double valor, int unidad1, int unidad2){
		
		Double factorIzq = factores[unidad1];
		Double factorDer = factores[unidad2];
		
		return (valor*factorIzq)/factorDer;
		
	}
	
	/*Se le pasan los dos spinners con las unidades seleccionadas, un EditText que albergará
	 * el resultado y el modo de conversión: 0 si es de izda-> dcha o 1 si es dcha-> izda
	*/
	public static void ponerResultado(Spinner unidad1,Spinner unidad2,
			EditText txtResultado,int modo){
		
		/*Multiplico por unidad izquierda y divido por derecha*/
		
		int udIzq = unidad1.getSelectedItemPosition();
		int udDer = unidad2.getSelectedItemPosition();
		
		double resultado = 0;
		if(modo==0){
		
			resultado = Aux.calcularConversion(valor1,udIzq,udDer);
			valor2 = resultado;
		}
		else{
			
			resultado = Aux.calcularConversion(valor2,udIzq,udDer);
			valor1 = resultado;
		}
		
		
			ponerTexto(resultado,txtResultado);

			
			
		}
	/*Formatea adecuadamente y escribe en <texto> el número <numero>*/
	
	public static void ponerTexto(double numero, EditText texto){
		
		EditText destino = texto;
		
		/*Si el número es menor que 10^(-numeroDecimales), entonces lo
		 * mostramos en notación científica*/
		
		if(numero<Math.pow(10,-decimales) && numero!=0.0){
			destino.setText(MenuConversor.decimalCientifico.format(numero));
		}
		/*En otro caso, lo mostramos en notación normal, con los decimales
		 * recortados*/
		else{
			destino.setText(MenuConversor.decimalNormal.format(numero));
		}
		
		
	}
	/*Si la cadena contiene el caracter 'E', devuelve su posición. En caso contrario,
	 * devuelve un número negativo.
	 */
	public static int esCientifico(String texto){
		int posicion = -1;
		int i = 0;
		while( i < texto.length() && posicion <0){
			if(texto.charAt(i) == 'E'){
				posicion = i;
			}
			i++;
		}
		return posicion;
		
		}
		
		
	}
	



