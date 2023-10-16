package model;

import java.io.Serializable;
import java.util.Date;

public class SkillEntity implements Serializable {

	private int userId; //ユーザーID
	private Date cStart; //入社年月日
	private String skill1; //資格１
	private String skill2; //資格２
	private String skill3; //資格３
	private String carrier1; //職歴１
	private String carrier2; //職歴２
	private String carrier3; //職歴３

	public SkillEntity(int userId, Date cStart, String skill1, String skill2, String skill3, String carrier1,
			String carrier2, String carrier3) {
		this.userId = userId;
		this.cStart = cStart;
		this.skill1 = skill1;
		this.skill2 = skill2;
		this.skill3 = skill3;
		this.carrier1 = carrier1;
		this.carrier2 = carrier2;
		this.carrier3 = carrier3;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getcStart() {
		return cStart;
	}

	public void setcStart(Date cStart) {
		this.cStart = cStart;
	}

	public String getSkill1() {
		return skill1;
	}

	public void setSkill1(String skill1) {
		this.skill1 = skill1;
	}

	public String getSkill2() {
		return skill2;
	}

	public void setSkill2(String skill2) {
		this.skill2 = skill2;
	}

	public String getSkill3() {
		return skill3;
	}

	public void setSkill3(String skill3) {
		this.skill3 = skill3;
	}

	public String getCarrier1() {
		return carrier1;
	}

	public void setCarrier1(String carrier1) {
		this.carrier1 = carrier1;
	}

	public String getCarrier2() {
		return carrier2;
	}

	public void setCarrier2(String carrier2) {
		this.carrier2 = carrier2;
	}

	public String getCarrier3() {
		return carrier3;
	}

	public void setCarrier3(String carrier3) {
		this.carrier3 = carrier3;
	}
}
