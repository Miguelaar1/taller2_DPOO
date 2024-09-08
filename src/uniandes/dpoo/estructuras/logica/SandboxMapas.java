package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista( )
    {
    	List<String> valuesMap = new ArrayList<String>(this.mapaCadenas.values());
    	valuesMap.sort(null);
        return valuesMap;
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( )
    {
    	List<String> keysMap = new ArrayList<String>(this.mapaCadenas.keySet());
    	keysMap.sort(new Comparator<String>() {
    		public int compare (String s1, String s2) {
    			return s2.compareTo(s1);
    		}
    	});
        return keysMap;
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera( )
    {
    	String menor = null;
    	if (!this.mapaCadenas.isEmpty()) {
    		List<String> valuesMap= getValoresComoLista();
    		menor = valuesMap.get(0);
    	}
        return menor;
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( )
    {
    	String mayor = null;
    	if (!this.mapaCadenas.isEmpty()) {
    		List<String> keysMap= getLlavesComoListaInvertida();
    		mayor = keysMap.get(0);
    	}
        return mayor;
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {
    	ArrayList<String> keys = new ArrayList<String>(this.mapaCadenas.keySet());
    	for (int i = 0; i < keys.size(); i++) {
    		keys.set(i, keys.get(i).toUpperCase());
    	}
        return keys;
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( )
    {
    	List<String> differentValues = new ArrayList<String>();
    	List<String> values = new ArrayList<String>(this.mapaCadenas.values());
    	for (String value : values) {
    		if (!differentValues.contains(value)) differentValues.add(value);
    	}
        return differentValues.size();
    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena )
    {
    	char[] arrayKey = cadena.toCharArray();// strings are immutable objects
    	
    	int i = 0, j = arrayKey.length - 1;
    	while (i < j) {
    		char temp = arrayKey[i];
    		arrayKey[i] = arrayKey[j];
    		arrayKey[j] = temp;
    		i++;
    		j--;
    	}
    	String nStr = new String(arrayKey);
    	this.mapaCadenas.put(nStr, cadena);
    	
    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave )
    {
    	this.mapaCadenas.remove(llave);
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor )
    {
    	String key = null;
    	for (Entry<String, String> entry : this.mapaCadenas.entrySet()) {
    		if (entry.getValue().equals(valor)) {
    			key = entry.getKey();
    			this.mapaCadenas.remove(key);
    		}
    	}
    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
    	this.mapaCadenas.clear();
    	for (Object str : objetos) {
    		agregarCadena((String) str);
    	}

    }

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	List<String> keys = new ArrayList<String>(this.mapaCadenas.keySet());
    	for (String key : keys) {
    		String value = this.mapaCadenas.remove(key);
    		this.mapaCadenas.put(key.toUpperCase(), value);
    	}
    	
    	
//    	for (Entry<String, String> entry : this.mapaCadenas.entrySet()) {
//    		String nKey = entry.getKey().toUpperCase();
//    		String value = this.mapaCadenas.remove(entry.getKey());
//    		this.mapaCadenas.put(nKey, value);
//    	}
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo )
    {
        boolean equal = true;
        int i = 0;
        ArrayList<String> values = new ArrayList<String>(this.mapaCadenas.values());
        while (equal && i < values.size()) {
        	if (!values.contains(otroArreglo[i])) equal = false;
        	i ++;
        }
        return equal;
    }

}
