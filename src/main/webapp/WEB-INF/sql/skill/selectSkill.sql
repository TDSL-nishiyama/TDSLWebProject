SELECT SK.id, U.name, S.nyuusyaYMD, SK.sikaku1, SK.sikaku1syutokuYMD, SK.sikaku2, SK.sikaku2syutokuYMD, SK.sikaku3, SK.sikaku3syutokuYMD FROM skill_1 AS SK JOIN usershousai AS S ON SK.id = S.id JOIN user AS U ON SK.id = U.id WHERE SK.id = ?;