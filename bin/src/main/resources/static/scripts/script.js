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

var descricaoTarefas = document.getElementsByClassName('descricao-tarefa');
for (var i = 0; i < descricaoTarefas.length; i++) {
	var descricao = descricaoTarefas[i].textContent;
	if (descricao.length > 20) {
		descricaoTarefas[i].textContent = descricao.substring(0, 20) + '...';
	}
}

function confirmDelete() {
  return confirm('Tem certeza que deseja excluir esta tarefa?');
}