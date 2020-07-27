package Print;

import GameElements.GameElement;
import GameElements.Ships.UCMShip;
import GameElements.Weapons.ShockWave;

import java.util.Objects;

public class Board {

    private GameElement[] elements;

    private int currentElements;

    public Board(int width, int height) {
        elements = new GameElement[width*height];
        currentElements = 0;
    }

    public void add(GameElement gameElement) {
        elements[currentElements] = gameElement;
        currentElements++;
    }

    public void update() {
        for (int i = 0; i < getCurrentElements(); i++) {
            checkAttacks(elements[i]);
            if(elements[i].toString() != null){
                if(!elements[i].toString().equals("/-^-\\")){
                    elements[i].move();
                }
            } else continue;
//            assert elements[i] != null;
            checkAttacks(elements[i]);
        }
//        for (GameElement g: elements) {
//            checkAttacks(g);
//            g.move();
//        }
         removeDead();
    }

    public void computerAction() {
        // TODO implement
        for (int i = 0; i < getCurrentElements(); i++) {
            elements[i].computerAction();
        }
    }

    public String toString(int x, int y) {
        // TODO implement
        if(getObjectAt(x,y) != null) {
            return getObjectAt(x,y).toString();
        } else return "";

    }

    private GameElement getObjectAt(int x, int y) {
        for (int i = 0; i < getCurrentElements(); i++) {
            if(elements[i].isOnPosition(x,y)){
                return elements[i];
            }
        }
//        for (GameElement g: elements) {
//            if(g.isOnPosition(x,y)){
//                return g;
//            }
//        }
        return null;
    }

    private int getIndex(int x, int y) {
        // TODO implement
        for (int i = 0; i < getCurrentElements(); i++) {
            if(elements[i].isOnPosition(x,y)){
                return i;
            }
        }
        return -1;
    }

    private void checkAttacks(GameElement gameElement) {
        // TODO implement
        if (gameElement.isAlive() || !gameElement.isOut()){
            for (int i = 0; i < getCurrentElements(); i++) {
                if(!gameElement.equals(elements[i])) {
                    gameElement.performAttack(elements[i]);
                }
            }
        }
    }

    private void remove(/*GameElement gameElement,*/ int j) {
        GameElement tmp;
        // TODO implement
        elements[j].onDelete();

//        if(!elements[j].isAlive()) {
            for (int i = j; i < getCurrentElements() - 1; i++) {
                tmp = elements[i + 1];
                elements[i + 1] = elements[i];
                elements[i] = tmp;
//            replaceElem(elements[i],elements[i+1]);

            }
        //}

//        if(elements[j + 1].isAlive() || !elements[j + 1].isOut()){
//            replaceElem(gameElement,elements[j+1]);
//        }
//
//        else {
//            GameElement tmp;
//            for (int i = j; i < getCurrentElements() - 1; i++) {
//                tmp = elements[i + 1];
//                elements[i + 1] = elements[i];
//                elements[i] = tmp;
//            }
//        }
        currentElements--;

    }

    private int getCurrentElements() {
        return currentElements;
    }

    private void removeDead() {
        // TODO implement
        for (int i = 0; i < getCurrentElements(); i++) {
            if(!elements[i].isAlive() || elements[i].isOut() || elements[i].getShield() <=0){
                remove(/*elements[i],*/ i);
            }
        }
    }

    public void replaceElem(GameElement e1, GameElement e2){
        int x = e1.getCord('x');
        int y = e1.getCord('y');

        if(x != -1 && y != -1) {
            elements[getIndex(x,y)] = e2;
        }
    }

    public String stringify(){
        StringBuilder serials = new StringBuilder();
        for (int i = 0; i < getCurrentElements(); i++) {
            serials.append(elements[i].stringify()).append("\n");
        }
        return serials.toString();
    }


    public GameElement getLabelOwner(int ref) {
        for (int i =0; i< getCurrentElements(); i++) {
            if (elements[ i ].isOwner(ref))
                return elements[i];
        }
        return null;
    }

}