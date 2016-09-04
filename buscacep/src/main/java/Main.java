import java.util.Scanner;

import br.com.buscacep.factory.BuscaCep;
import br.com.buscacep.factory.BuscaCepPostmon;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		BuscaCep busca = new BuscaCepPostmon();
		String cep = "";
        Scanner scan = new Scanner(System.in);

        do {
            System.out.print("Digite um cep: ");
            cep = scan.next();
            // consulta
            if (!cep.equals("sair")) {
                try {
                    System.out.println(busca.buscaCep(cep));                    
                } catch (Exception ex) {
                    System.err.println("Ocorreu um erro " + ex.getMessage());
                }
            }
        } while (!cep.equals("sair"));
        scan.close();
    }

}
