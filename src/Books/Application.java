package Books;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Application {
	// Constante para o arquivo que vai armazenar os dados
	private static final String FILE = "books.bin";

	public static void main(String[] args) throws Exception {
		
		// Exibe o menu de opções
		System.out.println("Escolha uma opção:");
		System.out.println("1 - Gravar dados");
		System.out.println("2 - Ler dados");
		System.out.print("=> ");
		
		// Lê a opção digitada
		Scanner keyboard = new Scanner(System.in);
		String opcao = keyboard.nextLine();
		keyboard.close();
		
		if (opcao.equals("1")) {
			gravarDados();
			
		} else if (opcao.equals("2")) {
			lerDados();
			
		} else {
			System.out.println("Opção Desconhecida. -> OPERAÇÃO FINALIZADA!");
		}
	}
	
	private static void lerDados() throws Exception {
		// Cria dois objetos Livro
		Book l1 = new Book();
		Book l2 = new Book();
		
		DataInputStream dis = null;
		try {
			// Cria um stream para leitura dos dados
			dis = new DataInputStream(new FileInputStream(FILE));
			
			// Lê os dados de l1
			l1.read(dis);
			
			// Lê os dados de l2
			l2.read(dis);
		} finally {
			// Fecha a stream
			if (dis != null) {
				dis.close();
			}
		}
		
		// Imprime os dados dos livros (o método toString() será chamado)
		System.out.println(l1);
		System.out.println(l2);
	}
	
	private static void gravarDados() throws Exception {
		// Cria duas datas como String e um formatador
		String d1 = "11/09/1995";
		String d2 = "26/08/2002";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Cria os autores e livros a serem gravados no arquivo
		Author a1 = new Author();
		a1.setNome("Mateus Marques");
		a1.setDataNascimento(sdf.parse(d1));
		
		Author a2 = new Author();
		a2.setNome("Crisley Marques");
		a2.setDataNascimento(sdf.parse(d2));
		
		Book l1 = new Book();
		l1.setTitulo("Marés da Guerra");
		l1.setNumPaginas(357);
		l1.setAutor(a1);
		
		Book l2 = new Book();
		l2.setTitulo("Sherlock Holmes");
		l2.setNumPaginas(500);
		l2.setAutor(a2);
		
		DataOutputStream dos = null;
		try {
			// Cria um stream para gravação dos dados
			dos = new DataOutputStream(new FileOutputStream(FILE));
			
			// Grava os dados de l1
			l1.write(dos);
			
			// Grava os dados de l2
			l2.write(dos);
		} finally {
			// Fecha a stream
			if (dos != null) {
				dos.close();
			}
		}
	}
}
