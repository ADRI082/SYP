package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

			for (Element tr : tbody.getElementsByTag("tr")) { // Busca en cada tr todos los th y los td
				

				for (Element th : tr.getElementsByTag("th")) {
					rowBuilder.append(th.text() + "  ");
					rowBuilder.append(" ");
				}
				
				rowBuilder.append("\n");

				for (Element td : tr.getElementsByTag("td")) {
					rowBuilder.append(td.text() + " ");
					rowBuilder.append(" ");
				}	
				
			}
			
			System.out.println(rowBuilder.toString());
			
			BufferedWriter escritor;
			try {
				escritor = new BufferedWriter(new FileWriter(new File("fichero.txt")));
				escritor.append(rowBuilder);
				escritor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

}