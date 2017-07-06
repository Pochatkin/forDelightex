package com.example.delightex.components;

import com.example.delightex.entity.IntRectangle;
import com.example.delightex.entity.RealRectangle;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mikhail on 05.07.17.
 */
@Component
public class RealRectangleComponent {
    private LinkedList<RealRectangle> allRectangle;
    private LinkedList<RealRectangle> counterSky;

    public RealRectangleComponent() {
        allRectangle = new LinkedList<>();
        counterSky = new LinkedList<>();
    }

    public void refresh() {
        this.allRectangle = new LinkedList<>();
        this.counterSky = new LinkedList<>();
    }

    public void generateRectangle(int n, int maxRangeX, int maxRangeY) {
        for (int i = 0; i < n; i++) {
            RealRectangle newRectangle = new RealRectangle(maxRangeX, maxRangeY);
            allRectangle.addLast(newRectangle);
        }
    }

    public void generateCounterSky() {
        LinkedList<RealRectangle> listCandidate = new LinkedList<>(allRectangle);
        loop:
        for (int j = 0; j < listCandidate.size(); j++) {
            RealRectangle candidate = listCandidate.get(j);

            if (counterSky.size() == 0) {
                counterSky.add(new RealRectangle(candidate));
                continue;
            }

            for (int i = 0; i < counterSky.size(); i++) {
                RealRectangle counterRectangle = counterSky.get(i);
                if (candidate.equals(counterRectangle)) {
                    continue;
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getLeftPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getRightPoint() > counterRectangle.getRightPoint()) {
                        if (candidate.getHeight() > counterRectangle.getHeight()) {
                            counterSky.addLast(new RealRectangle(candidate.getLeftPoint(), counterRectangle.getRightPoint(), candidate.getHeight()));
                            double temp = counterRectangle.getRightPoint();
                            counterRectangle.setRightPoint(candidate.getLeftPoint());
                            candidate.setLeftPoint(temp);
                        } else {
                            candidate.setLeftPoint(counterRectangle.getRightPoint());
                        }
                    }
                }
                if (candidate.getLeftPoint() <= counterRectangle.getLeftPoint() && candidate.getRightPoint() >= counterRectangle.getRightPoint() && candidate.getHeight() > counterRectangle.getHeight()) {
                    counterSky.remove(i);
                    i--;
                }
                if (candidate.getRightPoint() >= counterRectangle.getLeftPoint() && candidate.getLeftPoint() <= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() > counterRectangle.getHeight()) {
                        counterSky.addLast(new RealRectangle(counterRectangle.getLeftPoint(), candidate.getRightPoint(), candidate.getHeight()));
                        double temp = counterRectangle.getLeftPoint();
                        counterRectangle.setLeftPoint(candidate.getRightPoint());
                        candidate.setRightPoint(temp);
                    } else {
                        candidate.setRightPoint(counterRectangle.getLeftPoint());
                    }
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() > counterRectangle.getHeight()) {
                        counterSky.addLast(new RealRectangle(candidate));
                        counterSky.addLast(new RealRectangle(candidate.getRightPoint(), counterRectangle.getRightPoint(), counterRectangle.getHeight()));
                        counterRectangle.setRightPoint(candidate.getLeftPoint());
                        continue loop;
                    }
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint() && candidate.getHeight() < counterRectangle.getHeight()) {
                    continue loop;
                }
                if (candidate.getLeftPoint() < counterRectangle.getLeftPoint() && candidate.getRightPoint() > counterRectangle.getRightPoint() && candidate.getHeight() < counterRectangle.getHeight()) {
                    listCandidate.addLast(new RealRectangle(candidate.getLeftPoint(), counterRectangle.getLeftPoint(), candidate.getHeight()));
                    listCandidate.addLast(new RealRectangle(counterRectangle.getRightPoint(), candidate.getRightPoint(), candidate.getHeight()));
                    continue loop;
                }
                if (candidate.getLeftPoint() == candidate.getRightPoint()) {
                    continue loop;
                }
                if (i + 1 == counterSky.size()) {
                    counterSky.addLast(new RealRectangle(candidate));
                    continue loop;
                }
            }
        }

    }

    public LinkedList<RealRectangle> getAllRectangle() {
        return allRectangle;
    }

    public LinkedList<RealRectangle> getCounterSky() {
        return counterSky;
    }


}

