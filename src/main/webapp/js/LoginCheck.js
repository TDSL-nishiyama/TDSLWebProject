/**
 * ログイン画面チェック用
 */

/*ログインボタン押下*/
function checkIDandText() {

	var id = document.login.id.value;
	var password = document.login.password.value;
	var almsg = "";

	if (id == "") {
		almsg ="ログインIDを入力してください";
	} else if (!id.match(/^[A-Za-z0-9]+$/)) {
		almsg = "IDは半角英数のみ入力可能です";
	} else if (password == "") {
		almsg = "パスワードを入力してください";
	}
	if (almsg == "") {
		return true;
	} else {
		alert(almsg);
		return false;
	}
}