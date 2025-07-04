package controllers;

import adapters.database.csv.CursoCSVRepositoryAdapter;
import adapters.database.csv.DisciplinaCSVRepositoryAdapter;
import domain.constants.CSVFiles;
import domain.entities.Curso;
import domain.entities.Disciplina;
import utils.List;

public class DisciplinaController {

	private DisciplinaCSVRepositoryAdapter repository = new DisciplinaCSVRepositoryAdapter(CSVFiles.DISCIPLINA);
	private CursoCSVRepositoryAdapter repositoryCurso = new CursoCSVRepositoryAdapter(CSVFiles.CURSO);

	public DisciplinaController() {
		super();
	}

	public List<Disciplina> getAll() {
		try {
			List<Disciplina> list = repository.list();
			return list;
		} catch (Exception e) {
			return new List<Disciplina>();
		}
	}

	public List<Curso> getAllCursos() {
		try {
			List<Curso> list = repositoryCurso.list();
			return list;
		} catch (Exception e) {
			return new List<Curso>();
		}
	}

	public Disciplina show(String codigo) throws Exception {
		return repository.show(codigo);
	}

	public Curso showCurso(Object codigo) throws Exception {
		return repositoryCurso.show(codigo);
	}

	public Disciplina save(String codigo, String nome, String diaSemana, String horarioInicial, String horasDiarias, Object codigoCurso) throws Exception {
		Disciplina record = new Disciplina(codigo, nome, diaSemana, horarioInicial, horasDiarias, codigoCurso);
		
		repository.save(record);
		return record;
	}

	public void update(Disciplina record) throws Exception {
		repository.update(record);
	}

	public void delete(Disciplina record) throws Exception {
		repository.delete(record);
	}

	public ValidationResponse validForm(String nome, String area) throws Exception {

		boolean isValid = true;
		List<String> listaErros = new List<String>();

		if (nome == null || nome.isEmpty()) {
			if (listaErros.isEmpty()) {
				listaErros.addFirst("Nome inválido.");
			} else {
				listaErros.addLast("Nome inválido.");
			}
			isValid = false;
		}

		if (nome == null || area.isEmpty()) {
			if (listaErros.isEmpty()) {
				listaErros.addFirst("Área inválido.");
			} else {
				listaErros.addLast("Área inválido.");
			}
			isValid = false;
		}

		StringBuffer messages = new StringBuffer();

		for (int i = 0; i < listaErros.size(); i++) {
			messages.append(listaErros.get(i));
			messages.append("\n");
		}

		ValidationResponse response = new ValidationResponse();
		response.isValid = isValid;

		if (isValid == false) {

			String errorMessage = "Corrija os erros abaixo para continuar:\n\n" + messages.toString();
			response.message = errorMessage;

		}

		return response;
	}
}
