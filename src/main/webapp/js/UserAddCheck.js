/**
 * ユーザー登録画面チェック用
 */

/*登録ボタン押下*/
function checkUserAdd() {

	var userName = document.userInsert.userName.value;
	var almsg = "";

	if (userName == "") {
		almsg ="ユーザー名を入力してください";
	}
	
	if (almsg == "") {
		return true;
	} else {
		alert(almsg);
		return false;
	}
}