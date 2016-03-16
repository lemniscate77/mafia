/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.model.state;

import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;

/**
 *
 * @author Just1689
 */
public class Repository {

    //Games stored by their keys
    private static ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    //Sessions by ID
    private static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    //Player by ID
    private static ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();

    public static Game getGameByKey(String key) {
        return games.get(key);
    }

    public static Player getPlayerBySession(Session session) {
        return getPlayerBySessionId(session.getId());
    }

    public static Player getPlayerBySessionId(String id) {
        return players.get(id);
    }

    public static Session getSessionByPlayer(Player player) {
        return sessions.get(player.getSessionId());
    }

    public static void createGame(Game game) {
        games.put(game.getKey(), game);
    }

}