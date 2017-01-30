/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun;

/**
 *
 * @author carlos
 */
public interface Filter<T,E> {
    public boolean isMatched(T object, E text);
}
