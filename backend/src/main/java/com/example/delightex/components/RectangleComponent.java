package com.example.delightex.components;

import com.example.delightex.entity.Rectangle;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by mikhail on 07.07.17.
 */
public class RectangleComponent {
    private LinkedList<Rectangle> allRectangle;
    private LinkedList<Rectangle> counterSky;

    public RectangleComponent() {
        allRectangle = new LinkedList<>();
        counterSky = new LinkedList<>();
    }

    public void refresh() {
        this.allRectangle = new LinkedList<>();
        this.counterSky = new LinkedList<>();
    }

    public void generateRectangle(boolean isIntPoints, int n, int maxRangeX, int maxRangeY) {
        for (int i = 0; i < n; i++) {
            Rectangle newRectangle = new Rectangle(isIntPoints, maxRangeX, maxRangeY);
            System.out.println("+++++++++++++++++++");
            System.out.println(newRectangle.toString());
            System.out.println("+++++++++++++++++++");
            allRectangle.addLast(newRectangle);
        }
    }


    public void generateCounterSky() {
        LinkedList<Rectangle> listCandidate = new LinkedList<>(allRectangle);
        loop:
        for (int j = 0; j < listCandidate.size(); j++) {
            Rectangle candidate = listCandidate.get(j);

            if (counterSky.size() == 0) {
                counterSky.add(new Rectangle(candidate));
                continue;
            }

            for (ListIterator<Rectangle> iterator = counterSky.listIterator(); iterator.hasNext(); ) {
                Rectangle counterRectangle = iterator.next();
                if (candidate.equals(counterRectangle)) {
                    continue;
                }
                if (candidate.getLeftPoint() <= counterRectangle.getLeftPoint() && candidate.getRightPoint() >= counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() >= counterRectangle.getHeight()) {
                        iterator.remove();
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                        }
                        if (counterSky.size() == 0) {
                            iterator.add(new Rectangle(candidate));
                        }
                        continue;
                    }
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getLeftPoint() < counterRectangle.getRightPoint()) {
                    if (candidate.getRightPoint() > counterRectangle.getRightPoint()) {
                        if (candidate.getHeight() > counterRectangle.getHeight()) {

                            Rectangle toAdd = new Rectangle(candidate.getLeftPoint(), counterRectangle.getRightPoint(), candidate.getHeight());

                            double temp = counterRectangle.getRightPoint();
                            counterRectangle.setRightPoint(candidate.getLeftPoint());
                            candidate.setLeftPoint(temp);
                            iterator.set(new Rectangle(counterRectangle));
                            iterator.add(toAdd);
                            iterator.previous();
                            continue;

                        } else {
                            candidate.setLeftPoint(counterRectangle.getRightPoint());
                        }
                    }
                }

                if (candidate.getRightPoint() > counterRectangle.getLeftPoint() && candidate.getLeftPoint() <= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() > counterRectangle.getHeight()) {
                        Rectangle toAdd = new Rectangle(counterRectangle.getLeftPoint(), candidate.getRightPoint(), candidate.getHeight());
                        double temp = counterRectangle.getLeftPoint();
                        counterRectangle.setLeftPoint(candidate.getRightPoint());
                        iterator.set(new Rectangle(counterRectangle));
                        candidate.setRightPoint(temp);
                        iterator.add(toAdd);
                        iterator.previous();
                        continue;

                    } else {
                        candidate.setRightPoint(counterRectangle.getLeftPoint());
                    }
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() > counterRectangle.getHeight()) {
                        iterator.add(new Rectangle(candidate));
                        //iterator.previous();
                        iterator.add(new Rectangle(candidate.getRightPoint(), counterRectangle.getRightPoint(), counterRectangle.getHeight()));
                        counterRectangle.setRightPoint(candidate.getLeftPoint());
                        //iterator.previous();
                        continue loop;
                    }
                }
                if (candidate.getLeftPoint() < counterRectangle.getLeftPoint() && candidate.getRightPoint() > counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() < counterRectangle.getHeight()) {
                        listCandidate.addLast(new Rectangle(candidate.getLeftPoint(), counterRectangle.getLeftPoint(), candidate.getHeight()));
                        listCandidate.addLast(new Rectangle(counterRectangle.getRightPoint(), candidate.getRightPoint(), candidate.getHeight()));
                        continue loop;
                    }
                }
                if (candidate.getLeftPoint() == candidate.getRightPoint()) {
                    continue loop;
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint() && candidate.getHeight() <= counterRectangle.getHeight()) {
                    continue loop;
                }
                if (!iterator.hasNext()) {
                    iterator.add(new Rectangle(candidate));
                    iterator.previous();
                    continue;
                }
            }
        }
        System.out.println("---------------");
        System.out.println();
        System.out.println("---------------");

        for (ListIterator<Rectangle> iterator = counterSky.listIterator(); iterator.hasNext(); ) {
            Rectangle counterRectangle = iterator.next();
            if (counterRectangle.getLeftPoint() == counterRectangle.getRightPoint()) {
                iterator.remove();
            }
        }

    }

    public LinkedList<Rectangle> getAllRectangle() {
        return allRectangle;
    }

    public LinkedList<Rectangle> getCounterSky() {
        return counterSky;
    }
}
