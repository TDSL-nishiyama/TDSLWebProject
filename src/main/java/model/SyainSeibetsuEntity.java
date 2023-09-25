package model;

import java.io.Serializable;

public class SyainSeibetsuEntity implements Serializable {
	
	private int id;				//社員ID
	private String name;		//社員名
	private String seibetsu;	//性別
	
	//コンストラクタ
	public SyainSeibetsuEntity(){
		
	}
	
	//コンストラクタ（社員ID、名前、性別）
	public SyainSeibetsuEntity(int id,String name,String seibetsu){
		this.id = id;
		this.name = name;
		this.seibetsu = seibetsu;
	}
	
	//アクセサメソッド
	/**
	 * idを取得します。
	 * @return id
	 */
	public int getId() {
	    return id;
	}

	/**
	 * idを設定します。
	 * @param id id
	 */
	public void setId(int id) {
	    this.id = id;
	}

	/**
	 * nameを取得します。
	 * @return name
	 */
	public String getName() {
	    return name;
	}

	/**
	 * nameを設定します。
	 * @param name name
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * seibetsuを取得します。
	 * @return seibetsu
	 */
	public String getSeibetsu() {
	    return seibetsu;
	}

	/**
	 * seibetsuを設定します。
	 * @param seibetsu seibetsu
	 */
	public void setSeibetsu(String seibetsu) {
	    this.seibetsu = seibetsu;
	}
	
}
