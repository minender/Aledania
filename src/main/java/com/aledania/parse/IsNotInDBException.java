/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aledania.parse;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.NoViableAltException;

/**
 *
 * @author federico
 */
public class IsNotInDBException extends NoViableAltException {

    /**
     * message 
     */
    public String message=" Does not exists the term ";
    /**
     * Creates a new instance of
     * <code>IsNotInDBException</code> without detail message.
     */
    public IsNotInDBException() {
    }

    /**
     * Constructs an instance of
     * <code>IsNotInDBException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public IsNotInDBException(String grammarDecisionDescription,
								int decisionNumber,
								int stateNumber,
								IntStream input,
                                                                String message) {
        super(grammarDecisionDescription,decisionNumber,stateNumber, input);
        this.message += message;
    }
}
