package appli;

import java.util.List;

/**
 * 
 * @author naiya
 *
 *Interface suivant la logique du DP Strategy
 *On peut trier les fichiers de diff�rentes fa�ons
 *On isole diff�rents mode de tri afin que chaque mod�le puisse �voluer si besoin
 */
public interface Tris {
	
	public  void sort(List<People> list);

}
