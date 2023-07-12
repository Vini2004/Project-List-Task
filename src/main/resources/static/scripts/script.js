var descricaoTarefas = document.getElementsByClassName('descricao-tarefa');

window.addEventListener('DOMContentLoaded', function() {
	const dataInicialSpans = document.querySelectorAll('.data-inicial');
	const dataFinalSpans = document.querySelectorAll('.data-final');
	for (const span of dataInicialSpans) {
		span.textContent = span.textContent.replace('T', ' ');
	}
	for (const span of dataFinalSpans) {
		span.textContent = span.textContent.replace('T', ' ');
	}
});


for (var i = 0; i < descricaoTarefas.length; i++) {
	var descricao = descricaoTarefas[i].textContent;
	if (descricao.length > 20) {
		descricaoTarefas[i].textContent = descricao.substring(0, 20) + '...';
	}
}

document.getElementById('search-form').addEventListener('submit', function(event) {
	event.preventDefault(); // Impede o envio do formulário

	const searchValue = document.getElementById('search-input').value.toLowerCase();

	const rows = document.querySelectorAll('tbody tr');

	for (const row of rows) {
		const columns = row.getElementsByTagName('td');
		let found = false;

		for (const column of columns) {
			const text = column.textContent.toLowerCase();

			if (text.includes(searchValue)) {
				found = true;
				break;
			}
		}

		row.style.display = found ? '' : 'none';
	}
});

function confirmDelete() {
	return confirm('Tem certeza que deseja excluir esta tarefa?');
}