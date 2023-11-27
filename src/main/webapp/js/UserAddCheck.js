/**
 * ユーザー登録画面チェック用
 */

/*ユーザー登録ボタン押下*/
function checkUserAdd() {

	/*フォーム入力項目*/
	var userName = document.userInsert.userName.value;
	var kanriFlg = document.userInsert.kanriFlg.value;
	var sei = document.userInsert.sei.value;
	var seiyomi = document.userInsert.seiyomi.value;
	var mei = document.userInsert.mei.value;
	var meiyomi = document.userInsert.meiyomi.value;
	var nyuusyaYMD = document.userInsert.nyuusyaYMD.value;
	var seibetsu = document.userInsert.seibetsu.value;
	var seinenngappi = document.userInsert.seinenngappi.value;
	var syusshin = document.userInsert.syusshin.value;
	var juusyo = document.userInsert.juusyo.value;

	var almsg = "";
	var msg1="必須項目を入力してください";
	
	/*必須項目チェック処理*/
	if (Trim(userName) == "") {
		almsg = msg1;
		alert(almsg);
		return false;
	}

	/*日付形式チェック処理*/
	
	/*日付存在チェック処理*/
	
	/*エラー判定処理*/
	if (almsg == "") {
		return true;
	} else {
		alert(almsg);
		return false;
	}
}