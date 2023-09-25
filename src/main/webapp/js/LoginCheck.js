/**
 * ログイン画面チェック用
 */

function checkIDandText(){

	var id = document.form1.id.value;
	var password = document.form1.password.value;

    if(id == ""){
        window.alert('ログインIDを入力してください');
        return false;
    }else if(!id.match(/^[A-Za-z0-9]+$/)){
    	window.alert('IDは半角英数のみ入力可能です')
    	return false;
    }else if(password == ""){
        window.alert('パスワードを入力してください');
        return false;
    }
}