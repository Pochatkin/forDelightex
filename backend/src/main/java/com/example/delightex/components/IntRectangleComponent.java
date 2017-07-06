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

    private void log(String message, IntRectangle candidate, IntRectangle elemList) {
        System.out.println("--------------");
        System.out.println(message);
        System.out.println("candidate: " + candidate.toString());
        System.out.println("that have: " + elemList.toString());
        System.out.println("--------------");
    }

    public void generateCounterSky() {
        /*allRectangle.add(new IntRectangle(163, 910, 125));
        counterSky.add(new IntRectangle(163, 910, 125));
        allRectangle.add(new IntRectangle(369, 561, 88));
        allRectangle.add(new IntRectangle(867, 960, 366));
        allRectangle.add(new IntRectangle(994, 1005, 162));
        allRectangle.add(new IntRectangle(996, 1073, 395));*/
        LinkedList<IntRectangle> listCandidate = new LinkedList<>(allRectangle);
        loop:
        for (int j = 0; j < listCandidate.size(); j++) {
            IntRectangle candidate = listCandidate.get(j);

            if (counterSky.size() == 0) {
                log("First add", candidate, candidate);
                counterSky.add(new IntRectangle(candidate));
                continue;
            }

            for (int i = 0; i < counterSky.size(); i++) {
                IntRectangle counterRectangle = counterSky.get(i);
                log("Start", candidate, counterRectangle);
                if (candidate.equals(counterRectangle)) {
                    log("Equals", candidate, counterRectangle);
                    continue;
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getLeftPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getRightPoint() > counterRectangle.getRightPoint()) {
                        if (candidate.getHeight() > counterRectangle.getHeight()) {
                            log("First, Before", candidate, counterRectangle);
                            counterSky.addLast(new IntRectangle(candidate.getLeftPoint(), counterRectangle.getRightPoint(), candidate.getHeight()));
                            int temp = counterRectangle.getRightPoint();
                            counterRectangle.setRightPoint(candidate.getLeftPoint());
                            candidate.setLeftPoint(temp);
                            log("First, After", candidate, counterRectangle);
                        } else {
                            candidate.setLeftPoint(counterRectangle.getRightPoint());
                        }
                    }
                }
                if (candidate.getLeftPoint() <= counterRectangle.getLeftPoint() && candidate.getRightPoint() >= counterRectangle.getRightPoint() && candidate.getHeight() > counterRectangle.getHeight()) {
                    log("Remove", candidate, counterRectangle);
                    counterSky.remove(i);
                    i--;
                }
                if (candidate.getRightPoint() >= counterRectangle.getLeftPoint() && candidate.getLeftPoint() <= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() > counterRectangle.getHeight()) {
                        log("Second, Before", candidate, counterRectangle);
                        counterSky.addLast(new IntRectangle(counterRectangle.getLeftPoint(), candidate.getRightPoint(), candidate.getHeight()));
                        int temp = counterRectangle.getLeftPoint();
                        counterRectangle.setLeftPoint(candidate.getRightPoint());
                        candidate.setRightPoint(temp);
                        log("Second, After", candidate, counterRectangle);
                    } else {
                        candidate.setRightPoint(counterRectangle.getLeftPoint());
                    }
                }
                if (candidate.getLeftPoint() >= counterRectangle.getLeftPoint() && candidate.getRightPoint() <= counterRectangle.getRightPoint()) {
                    if (candidate.getHeight() > counterRectangle.getHeight()) {
                        log("Six, Before", candidate, counterRectangle);
                        counterSky.addLast(new IntRectangle(candidate));
                        counterSky.addLast(new IntRectangle(candidate.getRightPoint(), counterRectangle.getRightPoint(), counterRectangle.getHeight()));
                        counterRectangle.setRightPoint(candidate.getLeftPoint());
                        log("Six, After", counterSky.getLast(), counterRectangle);
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
                    log("candidate out", candidate, counterRectangle);
                    continue loop;
                }
                if (i + 1 == counterSky.size()) {
                    log("Last", candidate, counterRectangle);
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
