package university.pds.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryGenerator {
	
	public void writeFile(List<String> list, File file, BufferedWriter bw) throws IOException{
		
		int counter = 0;
		String st = "";			
		
		for(String temp: list){
			if(counter++ > 1){
				
				if(temp.contains("\n")){					
					temp = temp.substring(0, temp.indexOf("\n"));
				}
				
				System.out.println(temp + " " + counter);
				
				//if(counter != 7 && counter != 8){
					
				st += new String("'"+temp+"'");
				
				if(counter != list.size())
				st += ",";
				//}
				
			}
		}
		
		st = new String("insert into pds.unidade (unidade,endereco,bairro,fone,latitude,longitude,id_categoria)"
				+ "values(" + st + ",11);");
		
		bw.write(st);
		bw.newLine();
		
	}
	
	public static void main(String[] args) throws IOException{
		
		Scanner scanner = new Scanner(new FileReader("C:\\Users\\Diego\\Downloads\\unidades-especializadas.csv")).useDelimiter(";");
		List<String> data = new ArrayList<>();
		QueryGenerator sg = new QueryGenerator();
		
		File file = new File("C:\\Users\\Diego\\Downloads\\arquivoUnidades-especializadas.txt");
		
		if(file.exists()){
			System.out.println("Arquivo já existe");
		}
		
		FileWriter fw = new FileWriter(file);
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		while(scanner.hasNext()){
			
			String test = scanner.next();
			
				if(test.contains("\n")){					
					test = test.substring(0, test.indexOf("\n"));					
					data.add(test);
					
					try{
						sg.writeFile(data,file,bw);						
						data = new ArrayList<>();
					}catch(IOException e){
						e.printStackTrace();
					}
										
				}else{
					data.add(test);
				}				
			
			
		}
		
		
		bw.close();
		fw.close();
		
				
		scanner.close();
		
	}

}
