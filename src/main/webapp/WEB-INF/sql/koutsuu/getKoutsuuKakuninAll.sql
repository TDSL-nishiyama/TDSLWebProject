SELECT K.no,K.id,U.name,K.sendmailaddress,K.riyouhiduke,K.kukan_start,K.kukan_end,K.kingaku,K.bikou,K.modoshiriyuu,KT.status,KT.youkyuu,KT.sashimodoshi,KT.syounin FROM koutsuu AS K JOIN user AS U ON K.id = U.id JOIN ktimestamp AS KT ON K.no = KT.no ORDER BY K.no;