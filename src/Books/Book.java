package Books;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Book implements Recordable {

	private String titulo;
	private int numPaginas;
	private Author autor;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public void setAutor(Author autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public Author getAutor() {
		return autor;
	}
	
	public void read(DataInputStream in) throws IOException {
		// Lê o título do livro
		titulo = in.readUTF();
		if (titulo.equals(NULL_DATA)) {
			// Se o título for <null>, ele era null quando foi gravado
			titulo = null;
		}
		
		// Lê o número de páginas do livro
		numPaginas = in.readInt();
		
		// Se o objeto autor for nulo, cria o objeto para que ele tenha seus dados populados
		if (autor == null) {
			autor = new Author();
		}
		
		// Chama o método read do autor, que vai popular os dados do autor
		autor.read(in);
	}

	public void write(DataOutputStream out) throws IOException {
		// Grava o título
		if (titulo == null) {
			// Se o título for nulo, grava <null>
			out.writeUTF(NULL_DATA);
		} else {
			out.writeUTF(titulo);
		}
		
		// Grava o número de páginas
		out.writeInt(numPaginas);
		
		// Se houver um autor para o livro faz a gravação dos seus dados
		if (autor != null) {
			autor.write(out);
		}
	}

	@Override
	public String toString() {
		return "Livro -> Título = " + titulo + " -> numPaginas = " + numPaginas
				+ " -> autor = " + autor;
	}
}
