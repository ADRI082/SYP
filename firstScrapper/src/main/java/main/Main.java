package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while(true) {
			

			Document doc = null;
			try {
				doc = Jsoup.connect(
						"https://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000&punto=indice")
						.get();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Element table = doc.select("#ctl00_Contenido_tblÍndice").first(); // Esto sirve para coger un unico elemento
																				// de la pagina

			Element tbody = table.getElementsByTag("tbody").first();
			
			
			StringBuilder rowBuilder = new StringBuilder();
			
			int posicionNombre = 0;
			int posicionFecha = 0;
			int posicionHora = 0;
			int posicionUltimo = 0;

			for (Element tr : tbody.getElementsByTag("tr")) { // Busca en cada tr todos los th y los td 
				
				 for (Element th : tr.getElementsByTag("th")) {
		                
		                switch(th.text()) {
		                
		                case "Nombre":
		                
		                	posicionNombre = th.elementSiblingIndex();
		                	rowBuilder.append(th.text() + "  ");
		                	rowBuilder.append(" ");
		                    break;
		                case "Fecha":
		                  
		                    posicionFecha = th.elementSiblingIndex();
		                	rowBuilder.append(th.text() + "  ");
		                	rowBuilder.append(" ");
		                    break;
		                    
		                case "Hora":
			                  
		                    posicionHora = th.elementSiblingIndex();
		                	rowBuilder.append(th.text() + "  ");
		                	rowBuilder.append(" ");
		                    break;
		                    
		                case "Último":
			                  
		                    posicionUltimo = th.elementSiblingIndex();
		                	rowBuilder.append(th.text() + "  ");
		                	rowBuilder.append(" ");
		                    break;
		                }
		              
		            }
				
				
				rowBuilder.append("\n");

				Elements tds = tr.getElementsByTag("td");	

				if (tds.size() > 0){
					rowBuilder.append(tds.get(posicionNombre).text()+" ");
					rowBuilder.append(tds.get(posicionUltimo).text()+" ");
					rowBuilder.append(tds.get(posicionFecha).text()+" ");
					rowBuilder.append(tds.get(posicionHora).text()+" ");
					rowBuilder.append("\n");
					rowBuilder.append("\n");
				  }	
				
			}
			
			System.out.println(rowBuilder.toString());
			
			BufferedWriter escritor;
			try {
				escritor = new BufferedWriter(new FileWriter(new File("fichero.txt"),true));
				escritor.append(rowBuilder);
				escritor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(60000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		 
		
	}

}