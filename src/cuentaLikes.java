import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class cuentaLikes {

	public static void main(String[] args) {
		String fileName = "Likes";
		//leer archivo y retornar valores en un arreglo
		ArrayList <String> listaLikes = leeArchivo(fileName);
		List <String> lstNombreFoto = listaLikes.stream().sorted()
				 .distinct()
				 .collect(Collectors.toList());
	
		ArrayList <String> lstFrecuenciaFotos = calculaFrecuencia(listaLikes,lstNombreFoto);
				
		String strMaximo = calculaMaximo(lstFrecuenciaFotos,lstNombreFoto);
		String intMinimo = calculaMinimo(lstFrecuenciaFotos,lstNombreFoto);
		float fltPromedioFotos = calculaPromedios(lstFrecuenciaFotos);
		

	}


private static float calculaPromedios(ArrayList<String> listaLikes) {
	int sum=0;
	float promedio=0;
	for(String numLikes:listaLikes) {
		
		sum+=Integer.parseInt(numLikes);
	}
	promedio=sum/listaLikes.size();
	System.out.println("El promedio de like por foto es:" + promedio);
	return promedio;

	
	}



	static String calculaMinimo(ArrayList<String> lstFrecuenciaFotos,  List<String> lstFotos) {
		int intMinimo=-1;
		int cont=0;
		String strFotoMinimo=new String();
		for(String frecuencia:lstFrecuenciaFotos) {
			if(intMinimo==-1) {
				intMinimo=Integer.parseInt(frecuencia);
			}else {
				if (Integer.parseInt(frecuencia)<intMinimo) {
					intMinimo=Integer.parseInt(frecuencia);
					strFotoMinimo=lstFotos.get(cont);

				}
				
			}
			cont+=1;
		}
		
		System.out.println("La foto con menos likes es la foto: " + strFotoMinimo + " con " + intMinimo + " Likes");
		return strFotoMinimo;
	}


	static String calculaMaximo(ArrayList<String> lstFrecuenciaFotos, List<String> lstFotos) {
		int intMaximo=0;
		int cont=0;
		String strFotoMaximo= new String();
		for(String frecuencia:lstFrecuenciaFotos) {
			if (Integer.parseInt(frecuencia)>intMaximo) {
				intMaximo=Integer.parseInt(frecuencia);
				strFotoMaximo=lstFotos.get(cont);
			}
			cont+=1;
		}
		System.out.println("La foto con más likes es la foto: " + strFotoMaximo + " con " + intMaximo + " Likes");
		return strFotoMaximo;
	}


	static ArrayList <String> calculaFrecuencia(ArrayList <String> listaLikes, List <String> lstFotos){
		ArrayList <String> aLstFrecuencia = new ArrayList <String>();
		String strFrecuencia = new String();
		for(String like:lstFotos) {
			int frecuencia=Collections.frequency(listaLikes, like);
			//strFrecuencia[0]=like;
			strFrecuencia=Integer.toString(frecuencia);
			aLstFrecuencia.add(strFrecuencia);
			
			System.out.println("Foto "+ like +": "+ strFrecuencia + " likes");
		}
		return aLstFrecuencia;
		
	}
	
	static ArrayList <String> leeArchivo(String fileName){
		/*Lee un archivo linea por linea y retorna en un arreglo*/
		ArrayList <String> listaLikes = new ArrayList <String>();
		try {
				FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);
				
				String strLinea = br.readLine();//lee la primera linea
				while(strLinea!=null) {
					//lee
					ArrayList<String> lineaSeparada = new ArrayList<String>
					(Arrays.asList(strLinea.split(" ")));//separa los elementos de la linea y los agrega a lineaSeparada
	
					listaLikes.addAll(lineaSeparada.stream().collect(Collectors.toList()));
					strLinea = br.readLine();
				}
				fr.close();
				br.close();
			}
		catch (Exception e){
			 System.out.println("Excepcion leyendo fichero "+ fileName + ": " + e);
			}	
		return listaLikes;
	}

}
