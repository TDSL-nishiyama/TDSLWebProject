SELECT u.name,s.nyuusyaYMD FROM user AS u join usershousai AS s on u.id = s.id WHERE u.id = ?;