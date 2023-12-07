package control;

import java.io.Serializable;
import java.util.Date;

public class TestBean implements Serializable{
  
  private Date seinenngappi;
  
  public TestBean(Date seinenngappi) {
    this.seinenngappi = seinenngappi;
  }

  public Date getSeinenngappi() {
    return seinenngappi;
  }

  public void setSeinenngappi(Date seinenngappi) {
    this.seinenngappi = seinenngappi;
  }
  
}
