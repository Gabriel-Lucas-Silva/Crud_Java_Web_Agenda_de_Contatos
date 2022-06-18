/**
 * 
 */
 
 function confirmar(id){
	let resposta = confirm("Confirma a exclusao do contato "+id+"?");
	if (resposta){
		alert("Contato Apagado!");
		window.location.href="/agenda/delete?idCon="+id;
	}
}