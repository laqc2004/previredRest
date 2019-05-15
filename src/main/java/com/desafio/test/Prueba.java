package com.desafio.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
public class Prueba {
	static List<String> fechaO;
	static List<String> fechaF;
	private static File WORKING_DIRECTORY;
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  try {

	            URL url = new URL("http://127.0.0.1:8080/periodos/api");//your url i.e fetch data from .
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Accept", "application/json");
	            if (conn.getResponseCode() != 200) {
	                throw new RuntimeException("Failed : HTTP Error code : "
	                        + conn.getResponseCode());
	            }
	            InputStreamReader in = new InputStreamReader(conn.getInputStream());
	            BufferedReader br = new BufferedReader(in);
	            String output;
	            String salida="";
	            while ((output = br.readLine()) != null) {
	                salida=salida+output;
	            }
	             
	            
	            JSONObject json = new JSONObject(salida);
	            conn.disconnect();
	            String fCreacion= json.getString("fechaCreacion");
	            String fFin=json.getString("fechaFin");
	            Date dateI = convertir(fCreacion);
	            Date dateF = convertir(fFin);	                  
	            JSONArray fechas = json.getJSONArray("fechas");
	            String fechaObtenidas="";
	            String fechasFaltantes="";
	            //creo todas las fechas
	            List<String> tFechas = new ArrayList<String>();
	            tFechas.add(fechaTexto(dateI));
	            while (!fechaTexto(dateI).equals(fechaTexto(dateF)))
	            {
	            	dateI=sumarMes(dateI);
	            	tFechas.add(fechaTexto(dateI));
	            }
	            tFechas.add(fechaTexto(dateF));
	            fechaO = new ArrayList<String>();
	            fechaF = new ArrayList<String>();
	            for (int i=0;i<tFechas.size();i++)
	            {
	            	String fecha=tFechas.get(i);
	            	int sw=0;
	            	for (int d = 0; d < fechas.length(); d++) 
	            	{
	            		 String jb =fechas.getString(d);
	            		if (fecha.equals(jb))
	            		{
	            			sw=1;
	            			break;
	            		}
	            	}
	            	if (sw==0)
	            	   fechaF.add(fecha);
	            	else
	            		fechaO.add(fecha);		
	            }
	            for (int i=0;i<fechaO.size();i++)
	            {
	            	if (fechaObtenidas.length()>0) fechaObtenidas=fechaObtenidas + ",";
	            	fechaObtenidas= fechaObtenidas+ fechaO.get(i);
	            }
	            
	            for (int i=0;i<fechaF.size();i++)
	            {
	            	if (fechasFaltantes.length()>0) fechasFaltantes=fechasFaltantes + ",";
	            	fechasFaltantes= fechasFaltantes+ fechaF.get(i);
	            }
	            System.out.println("fecha creaciòn:"+fCreacion);
	            System.out.println("fecha fin:"+fFin);
	            System.out.println("fechas recibidas:"+fechaObtenidas);
	            System.out.println("fechas faltantes:"+fechasFaltantes);
	            crearArchivo(fechaO,"fechasObtenida.txt","fechas òbtenidas:");
	            crearArchivo(fechaF,"fechasFaltantes.txt","fechas faltantes:");
	       
	           

	        } catch (Exception e) {
	            System.out.println("Exception in NetClientGet:- " + e);
	        }
	}
	public static String fechaTexto(Date fecha)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(fecha);
	}
	public static Date convertir(String fecha)
	{
		 DateFormat format = new SimpleDateFormat("yyyy-M-dd");
		 Date date = null;
		 try {
			date = format.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return date;
	}
	public static Date sumarMes(Date date)
	{
		 Calendar fecha = Calendar.getInstance(); 
		 fecha.setTime(date);
         fecha.add(Calendar.MONTH, 1);
         return fecha.getTime();
		
	}
	public static String obtenerDirectorio() 
	{
		 String Recurso = Prueba.class.getSimpleName() + ".class";
		    if (WORKING_DIRECTORY == null) {
		        try {
		            URL url = Prueba.class.getResource(Recurso);
		            if (url.getProtocol().equals("file")) {
		                File f = new File(url.toURI());
		                do {
		                    f = f.getParentFile();
		                } while (!f.isDirectory());
		                WORKING_DIRECTORY = f;
		            } else if (url.getProtocol().equals("jar")) {
		                String expected = "!/" + Recurso;
		                String s = url.toString();
		                s = s.substring(4);
		                s = s.substring(0, s.length() - expected.length());
		                File f = new File(new URL(s).toURI());
		                do {
		                    f = f.getParentFile();
		                } while (!f.isDirectory());
		                WORKING_DIRECTORY = f;
		            }
		        } catch (Exception e) {
		            WORKING_DIRECTORY = new File(".");
		        }
		    }
		    
		    return WORKING_DIRECTORY.getPath();
		  
	}
	
	public static void crearArchivo(List<String> datos,String nombre ,String formato )
	{
		String ruta= obtenerDirectorio();
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta+"\\"+nombre);
            pw = new PrintWriter(fichero);
            pw.println(formato+":");
            for (int i = 0; i < datos.size(); i++)
                pw.println(datos.get(i));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	
}
