package com.teleco.minimus.services;

import java.util.HashMap;

import com.teleco.minimus.entities.Pareja;

public abstract class Parejas {

    private static HashMap<Integer, Pareja> parejas = new HashMap<Integer, Pareja>();

    public static boolean nuevaPareja(Pareja pareja) {
        
        if ( parejas.containsKey(pareja.getId()) )
            return false;

        parejas.put(pareja.getId(), pareja);
        return true;
    }

    public static HashMap<Integer, Pareja> getParejas() {
        
        HashMap<Integer, Pareja> setParejas = new HashMap<Integer, Pareja>();

        for (Integer id : parejas.keySet()) {
            setParejas.put(id, parejas.get(id));
            if ( setParejas.size() == 2 )
                return setParejas;
        }

        return null;
    }
    
}
