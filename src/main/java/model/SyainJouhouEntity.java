package model;

import java.io.Serializable;

public class SyainJouhouEntity implements Serializable {

	private int id; // 社員ID
	private String name; // 社員名
	private String yomigana; // 読み仮名（ローマ字）
	private String seibetsu; // 性別
	private String syussinn; // 出身地
	private String juusyo; // 現住所
	private int juutakuTeateUmu; // 住宅手当有無(0=なし 1=あり)
	private String shokui; // 職位
	private String salary; // 年収
	private int nyuusyaNengetsu; // 入社年月
	private int kinzokuNennsuu; // 勤続年数

	// コンストラクタ
	public SyainJouhouEntity() {

	}

	// コンストラクタ（社員ID、名前、読み仮名、性別、出身地、現住所、住宅手当有無、職位、年収、入社年月、勤続年数）
	public SyainJouhouEntity(int id, String name, String yomigana,
			String seibetsu, String syussinn, String juusyo,
			int juutakuTeateUmu, String shokui, String salary,
			int nyuusyaNengetsu, int kinzokuNennsuu) {

		this.id = id;
		this.name = name;
		this.yomigana = yomigana;
		this.seibetsu = seibetsu;
		this.syussinn = syussinn;
		this.juusyo = juusyo;
		this.juutakuTeateUmu = juutakuTeateUmu;
		this.shokui = shokui;
		this.salary = salary;
		this.nyuusyaNengetsu = nyuusyaNengetsu;
		this.kinzokuNennsuu = kinzokuNennsuu;

	}

	// アクセサメソッド
	/**
	 * idを取得します。
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * idを設定します。
	 *
	 * @param id
	 *            id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * nameを取得します。
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * nameを設定します。
	 *
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * yomiganaを取得します。
	 *
	 * @return yomigana
	 */
	public String getYomigana() {
		return yomigana;
	}

	/**
	 * yomiganaを設定します。
	 *
	 * @param yomigana
	 *            yomigana
	 */
	public void setYomigana(String yomigana) {
		this.yomigana = yomigana;
	}

	/**
	 * seibetsuを取得します。
	 *
	 * @return seibetsu
	 */
	public String getSeibetsu() {
		return seibetsu;
	}

	/**
	 * seibetsuを設定します。
	 *
	 * @param seibetsu
	 *            seibetsu
	 */
	public void setSeibetsu(String seibetsu) {
		this.seibetsu = seibetsu;
	}

	/**
	 * syussinnを取得します。
	 *
	 * @return syussinn
	 */
	public String getSyussinn() {
		return syussinn;
	}

	/**
	 * syussinnを設定します。
	 *
	 * @param syussinn
	 *            syussinn
	 */
	public void setSyussinn(String syussinn) {
		this.syussinn = syussinn;
	}

	/**
	 * juusyoを取得します。
	 *
	 * @return juusyo
	 */
	public String getJuusyo() {
		return juusyo;
	}

	/**
	 * juusyoを設定します。
	 *
	 * @param juusyo
	 *            juusyo
	 */
	public void setJuusyo(String juusyo) {
		this.juusyo = juusyo;
	}

	/**
	 * jutakuTeateUmuを取得します。
	 *
	 * @return jutakuTeateUmu
	 */
	public int getJutakuTeateUmu() {
		return juutakuTeateUmu;
	}

	/**
	 * jutakuTeateUmuを設定します。
	 *
	 * @param jutakuTeateUmu
	 *            jutakuTeateUmu
	 */
	public void setJutakuTeateUmu(int jutakuTeateUmu) {
		this.juutakuTeateUmu = jutakuTeateUmu;
	}

	/**
	 * shokuiを取得します。
	 *
	 * @return shokui
	 */
	public String getShokui() {
		return shokui;
	}

	/**
	 * shokuiを設定します。
	 *
	 * @param shokui
	 *            shokui
	 */
	public void setShokui(String shokui) {
		this.shokui = shokui;
	}

	/**
	 * salaryを取得します。
	 *
	 * @return salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * salaryを設定します。
	 *
	 * @param salary
	 *            salary
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * nyuusyaNengetsuを取得します。
	 *
	 * @return nyuusyaNengetsu
	 */
	public int getNyuusyaNengetsu() {
		return nyuusyaNengetsu;
	}

	/**
	 * nyuusyaNengetsuを設定します。
	 *
	 * @param nyuusyaNengetsu
	 *            nyuusyaNengetsu
	 */
	public void setNyuusyaNengetsu(int nyuusyaNengetsu) {
		this.nyuusyaNengetsu = nyuusyaNengetsu;
	}

	/**
	 * kinzokuNennsuuを取得します。
	 *
	 * @return kinzokuNennsuu
	 */
	public int getKinzokuNennsuu() {
		return kinzokuNennsuu;
	}

	/**
	 * kinzokuNennsuuを設定します。
	 *
	 * @param kinzokuNennsuu
	 *            kinzokuNennsuu
	 */
	public void setKinzokuNennsuu(int kinzokuNennsuu) {
		this.kinzokuNennsuu = kinzokuNennsuu;
	}
}
