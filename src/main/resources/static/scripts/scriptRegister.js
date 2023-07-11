function validatePassword() {
	var password = document.getElementById("password").value;
	var confirmPassword = document.getElementById("confirmPassword").value;

	if (password.trim() === "") {
		alert("A senha não pode ser vazia.");
		return false;
	}

	if (password.length < 4) {
		alert("A senha deve ter pelo menos 4 caracteres.");
		return false;
	}

	if (password !== confirmPassword) {
		alert("As senhas não coincidem.");
		return false;
	}

	return true;
}
