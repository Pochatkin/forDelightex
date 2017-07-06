package com.example.delightex.components;

import com.example.delightex.entity.IntRectangle;

import java.util.LinkedList;

/**
 * Created by mikhail on 05.07.17.
 */
public class IntRectangleComponent<T> {
    private LinkedList<IntRectangle> allRectangle;
    private LinkedList<IntRectangle> counterSky;

    public IntRectangleComponent() {
        allRectangle = new LinkedList<>();
        counterSky = new LinkedList<>();
    }

    public void refresh() {
        this.allRectangle = new LinkedList<>();
        this.counterSky = new LinkedList<>();
    }

    public void generateRectangle(int n, int maxRangeX, int maxRangeY) {
        for (int i = 0; i < n; i++) {
            IntRectangle newRectangle = new IntRectangle(maxRangeX, maxRangeY);
            allRectangle.addLast(newRectangle);
        }
    }

    public void generateCounterSky() {
        LinkedList<IntRectangle> listCandidate = new LinkedList<>(allRectangle);
        loop:
        for (int j = 0; j < listCandidate.size(); j++) {
            IntRectangle candidate = listCandidate.get(j);

            if (counterSky.size() == 0) {
                counterSky.add(new IntRectangle(candidate));
                continue;
            }

            for (int i = 0; i < counterSky.size(); i++) {
                IntRectangle counterRectangle = counterSky.get(i);
                if (candidate.equals(counterRectangle)) {
                    continue;
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getLeftPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getRightPoint() > counterRectangle.getRightPoint()) {
                        if (candidate.getHeight() > counterRectangle.getHeight()) {
                            counterSky.addLast(new IntRectangle(candidate.getLeftPoint(), counterRectangle.getRightPoint(), candidate.getHeight()));
                            int temp = counterRectangle.getRightPoint();
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
                        counterSky.addLast(new IntRectangle(counterRectangle.getLeftPoint(), candidate.getRightPoint(), candidate.getHeight()));
                        int temp = counterRectangle.getLeftPoint();
                        counterRectangle.setLeftPoint(candidate.getRightPoint());
                        candidate.setRightPoint(temp);
                    } else {
                        candidate.setRightPoint(counterRectangle.getLeftPoint());
                    }
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() > counterRectangle.getHeight()) {
                        counterSky.addLast(new IntRectangle(candidate));
                        counterSky.addLast(new IntRectangle(candidate.getRightPoint(), counterRectangle.getRightPoint(), counterRectangle.getHeight()));
                        counterRectangle.setRightPoint(candidate.getLeftPoint());
                        continue loop;
                    }
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint() && candidate.getHeight() < counterRectangle.getHeight()) {
                    continue loop;
                }
                if (candidate.getLeftPoint() < counterRectangle.getLeftPoint() && candidate.getRightPoint() > counterRectangle.getRightPoint() && candidate.getHeight() < counterRectangle.getHeight()) {
                    listCandidate.addLast(new IntRectangle(candidate.getLeftPoint(), counterRectangle.getLeftPoint(), candidate.getHeight()));
                    listCandidate.addLast(new IntRectangle(counterRectangle.getRightPoint(), candidate.getRightPoint(), candidate.getHeight()));
                    continue loop;
                }
                if (candidate.getLeftPoint() == candidate.getRightPoint()) {
                    continue loop;
                }
                if (i + 1 == counterSky.size()) {
                    counterSky.addLast(new IntRectangle(candidate));
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
