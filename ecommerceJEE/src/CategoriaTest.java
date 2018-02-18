import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import cad.CategoriaCad;
import javabeans.Categoria;

class CategoriaTest {

	@Test
	void test() {
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
//		Categoria cat = new Categoria(1, "hola", true, 1);
//		lista.add(cat);
		
		lista = CategoriaCad.listar();
		
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getCodigo() + " - " + lista.get(i).getNombre());
		}
		
		System.out.println(lista.size());
		
	}

}
