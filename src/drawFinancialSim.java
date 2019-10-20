import processing.core.*;
import java.util.*;

public final class drawFinancialSim
   extends PApplet {

  communityLSim theCommunity;

  /* draw the lottery data - this is fairly fragile and dependent on scale */
  /* slightly more robust, needs further tuning for full stability */
  public void plotPoints(int r, int g, int b, ArrayList<Float> OnePlayersFunds, float max, float min) {
    float x =20, y =200;
    float pX =0;
    float pY = 0;
    float priorYear = OnePlayersFunds.get(0);
	  int heightOff = 10;

	  float scaleOff = (max-min);

    for (int i=0; i < OnePlayersFunds.size(); i++) {
      if (i >= 1) {
        priorYear = ((OnePlayersFunds.get(i-1)-min)/scaleOff)*(height/2.1f);
      }
      fill(r, g, b);
      y = heightOff + ((OnePlayersFunds.get(i)-min)/scaleOff)*(height/2.1f);
      ellipse(x, height - y, 8, 8);
      if(i >=1) {
        pY = heightOff + priorYear;
        stroke(r, g, b);
        line(pX, height -pY, x, height - y);
      }
      if (i > 1) {
       pX = x;
      }
      x+=12;
    }
  }

  /* draw the debt data */
  public void plotDot(int r, int g, int b, float PlayersDebt, float max, float min, int i) {
    float x =40, y =200;
    int heightOff = 420;
    float scaleOff = (max-min)/(height-heightOff-20);

    fill(r, g, b);
    y = heightOff + (PlayersDebt - min)/scaleOff;
    x += i*(width-40)/theCommunity.getSize();
    stroke(r, g, b);
    line(x, height - heightOff, x, height-y);
    stroke(0);
    ellipse(x, height - y, 8, 8);
  }

  public void settings() {
      size(1000, 800);
  }

  public void setup() {
		noLoop();
		theCommunity = new communityLSim(30);
		theCommunity.simulateYears(80);
	}

  public void draw() {
  	background(255);

    //add some text to the output
    fill(0);
    text("max debt paid: "+theCommunity.maxDebtPay(), 5, 12);
    text("min debt paid: "+theCommunity.minDebtPay(), 5, height/2-12);

    text("max pocket funds: "+theCommunity.maxPocket(), 5, 412);
    text("min pocket funds: "+theCommunity.minPocket(), 5, height-12);
    fill(0);
    line(0, height/2, width, height/2);

    //draw a plot for each of the community members
	  for (int i=0; i < theCommunity.getSize(); i++) {
		  AbstractPlayer p = theCommunity.getPlayer(i);
      //plot lottery data
		  plotPoints(p.getR(), p.getG(), p.getB(), p.getFunds(), theCommunity.maxPocket(), theCommunity.minPocket());
      //plot debt data (comment out while developing) - cast player to a manageDebt
      plotDot(p.getR(), p.getG(), p.getB(), ((manageDebt)p).totalDebt(), theCommunity.maxDebtPay(), theCommunity.minDebtPay(), i);
	  }

  }

  //necessary main to use Processing to draw
   public static void main(String[] args) {
        PApplet.main("drawFinancialSim");
    }
}