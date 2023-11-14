/**
 * ユーザー登録画面チェック用
 */

/*ログインボタン押下*/
function checkUpdatePassword() {

  var pass1 = document.updatePassword.pass1.value;
  var pass2 = document.updatePassword.pass2.value;
  var almsg = "";

  if (pass1 == "" || pass2 == "") {
    almsg = "パスワードを入力してください"
  } else if (pass1 != pass2) {
    almsg = "パスワードが異なります"
  }

  if (almsg == "") {
    return true;
  } else {
    alert(almsg);
    return false;
  }
}