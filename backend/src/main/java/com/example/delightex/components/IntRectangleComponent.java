package com.example.delightex.components;

import com.example.delightex.entity.IntRectangle;

import java.util.LinkedList;

/**
 * Created by mikhail on 05.07.17.
 */
public class IntRectangleComponent {
    private LinkedList<IntRectangle> allRectangle;
    private LinkedList<IntRectangle> counterSky;

    public IntRectangleComponent() {
        allRectangle = new LinkedList<>();
        counterSky = new LinkedList<>();
    }

    public void refreshCounterSky() {
        this.counterSky = new LinkedList<>();
    }

    public void generateRectangle(int n, int maxRangeX, int maxRangeY) {
        for (int i = 0; i < n; i++) {
            IntRectangle newRectangle = new IntRectangle(maxRangeX, maxRangeY);
            if (i == 0) counterSky.add(newRectangle);
            allRectangle.addLast(newRectangle);
        }
    }

    public void generateCounterSky() {
        /*allRectangle.add(new IntRectangle(163, 910, 125));
        counterSky.add(new IntRectangle(163, 910, 125));
        allRectangle.add(new IntRectangle(369, 561, 88));
        allRectangle.add(new IntRectangle(867, 960, 366));
        allRectangle.add(new IntRectangle(994, 1005, 162));
        allRectangle.add(new IntRectangle(996, 1073, 395));*/
        loop:
        for (int j = 1; j < allRectangle.size(); j++) {
            IntRectangle candidate = allRectangle.get(j);

            if (counterSky.size() == 0) {
                counterSky.add(candidate);
            }

            for (int i = 0; i < counterSky.size(); i++) {
                IntRectangle counterRectangle = counterSky.get(i);
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getLeftPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() > counterRectangle.getHeight()) {
                        if (candidate.getRightPoint() > counterRectangle.getRightPoint()) {
                            counterSky.add(i + 1, new IntRectangle(candidate.getLeftPoint(), counterRectangle.getRightPoint(), candidate.getHeight()));
                            int temp = counterRectangle.getRightPoint();
                            counterRectangle.setRightPoint(candidate.getLeftPoint());
                            candidate.setLeftPoint(temp);
                            //Остался неиспользованный кусок
                            if (i + 2 == counterSky.size()) {
                                counterSky.add(i + 2, candidate);
                                continue loop;
                            }
                        } else {
                            counterSky.add(i + 1, candidate);
                            counterSky.add(i + 2, new IntRectangle(candidate.getRightPoint(), counterRectangle.getRightPoint(), counterRectangle.getHeight()));
                            counterRectangle.setRightPoint(candidate.getLeftPoint());
                            continue loop;
                        }
                    }
                } else {
                    if (candidate.getLeftPoint() >= counterRectangle.getRightPoint()) {
                        if (i + 1 != counterSky.size()) {
                            if (candidate.getRightPoint() <= counterSky.get(i + 1).getLeftPoint()) {
                                counterSky.add(i + 1, candidate);
                            }
                        } else {
                            counterSky.add(i + 1, candidate);
                            continue loop;
                        }
                    }
                }
                if (candidate.getLeftPoint() == candidate.getRightPoint()) {
                    continue loop;
                }
            }
        }

    }

    public LinkedList<IntRectangle> getAllRectangle() {
        return allRectangle;
    }

    public LinkedList<IntRectangle> getCounterSky() {
        return counterSky;
    }
}
