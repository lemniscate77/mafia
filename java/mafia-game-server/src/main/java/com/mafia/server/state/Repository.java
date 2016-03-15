/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.state;

import com.mafia.server.ConcurrentArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;

/**
 *
 * @author w1428368
 */
public class Repository {

    private static ConcurrentHashMap<String, Game> games;

    public Repository() {
        games = new ConcurrentHashMap<>();
    }

    public static synchronized Game getByKey(String key) {
        Set<String> keys = games.keySet();
        Game result = null;
        for (String rowKey : keys) {
            Game game = games.get(rowKey);
            if (game != null) {
                if (game.getKey().equals(key)) {
                    result = game;
                    break;
                }
            }
        }
        return result;
    }

    public static synchronized Game getBySession(Session session) {
        Set<String> keys = games.keySet();
        Game result = null;
        for (String rowKey : keys) {
            Game game = games.get(rowKey);
            if (game != null) {
                Iterator<Player> iterator = game.getPlayers().iterator();
                while (iterator.hasNext()) {
                    Player player = iterator.next();
                    if (player.getSession().equals(session)) {
                        result = game;
                        break;
                    }
                }
            }
            if (result != null) {
                break;
            }
        }
        return result;
    }

    public static synchronized Game create(Player player) {
        String newKey = makeKey();
        Game game = new Game(player, newKey);
        games.put(newKey, game);
        return game;
    }

    private static String makeKey() {
        boolean ok = false;
        String key = null;
        while (!ok) {
            key = UUID.randomUUID().toString().substring(1, 5);
            ok = !games.contains(key);
        }
        return key;
    }

}