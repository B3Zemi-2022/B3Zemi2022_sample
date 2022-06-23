package jp.sagalab.b3semi;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

public final class BezierCurve {
  public static BezierCurve create(List<Point2D.Double> _controlPoints) {
    return new BezierCurve(_controlPoints);
  }

  /**
   * ベジェ曲線をパラメーター _t で評価する
   * @param _t
   * @return 評価点
   */
  double x;
  double y;
  public Point2D.Double evaluate(double _t) {
    int listLength = m_controlPoints.size();
    java.util.List<Point2D.Double> b = m_controlPoints;

    for (int i = 0; i < listLength - 1; i++) {
      b = getDividedPointList(_t, b);
    }
    Point2D.Double evaluatePoint = b.get(0);
    new Point2D.Double( x, y ) = (evaluatePoint.x,evaluatePoint.y);
    //return evaluatePoint;

    return new Point2D.Double( x, y );
  }

  /* ↓ここから必要な関数を書き足していく↓ */

  //点のx座標を取得する.
  public java.lang.Double getX() {
    return m_x;
  }

  //点のy座標を取得する.
  public java.lang.Double getY() {
    return m_y;
  }

  //座標 (x, y) を表すための変数.
  private java.lang.Double m_x;
  private java.lang.Double m_y;

  //public Point(java.lang.Double _x,java.lang.Double _y)

  //この点ともう一つの点をt:(1-t)で内分(t<0 または t>1 の場合は外分)する.
  public Point2D divide(java.lang.Double _t, Point2D.Double _other) {
    java.lang.Double otherX = _other.getX();
    java.lang.Double otherY = _other.getY();

    java.lang.Double dividedX = otherX * _t + m_x * (1.0 - _t);
    java.lang.Double dividedY = otherY * _t + m_y * (1.0 - _t);
    Point2D.Double dividePoint = new Point2D.Double(dividedX, dividedY);

    return dividePoint;
  }

  //この点列を順にt:(1-t) で内分して新たな点列を返す.
  public java.util.List<Point2D.Double> getDividedPointList(java.lang.Double _t, java.util.List<Point2D.Double> _b) {
    java.util.List<Point2D.Double> pointList = _b;
    java.util.List<Point2D.Double> dividedPointList = new ArrayList<>();
    int pointListLength = pointList.size();

    for (int i = 0; i < pointListLength - 1; i++) {
      Point2D.Double dividedPoint = pointList.get(i).divide(_t, pointList.get(i+1));
      dividedPointList.add(dividedPoint);
    }

    return dividedPointList;
  }


  private BezierCurve(List<Point2D.Double> _controlPoints) {
    m_controlPoints = _controlPoints;
  }

  private List<Point2D.Double> m_controlPoints;
}
