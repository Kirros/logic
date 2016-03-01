package com.logic;

import com.logic.propositional.formulas.types.Formula;

public class Main {

    public static void main(String[] args) {
        //Formula test = Formula.fromString("!(!(A & B) & !C)");
        Formula test = Formula.fromString("((A!&)B(C!");
        System.out.println(test.toString());
    }
}
