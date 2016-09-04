package br.com.appbuscacep.app;

import java.util.Scanner;

import br.com.buscacep.factory.BuscaCep;
import br.com.buscacep.factory.BuscaCepPostmon;

public class Application {
	
	public static void main(String[] args) {
		new Application().run();
	}
	
	public void run() {
		BuscaCep busca = new BuscaCepPostmon();
		String cep = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite \"sair\" para sair do programa");
        do {
            System.out.printf("Digite um cep: ");
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
